/*
 * Problem: Matrix Chain Multiplication
 *
 * Given a chain of matrices, find the cheapest way to parenthesize the product
 * (the order of multiplication that needs the fewest scalar multiplications).
 * dims[] gives matrix i as dims[i-1] x dims[i].
 *
 * Example:
 * dims = [40, 20, 30, 10, 30]  (4 matrices)
 * Output: 26000
 *
 * Approach: interval DP. dp[i][j] = min cost to multiply matrices i..j. try every
 *           split point k between i and j: cost = dp[i][k] + dp[k+1][j] + cost of
 *           multiplying the two resulting blocks (dims[i-1]*dims[k]*dims[j]). take
 *           the min over all k. we fill by increasing chain length so the smaller
 *           subproblems are ready. this "try every split point" idea is the core
 *           of interval DP (same family as burst balloons).
 *
 * Time: O(n^3)
 * Space: O(n^2)
 *
 * Topics: dp, interval dp
 */
public class MatrixChainMultiplication {

    static int solve(int[] dims) {
        int n = dims.length - 1;   // number of matrices
        int[][] dp = new int[n + 1][n + 1];

        for (int len = 2; len <= n; len++) {            // chain length
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {           // split between k and k+1
                    int cost = dp[i][k] + dp[k + 1][j]
                             + dims[i - 1] * dims[k] * dims[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{40,20,30,10,30})); // expected: 26000
        System.out.println("Test 2: " + solve(new int[]{1,2,3,4}));         // expected: 18
        System.out.println("Test 3: " + solve(new int[]{10,20,30}));        // expected: 6000
    }
}
