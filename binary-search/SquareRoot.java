/*
 * Problem: Square Root (integer sqrt)
 *
 * Return floor(sqrt(x)) without using Math.sqrt. So sqrt(8) = 2 (since 2*2=4 <= 8
 * but 3*3=9 > 8).
 *
 * Example:
 * Input: 8
 * Output: 2
 *
 * Approach: binary search on the answer between 1 and x. for a candidate mid,
 *           check if mid*mid <= x. if yes, mid might be the answer so remember it
 *           and search higher; if mid*mid > x, search lower. use long for mid*mid
 *           so it doesn't overflow int (that bug got me).
 *
 * Time: O(log x)
 * Space: O(1)
 *
 * Topics: binary search on answer, math
 */
public class SquareRoot {

    static int solve(int x) {
        if (x < 2) return x;
        long lo = 1, hi = x, ans = 1;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (mid * mid <= x) {
                ans = mid;       // mid works, but maybe a bigger one does too
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(8));  // expected: 2
        System.out.println("Test 2: " + solve(4));  // expected: 2
        System.out.println("Test 3: " + solve(1));  // expected: 1
        System.out.println("Test 4: " + solve(2147395600)); // expected: 46340
    }
}
