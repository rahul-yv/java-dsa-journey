/*
 * Problem: Climbing Stairs
 *
 * You can climb 1 or 2 steps at a time. How many distinct ways to reach the top
 * of n stairs?
 *
 * Example:
 * Input: n = 3
 * Output: 3   (1+1+1, 1+2, 2+1)
 *
 * Approach: to reach step n you either came from step n-1 (one step) or step n-2
 *           (two steps). so ways(n) = ways(n-1) + ways(n-2)... which is just
 *           Fibonacci! this is the "hello world" of DP. I keep two running values
 *           instead of an array since we only ever look back two steps.
 *
 * Time: O(n)
 * Space: O(1)
 *
 * Topics: dp, fibonacci, bottom-up
 */
public class ClimbingStairs {

    static int solve(int n) {
        if (n <= 2) return n;
        int oneBack = 2, twoBack = 1;   // ways to reach step 2 and step 1
        for (int i = 3; i <= n; i++) {
            int cur = oneBack + twoBack;
            twoBack = oneBack;
            oneBack = cur;
        }
        return oneBack;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(2)); // expected: 2
        System.out.println("Test 2: " + solve(3)); // expected: 3
        System.out.println("Test 3: " + solve(5)); // expected: 8
    }
}
