/*
 * Problem: Factorial
 * Source: Practice
 * Difficulty: Easy
 *
 * Approach:
 * Multiply the current number by the factorial of the previous number.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class FactorialUsingRecursion {

    public static void main(String[] args) {
        int number = 5;
        System.out.println(factorial(number));
    }

    static int factorial(int number) {
        if (number <= 1) {
            return 1;
        }

        return number * factorial(number - 1);
    }
}
