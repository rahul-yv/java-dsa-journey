/*
 * Problem: Power of a Number
 * Source: Practice
 * Difficulty: Easy
 *
 * Approach:
 * Recursively multiply the base until the exponent reaches zero.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class PowerOfNumberUsingRecursion {

    public static void main(String[] args) {
        int base = 2;
        int exponent = 5;
        System.out.println(power(base, exponent));
    }

    static int power(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        }

        return base * power(base, exponent - 1);
    }
}
