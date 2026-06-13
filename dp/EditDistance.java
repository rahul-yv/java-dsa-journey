/*
 * Problem: Edit Distance (Levenshtein)
 *
 * Minimum number of single-character edits (insert, delete, replace) to turn word1
 * into word2.
 *
 * Example:
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3   (horse -> rorse -> rose -> ros)
 *
 * Approach: 2D DP. dp[i][j] = edits to turn first i chars of word1 into first j of
 *           word2. base cases: turning something into "" costs i deletes; building
 *           from "" costs j inserts. if chars match, no new cost: dp[i-1][j-1].
 *           else 1 + min of the three operations:
 *             - replace: dp[i-1][j-1]
 *             - delete:  dp[i-1][j]
 *             - insert:  dp[i][j-1]
 *           figuring out which neighbour cell maps to which operation is the part
 *           to slow down on.
 *
 * Time: O(m*n)
 * Space: O(m*n)
 *
 * Topics: dp, strings, 2D table
 */
public class EditDistance {

    static int solve(String w1, String w2) {
        int m = w1.length(), n = w2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) dp[i][0] = i;   // delete all of w1
        for (int j = 0; j <= n; j++) dp[0][j] = j;   // insert all of w2

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];     // chars match, free
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],            // replace
                                   Math.min(dp[i - 1][j], dp[i][j - 1])); // delete / insert
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve("horse", "ros"));        // expected: 3
        System.out.println("Test 2: " + solve("intention", "execution")); // expected: 5
        System.out.println("Test 3: " + solve("", "abc"));              // expected: 3
    }
}
