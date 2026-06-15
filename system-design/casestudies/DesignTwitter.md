# Design Twitter (timeline feed)

Notes on the simplified version — post tweets, follow people, see a home timeline.

## Core features
- Post a tweet.
- Follow / unfollow users.
- Home timeline = recent tweets from everyone you follow, newest first.

## The interesting problem: building the timeline
Two main approaches and the answer is "a mix".

**Fan-out on READ (pull):**
- When you open your timeline, fetch tweets from everyone you follow and merge them.
- Cheap writes (posting is just one insert), expensive reads (gotta gather + sort
  every time).

**Fan-out on WRITE (push):**
- When you post, immediately push that tweet into the precomputed timeline of every
  follower.
- Expensive writes, but reads are instant (timeline is already built).

**The hybrid (what Twitter actually does-ish):**
- Use fan-out on write for normal users (fast feeds).
- For **celebrities** with millions of followers, fan-out on write would be insane
  (one tweet = millions of inserts). So pull their tweets at read time and merge.

That celebrity case is the whole "aha" of this problem.

## Data model (rough)
```
Users(user_id, name, ...)
Follows(follower_id, followee_id)
Tweets(tweet_id, user_id, text, created_at)
Timeline cache: user_id -> list of recent tweet_ids   (Redis)
```

## Flow
**Post tweet:** save tweet → push tweet_id into followers' cached timelines (skip
for celebrities).
**Read timeline:** read your cached timeline → merge in any celebrity tweets you
follow → return sorted by time.

## Scaling
- **Cache** timelines in Redis (lists of tweet_ids).
- **Sharding** — shard tweets / timelines by user_id.
- **Message queue** for the fan-out work so posting feels instant and the pushing
  happens async.
- Media (images/video) goes to object storage + CDN, not the main DB.

## Gotchas
- Ordering across shards / clocks.
- A user with 0 cached timeline (new or inactive) → fall back to pull.
- Eventual consistency: it's fine if a tweet shows up in your feed a second late.
