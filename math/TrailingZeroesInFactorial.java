/*
 * Problem: Trailing Zeroes in Factorial
 *
 * Count the number of trailing zeroes in n! (n factorial).
 *
 * Example:
 * Input: n = 5
 * Output: 1   (5! = 120, one trailing zero)
 *
 * Approach: a trailing zero comes from a factor of 10 = 2 * 5. in a factorial there
 *           are always way more factors of 2 than 5, so the number of trailing
 *           zeroes is just how many factors of 5 are in n!. count them as
 *           n/5 + n/25 + n/125 + ... (n/25 catches numbers like 25 that contribute
 *           a SECOND 5, etc). no need to actually compute the giant factorial.
 *
 * Time: O(log n)
 * Space: O(1)
 *
 * Topics: math, factorials, number theory
 */
public class TrailingZeroesInFactorial {

    static int solve(int n) {
        int count = 0;
        for (long pow = 5; pow <= n; pow *= 5) {
            count += n / pow;   // add multiples of 5, then 25, then 125 ...
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(5));   // expected: 1
        System.out.println("Test 2: " + solve(10));  // expected: 2
        System.out.println("Test 3: " + solve(25));  // expected: 6
        System.out.println("Test 4: " + solve(100)); // expected: 24
    }
}
