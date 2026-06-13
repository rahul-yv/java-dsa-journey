/*
 * Problem: Longest Common Subsequence (LCS)
 *
 * Given two strings, find the length of the longest subsequence present in both
 * (characters in order, not necessarily contiguous).
 *
 * Example:
 * Input: a = "abcde", b = "ace"
 * Output: 3   ("ace")
 *
 * Approach: 2D DP. dp[i][j] = LCS length using the first i chars of a and first j
 *           chars of b. if the current chars match (a[i-1] == b[j-1]) then they
 *           extend the LCS: dp[i][j] = 1 + dp[i-1][j-1] (the diagonal). if they
 *           don't match, drop one char from either string and take the better:
 *           max(dp[i-1][j], dp[i][j-1]). the "match -> diagonal, else max of top/left"
 *           pattern is the template for tons of string DP problems.
 *
 * Time: O(m*n)
 * Space: O(m*n)
 *
 * Topics: dp, strings, 2D table
 */
public class LongestCommonSubsequence {

    static int solve(String a, String b) {
        int m = a.length(), n = b.length();
        int[][] dp = new int[m + 1][n + 1];   // row/col 0 = empty prefix = 0

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];          // chars match -> diagonal
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // skip a char
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve("abcde", "ace")); // expected: 3
        System.out.println("Test 2: " + solve("abc", "abc"));    // expected: 3
        System.out.println("Test 3: " + solve("abc", "def"));    // expected: 0
    }
}
