/*
 * Problem: Top K Frequent Elements
 *
 * Return the k elements that appear most often.
 *
 * Example:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1, 2]
 *
 * Approach: first count how often each number appears (hashmap). then we need the
 *           k entries with the biggest counts. a min-heap of size k ordered BY
 *           COUNT does it: push entries, pop the lowest-count one whenever the
 *           heap exceeds k. whatever's left are the top k frequent. (a bucket-sort
 *           approach can hit O(n), but the heap is the cleanest to remember.)
 *
 * Time: O(n log k)
 * Space: O(n)
 *
 * Topics: heap, hashmap, priority queue
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {

    static int[] solve(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : nums) count.merge(n, 1, Integer::sum);

        // min-heap by frequency, keep only the top k
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [value, freq]
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            heap.offer(new int[]{e.getKey(), e.getValue()});
            if (heap.size() > k) heap.poll();
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) res[i] = heap.poll()[0];
        return res;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + java.util.Arrays.toString(solve(new int[]{1,1,1,2,2,3}, 2)));
        // expected (order may vary): [2, 1]
        System.out.println("Test 2: " + java.util.Arrays.toString(solve(new int[]{1}, 1))); // expected: [1]
    }
}
