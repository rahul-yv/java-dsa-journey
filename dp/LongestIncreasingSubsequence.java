/*
 * Problem: Longest Increasing Subsequence (LIS)
 *
 * Find the length of the longest strictly increasing subsequence (elements don't
 * have to be contiguous).
 *
 * Example:
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4   (2,3,7,101 or 2,3,7,18)
 *
 * Approach: the O(n^2) DP: dp[i] = length of the LIS that ENDS at index i. for
 *           each i, look at every earlier j; if nums[j] < nums[i], we can extend
 *           that subsequence, so dp[i] = max(dp[i], dp[j] + 1). answer is the max
 *           over all dp[i]. (there's a slicker O(n log n) version using binary
 *           search / patience sorting, worth learning later.)
 *
 * Time: O(n^2)
 * Space: O(n)
 *
 * Topics: dp, subsequence
 */

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    static int solve(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);   // each element alone is a subsequence of length 1

        int best = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            best = Math.max(best, dp[i]);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{10,9,2,5,3,7,101,18})); // expected: 4
        System.out.println("Test 2: " + solve(new int[]{0,1,0,3,2,3}));         // expected: 4
        System.out.println("Test 3: " + solve(new int[]{7,7,7,7}));             // expected: 1
    }
}
