/*
 * Problem: Minimum Number of Platforms
 *
 * Given train arrival and departure times, find the minimum number of platforms
 * needed so no train waits (two trains overlapping in time need separate platforms).
 *
 * Example:
 * arr = [900, 940, 950, 1100, 1500, 1800]
 * dep = [910, 1200, 1120, 1130, 1900, 2000]
 * Output: 3
 *
 * Approach: sort arrivals and departures separately. then sweep through time with
 *           two pointers: if the next event is an arrival, a train comes in
 *           (platforms++); if it's a departure, one frees up (platforms--). track
 *           the peak number of platforms in use at once - that's the answer. you
 *           don't need to match which train is which, just count overlaps.
 *
 * Time: O(n log n)
 * Space: O(1)
 *
 * Topics: greedy, sorting, two pointers, intervals
 */

import java.util.Arrays;

public class MinimumPlatforms {

    static int solve(int[] arr, int[] dep) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int platforms = 0, maxPlatforms = 0;
        int i = 0, j = 0;
        while (i < arr.length) {
            if (arr[i] <= dep[j]) {   // a train arrives before the next departs
                platforms++;
                i++;
                maxPlatforms = Math.max(maxPlatforms, platforms);
            } else {                   // a train departs, free a platform
                platforms--;
                j++;
            }
        }
        return maxPlatforms;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(
            new int[]{900,940,950,1100,1500,1800},
            new int[]{910,1200,1120,1130,1900,2000})); // expected: 3
        System.out.println("Test 2: " + solve(new int[]{900,1100,1235}, new int[]{1000,1200,1240})); // 1
    }
}
