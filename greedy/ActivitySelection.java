/*
 * Problem: Activity Selection
 *
 * Given activities with start and end times, pick the MAXIMUM number that don't
 * overlap (you can only do one at a time).
 *
 * Example:
 * start = [1,3,0,5,8,5], end = [2,4,6,7,9,9]
 * Output: 4
 *
 * Approach: greedy. sort activities by their FINISH time. always grab the activity
 *           that finishes earliest among the ones that still fit (start >= last
 *           chosen finish). finishing early leaves the most room for future
 *           activities. it feels too simple but you can prove it's optimal - the
 *           classic "exchange argument" greedy.
 *
 * Time: O(n log n)  (the sort)
 * Space: O(n)
 *
 * Topics: greedy, sorting, intervals
 */

import java.util.Arrays;

public class ActivitySelection {

    static int solve(int[] start, int[] end) {
        int n = start.length;
        int[][] acts = new int[n][2];
        for (int i = 0; i < n; i++) { acts[i][0] = start[i]; acts[i][1] = end[i]; }
        Arrays.sort(acts, (a, b) -> a[1] - b[1]);   // sort by finish time

        int count = 0, lastEnd = Integer.MIN_VALUE;
        for (int[] a : acts) {
            if (a[0] >= lastEnd) {   // doesn't overlap with the last chosen one
                count++;
                lastEnd = a[1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{1,3,0,5,8,5}, new int[]{2,4,6,7,9,9})); // 4
        System.out.println("Test 2: " + solve(new int[]{10,12,20}, new int[]{20,25,30}));       // 1
    }
}
