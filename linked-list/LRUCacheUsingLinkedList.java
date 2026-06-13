/*
 * Problem: LRU Cache (built from scratch with a doubly linked list)
 *
 * Same LRU cache as queue/LRUCache.java, but here I build it the "real" way that
 * interviews want - a HashMap plus my own doubly linked list - instead of leaning
 * on LinkedHashMap.
 *
 * Example:
 * capacity = 2
 * put(1,1); put(2,2); get(1)->1; put(3,3) evicts 2; get(2)->-1
 *
 * Approach: the doubly linked list keeps entries in order of "how recently used":
 *           most recent right behind a dummy head, least recent right before a
 *           dummy tail. the HashMap gives O(1) lookup from key to its node.
 *           - get: move the node to the front (most recent).
 *           - put: add/update at front; if over capacity, drop the node before tail.
 *           dummy head & tail nodes mean no null checks at the ends. that detail
 *           makes the pointer code way cleaner.
 *
 * Time: O(1) get and put
 * Space: O(capacity)
 *
 * Topics: hashmap, doubly linked list, design
 */

import java.util.HashMap;

public class LRUCacheUsingLinkedList {

    static class Node {
        int key, val;
        Node prev, next;
        Node(int key, int val) { this.key = key; this.val = val; }
    }

    private final int capacity;
    private final HashMap<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0, 0);  // dummy
    private final Node tail = new Node(0, 0);  // dummy

    LRUCacheUsingLinkedList(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    // unlink a node from wherever it is
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // insert right after head (most recently used spot)
    private void addFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node);
        addFront(node);     // mark as most recently used
        return node.val;
    }

    void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            remove(node);
            addFront(node);
            return;
        }
        if (map.size() == capacity) {
            Node lru = tail.prev;   // least recently used
            remove(lru);
            map.remove(lru.key);
        }
        Node node = new Node(key, value);
        map.put(key, node);
        addFront(node);
    }

    public static void main(String[] args) {
        LRUCacheUsingLinkedList cache = new LRUCacheUsingLinkedList(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("get(1): " + cache.get(1)); // expected: 1
        cache.put(3, 3);                                 // evicts key 2
        System.out.println("get(2): " + cache.get(2)); // expected: -1
        cache.put(4, 4);                                 // evicts key 1
        System.out.println("get(1): " + cache.get(1)); // expected: -1
        System.out.println("get(3): " + cache.get(3)); // expected: 3
        System.out.println("get(4): " + cache.get(4)); // expected: 4
    }
}
