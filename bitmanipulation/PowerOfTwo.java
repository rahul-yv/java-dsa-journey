/*
 * Problem: Power of Two
 *
 * Return true if a number is a power of two (1, 2, 4, 8, 16, ...).
 *
 * Example:
 * Input: 16
 * Output: true
 * Input: 6
 * Output: false
 *
 * Approach: a power of two has EXACTLY one set bit in binary (1, 10, 100, 1000...).
 *           the trick: n & (n - 1) clears the lowest set bit, so for a power of two
 *           it becomes 0. so n is a power of two iff n > 0 and (n & (n-1)) == 0.
 *           one line, no loop.
 *
 * Time: O(1)
 * Space: O(1)
 *
 * Topics: bit manipulation
 */
public class PowerOfTwo {

    static boolean solve(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(16)); // expected: true
        System.out.println("Test 2: " + solve(6));  // expected: false
        System.out.println("Test 3: " + solve(1));  // expected: true
        System.out.println("Test 4: " + solve(0));  // expected: false
    }
}
