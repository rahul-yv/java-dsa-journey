# Caching (notes)

Idea: keep a copy of frequently-needed data somewhere FAST so you don't keep
asking the slow source (DB, disk, another service). Usually the cache lives in
memory (RAM), e.g. Redis or Memcached.

## Hit vs miss
- **Cache hit** — data was in the cache. fast, done.
- **Cache miss** — not in cache, go fetch from the DB, (usually) store it in the
  cache, then return it. next time it's a hit.

## Where you can cache
- **Browser / client** — local copy, no network at all.
- **CDN** — caches static stuff (images, JS, CSS) near the user geographically.
- **Application cache** — in front of the DB, the classic Redis layer.
- **Database cache** — the DB caches query results itself.

## Write strategies (how cache + DB stay in sync)
- **Write-through** — write to cache AND DB at the same time. safe, a bit slower.
- **Write-back** — write to cache now, flush to DB later. fast, but risky if cache
  dies before flushing.
- **Write-around** — write straight to DB, skip the cache (cache fills on reads).

## Eviction (cache is full, what do we drop?)
- **LRU** — Least Recently Used. drop whatever hasn't been touched longest. (I
  built this from scratch in `linked-list/LRUCacheUsingLinkedList.java`.)
- **LFU** — Least Frequently Used. drop the least-accessed item.
- **FIFO** — drop the oldest inserted.

## The hard part: stale data
The cache can hold old data after the DB changes. Fixes:
- **TTL** (time to live) — entries auto-expire after N seconds.
- **Invalidate on write** — when you update the DB, delete/update the cache entry.

> "There are only two hard things in CS: cache invalidation and naming things."
> ...yeah I get it now.

## When to cache
Read-heavy data that doesn't change often. Don't cache stuff that's written
constantly or must always be perfectly fresh.
