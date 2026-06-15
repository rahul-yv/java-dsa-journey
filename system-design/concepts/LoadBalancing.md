# Load Balancing (notes)

The whole point: one server can only handle so much. Put a load balancer (LB) in
front of a pool of servers and spread requests so no single box gets crushed.

## Where it sits
```
        clients
           |
     [ load balancer ]
       /     |     \
   server  server  server
```

## How it decides where to send a request
- **Round robin** — server 1, 2, 3, 1, 2, 3... simplest, ignores how busy they are.
- **Weighted round robin** — give beefier servers more requests.
- **Least connections** — send to the one currently handling the fewest requests.
- **IP hash** — hash the client IP so the same user keeps hitting the same server
  (sticky sessions, useful if session is stored on the server).

## Layer 4 vs Layer 7
- **L4 (transport)** — routes by IP/port, doesn't look at the actual request. Fast.
- **L7 (application)** — looks at the HTTP request (URL, headers, cookies) and can
  route smarter, e.g. `/api` to one pool, `/images` to another. More flexible.

## Health checks
LB pings each server regularly. If a server stops responding, the LB takes it out
of rotation so users don't hit a dead box. Adds it back when it recovers.

## Why it matters
- **Scalability** — add more servers behind the LB to handle more traffic.
- **Availability** — one server dies, others keep serving.
- The LB itself shouldn't be a single point of failure → usually run more than one.

Quick mental model: it's the bouncer deciding which line each person joins, and
kicking out the registers that aren't working.
