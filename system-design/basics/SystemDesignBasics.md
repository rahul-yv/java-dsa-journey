# System Design Basics (my notes)

Just my own notes so I stop forgetting this stuff. Not a textbook, more like what
I'd scribble before an interview. System design is mostly far off for me (I'm
doing DSA first) but good to have the vocabulary.

## CAP Theorem

In a distributed system you can only fully guarantee 2 of these 3 when a network
partition happens:

- **C — Consistency**: every read sees the latest write (everyone sees the same data).
- **A — Availability**: every request gets a response (maybe not the newest data).
- **P — Partition tolerance**: the system keeps working even if the network between
  nodes breaks.

The catch: network partitions WILL happen in real distributed systems, so P isn't
really optional. So in practice you're choosing **C or A** during a partition.
- CP system: refuse/error rather than serve stale data (e.g. banks, MongoDB-ish).
- AP system: keep serving, maybe stale, fix it up later (e.g. Cassandra, DNS).

Way I remember it: "during a network split, do you want to be RIGHT (C) or do you
want to ANSWER (A)?"

## Load Balancing

When one server can't handle all the traffic, put a load balancer in front and
spread requests across many servers.

Common strategies:
- **Round robin**: just rotate through servers one by one.
- **Least connections**: send to whoever's least busy.
- **IP hash**: same client always hits the same server (helps with sessions).

Bonus: the load balancer also does health checks and stops sending traffic to a
dead server. It's basically a traffic cop.

## Caching

Keep frequently-used data somewhere fast (memory) so you don't hit the slow thing
(database/disk) every time. Tools: Redis, Memcached.

- **Cache hit** = found it in cache (fast). **Cache miss** = not there, go to DB.
- Where to cache: client side, CDN, in front of the DB, inside the app.
- **Eviction policies** when cache is full: LRU (least recently used — I literally
  implemented this in `linked-list/LRUCacheUsingLinkedList.java`), LFU, FIFO.
- Watch out for **stale data** — need a way to invalidate/expire (TTL).

Rule of thumb: cache the stuff that's read a lot and changes rarely.

## Database Sharding

When one DB can't hold all the data / handle the load, split it across multiple
DBs. Each shard holds a slice of the data.

- **Horizontal sharding**: split ROWS across shards (e.g. users A–M on shard 1,
  N–Z on shard 2, or by user_id hash).
- **Vertical sharding**: split by COLUMNS / tables (e.g. profile data on one DB,
  posts on another).
- Hard part: picking a good **shard key** so data spreads evenly (no "hot shard"),
  and cross-shard queries/joins become painful.

Related: **replication** = copies of the same data for reads + backup (different
from sharding, which is splitting different data).

## Message Queues

A buffer between services so they don't have to talk directly / at the same time.
Producer drops a message, consumer picks it up whenever it's ready. Tools: Kafka,
RabbitMQ, SQS.

Why bother:
- **Decoupling**: producer doesn't care who consumes or when.
- **Smooth out spikes**: queue absorbs a burst, consumers process at their pace.
- **Async work**: e.g. "send email" — don't make the user wait, queue it.

Tradeoff: you're now dealing with eventual consistency and "what if a message is
processed twice" (idempotency).

---

If I had to summarize: scaling = add more machines (load balancing) + don't redo
work (caching) + split the data when it's too big (sharding) + don't make services
wait on each other (queues).
