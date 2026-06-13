/*
 * Problem: Partition Equal Subset Sum
 *
 * Can the array be split into two subsets with EQUAL sums?
 *
 * Example:
 * Input: [1,5,11,5]
 * Output: true   ([1,5,5] and [11])
 *
 * Approach: if the total sum is odd, impossible (can't halve it). otherwise the
 *           question becomes "is there a subset that sums to total/2?" - which is
 *           exactly subset sum. I use the 1D space-optimized boolean DP: dp[s] =
 *           can we make sum s. for each number, update s from high to low (going
 *           backwards so we don't reuse the same number twice in one pass - that
 *           direction detail is important).
 *
 * Time: O(n * sum)
 * Space: O(sum)
 *
 * Topics: dp, subset sum, knapsack family
 */
public class PartitionEqualSubsetSum {

    static boolean solve(int[] nums) {
        int total = 0;
        for (int n : nums) total += n;
        if (total % 2 != 0) return false;   // can't split an odd total evenly

        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int s = target; s >= num; s--) {   // backwards = each num used once
                dp[s] = dp[s] || dp[s - num];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{1,5,11,5})); // expected: true
        System.out.println("Test 2: " + solve(new int[]{1,2,3,5}));  // expected: false
        System.out.println("Test 3: " + solve(new int[]{1,1}));      // expected: true
    }
}
