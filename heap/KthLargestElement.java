/*
 * Problem: Kth Largest Element in an Array
 *
 * Find the kth largest element (not the kth distinct - just by value/position in
 * sorted order).
 *
 * Example:
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 *
 * Approach: sorting works (O(n log n)) but the heap way is the lesson here. keep a
 *           MIN-heap of size k. push elements; whenever it grows past k, pop the
 *           smallest. after processing everything the heap holds the k largest
 *           values, and its root (the min of those) is exactly the kth largest.
 *           feels backwards to use a min-heap for "largest" but that's the trick.
 *
 * Time: O(n log k)
 * Space: O(k)
 *
 * Topics: heap, priority queue
 */

import java.util.PriorityQueue;

public class KthLargestElement {

    static int solve(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();  // min-heap by default
        for (int n : nums) {
            minHeap.offer(n);
            if (minHeap.size() > k) minHeap.poll();  // drop the smallest, keep top k
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{3,2,1,5,6,4}, 2));       // expected: 5
        System.out.println("Test 2: " + solve(new int[]{3,2,3,1,2,4,5,5,6}, 4)); // expected: 4
        System.out.println("Test 3: " + solve(new int[]{1}, 1));                 // expected: 1
    }
}
