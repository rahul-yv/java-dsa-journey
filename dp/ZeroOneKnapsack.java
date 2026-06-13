/*
 * Problem: 0/1 Knapsack
 *
 * Given items with weights and values, and a bag that can carry total weight W,
 * maximize the value you can carry. Each item is either taken or not (0/1) - no
 * splitting, no taking twice.
 *
 * Example:
 * weights = [1,3,4,5], values = [1,4,5,7], W = 7
 * Output: 9   (items with weight 3 and 4 -> value 4 + 5)
 *
 * Approach: the textbook DP. dp[i][w] = best value using the first i items with a
 *           weight budget of w. for each item you either:
 *             - skip it: dp[i-1][w]
 *             - take it (if it fits): values[i-1] + dp[i-1][w - weights[i-1]]
 *           take the max. "take or skip, look at the row above" - same shape as
 *           subset-sum and partition problems.
 *
 * Time: O(n*W)
 * Space: O(n*W)
 *
 * Topics: dp, knapsack, 2D table
 */
public class ZeroOneKnapsack {

    static int solve(int[] weights, int[] values, int W) {
        int n = weights.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                dp[i][w] = dp[i - 1][w];   // skip item i
                if (weights[i - 1] <= w) { // item fits -> consider taking it
                    dp[i][w] = Math.max(dp[i][w],
                                        values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                }
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{1,3,4,5}, new int[]{1,4,5,7}, 7)); // expected: 9
        System.out.println("Test 2: " + solve(new int[]{2,3,4}, new int[]{4,5,6}, 5));     // expected: 9
    }
}
