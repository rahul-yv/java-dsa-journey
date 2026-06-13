/*
 * Longest Common Subsequence is really a DP problem, so I keep the full
 * worked-out solution in the dp folder to avoid having two copies that drift.
 *
 * See: ../dp/LongestCommonSubsequence.java   (LeetCode #1143 - Medium)
 *
 * Quick idea: build a 2D table dp[i][j] = LCS length of first i chars of a and
 * first j chars of b. if chars match -> 1 + diagonal, else max(top, left).
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        System.out.println("full solution lives in dp/LongestCommonSubsequence.java");
    }
}
