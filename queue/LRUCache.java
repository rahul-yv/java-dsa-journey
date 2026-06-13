/*
 * Problem: LRU Cache
 *
 * Design a cache with a fixed capacity. get(key) and put(key, value) must both
 * be O(1). When it's full and a new key comes in, evict the Least Recently Used
 * entry.
 *
 * Example:
 * capacity = 2
 * put(1,1); put(2,2); get(1)->1; put(3,3) evicts key 2; get(2)->-1
 *
 * Approach: the slick "from scratch" way uses a hashmap + doubly linked list (see
 *           linked-list/LRUCacheUsingLinkedList.java). here I use Java's built-in
 *           LinkedHashMap in access-order mode and just override removeEldestEntry,
 *           which is the practical/interview-shortcut version. both are valid.
 *
 * Time: O(1) get and put
 * Space: O(capacity)
 *
 * Topics: hashmap, linked list, design
 */

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    private final LinkedHashMap<Integer, Integer> map;

    LRUCache(int capacity) {
        // accessOrder=true -> most recently accessed moves to the end
        map = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;   // auto-evict the LRU entry
            }
        };
    }

    int get(int key) {
        return map.getOrDefault(key, -1);
    }

    void put(int key, int value) {
        map.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
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
