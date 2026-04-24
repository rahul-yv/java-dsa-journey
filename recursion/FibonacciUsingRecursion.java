/*
 * Problem: Fibonacci Number
 * Source: Practice
 * Difficulty: Easy
 *
 * Approach:
 * Use the recursive relation F(n) = F(n - 1) + F(n - 2).
 *
 * Time Complexity: O(2^n)
 * Space Complexity: O(n)
 */
public class FibonacciUsingRecursion {

    public static void main(String[] args) {
        int position = 6;
        System.out.println(fibonacci(position));
    }

    static int fibonacci(int position) {
        if (position <= 1) {
            return position;
        }

        return fibonacci(position - 1) + fibonacci(position - 2);
    }
}
