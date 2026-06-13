/*
 * Problem: Longest Palindromic Subsequence
 *
 * Find the length of the longest subsequence of s that is a palindrome (chars in
 * order, not necessarily contiguous).
 *
 * Example:
 * Input: "bbbab"
 * Output: 4   ("bbbb")
 *
 * Approach: neat trick - the longest palindromic SUBSEQUENCE of s is just the LCS
 *           (longest common subsequence) of s and its REVERSE. so I reuse the LCS
 *           DP. alternatively you can do interval DP directly: dp[i][j] = LPS in
 *           s[i..j]; if ends match dp[i][j] = 2 + dp[i+1][j-1], else max of dropping
 *           one end. I'll do the direct interval version here so it's self-contained.
 *
 * Time: O(n^2)
 * Space: O(n^2)
 *
 * Topics: dp, strings, interval dp
 */
public class LongestPalindromicSubsequence {

    static int solve(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = 1;   // single char is a palindrome of length 1

        // build by increasing substring length
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + (len == 2 ? 0 : dp[i + 1][j - 1]);
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve("bbbab")); // expected: 4
        System.out.println("Test 2: " + solve("cbbd"));  // expected: 2
        System.out.println("Test 3: " + solve("a"));     // expected: 1
    }
}
