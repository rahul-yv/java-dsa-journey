/*
 * Problem: Power of Three  (LeetCode #326 - Easy)
 *
 * Return true if n is a power of three (1, 3, 9, 27, ...).
 *
 * Example:
 * Input: 27
 * Output: true
 * Input: 0
 * Output: false
 *
 * Approach: the straightforward way: keep dividing by 3 while it divides evenly.
 *           if you end at exactly 1, it was a power of three. handle n <= 0 up front
 *           (those are never powers of three). there's a cute one-liner using the
 *           biggest int power of three (1162261467 % n == 0) but the loop is clearer
 *           for learning.
 *
 * Time: O(log_3 n)
 * Space: O(1)
 *
 * Topics: math
 */
public class PowerOfThree {

    static boolean solve(int n) {
        if (n <= 0) return false;
        while (n % 3 == 0) n /= 3;   // strip out factors of 3
        return n == 1;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(27)); // expected: true
        System.out.println("Test 2: " + solve(0));  // expected: false
        System.out.println("Test 3: " + solve(9));  // expected: true
        System.out.println("Test 4: " + solve(45)); // expected: false
    }
}
