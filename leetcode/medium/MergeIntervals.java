/*
 * Problem: Merge Intervals  (LeetCode #56 - Medium)
 *
 * Merge all overlapping intervals.
 *
 * Example:
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 *
 * Approach: sort the intervals by start time. then sweep through: keep the "current"
 *           interval, and for each next one, if it starts before/at the current
 *           interval's end, they overlap so extend the current end to the max of the
 *           two ends. otherwise the current interval is done - push it and start a
 *           new current. sorting first is what makes the single pass possible.
 *
 * Time: O(n log n)
 * Space: O(n)
 *
 * Topics: intervals, sorting
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    static int[][] solve(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);   // by start
        List<int[]> merged = new ArrayList<>();

        int[] cur = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= cur[1]) {             // overlap
                cur[1] = Math.max(cur[1], intervals[i][1]);
            } else {
                merged.add(cur);                          // no overlap, lock it in
                cur = intervals[i];
            }
        }
        merged.add(cur);
        return merged.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + Arrays.deepToString(solve(new int[][]{{1,3},{2,6},{8,10},{15,18}})));
        // expected: [[1, 6], [8, 10], [15, 18]]
        System.out.println("Test 2: " + Arrays.deepToString(solve(new int[][]{{1,4},{4,5}})));
        // expected: [[1, 5]]
    }
}
