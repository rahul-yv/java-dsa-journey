/*
 * Problem: Fibonacci with DP (memoization + tabulation)
 *
 * Compute the nth Fibonacci number. The point here is to SEE the two DP styles
 * side by side, because every DP problem is one or the other.
 *
 * Example:
 * Input: n = 10
 * Output: 55
 *
 * Approach:
 *   - plain recursion fib(n) = fib(n-1) + fib(n-2) is O(2^n) because it recomputes
 *     the same values a zillion times.
 *   - MEMOIZATION (top-down): same recursion but cache each answer in an array so
 *     each fib(i) is computed once. O(n).
 *   - TABULATION (bottom-up): fill an array from 0 up to n in a loop, no recursion.
 *     also O(n) and usually a bit faster / no stack.
 *
 * Time: O(n)
 * Space: O(n)
 *
 * Topics: dp, memoization, tabulation
 */

import java.util.Arrays;

public class FibonacciDP {

    // top-down with a memo cache
    static long memo(int n, long[] cache) {
        if (n <= 1) return n;
        if (cache[n] != -1) return cache[n];        // already computed
        cache[n] = memo(n - 1, cache) + memo(n - 2, cache);
        return cache[n];
    }

    static long fibMemo(int n) {
        long[] cache = new long[n + 1];
        Arrays.fill(cache, -1);
        return memo(n, cache);
    }

    // bottom-up tabulation
    static long fibTab(int n) {
        if (n <= 1) return n;
        long[] dp = new long[n + 1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++) dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println("memo(10): " + fibMemo(10)); // expected: 55
        System.out.println("tab(10): " + fibTab(10));    // expected: 55
        System.out.println("memo(20): " + fibMemo(20)); // expected: 6765
        System.out.println("tab(20): " + fibTab(20));    // expected: 6765
    }
}
