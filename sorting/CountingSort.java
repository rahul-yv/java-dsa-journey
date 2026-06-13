/*
 * Problem: Counting Sort
 *
 * Sort an array of non-negative integers without comparing them, by counting
 * how many times each value appears.
 *
 * Example:
 * Input:  [4, 2, 2, 8, 3, 3, 1]
 * Output: [1, 2, 2, 3, 3, 4, 8]
 *
 * Approach: not a comparison sort at all. find the max value, make a count array
 *           of that size, tally how many of each number there are, then read the
 *           counts back out in order. super fast when the numbers are in a small
 *           range, but useless if values are huge (count array gets giant).
 *
 * Time: O(n + k)   k = max value
 * Space: O(k)
 *
 * Topics: sorting, counting, non-comparison
 */

import java.util.Arrays;

public class CountingSort {

    static int[] sort(int[] a) {
        if (a.length == 0) return a;
        int max = a[0];
        for (int x : a) max = Math.max(max, x);

        int[] count = new int[max + 1];
        for (int x : a) count[x]++;        // tally occurrences

        int idx = 0;
        for (int v = 0; v <= max; v++) {
            while (count[v]-- > 0) a[idx++] = v;  // write each value count[v] times
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + Arrays.toString(sort(new int[]{4,2,2,8,3,3,1}))); // [1, 2, 2, 3, 3, 4, 8]
        System.out.println("Test 2: " + Arrays.toString(sort(new int[]{0,0,1})));         // [0, 0, 1]
        System.out.println("Test 3: " + Arrays.toString(sort(new int[]{5})));             // [5]
    }
}
