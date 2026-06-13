/*
 * Problem: Stock Span Problem
 *
 * For each day, the "span" is how many consecutive days (including today) the
 * price was <= today's price, going backwards.
 *
 * Example:
 * Input: [100, 80, 60, 70, 60, 75, 85]
 * Output: [1, 1, 1, 2, 1, 4, 6]
 *
 * Approach: for each day we want the most recent earlier day with a HIGHER price;
 *           the span is the gap to it. a monotonic stack of indices (decreasing
 *           prices) does this in one pass. pop all days with price <= today, the
 *           index left on top is the higher-price day. span = today's index minus
 *           that index (or index+1 if stack empties).
 *
 * Time: O(n)
 * Space: O(n)
 *
 * Topics: stack, monotonic stack
 */

import java.util.Arrays;
import java.util.Stack;

public class StockSpanProblem {

    static int[] solve(int[] price) {
        int n = price.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>();  // indices of days, decreasing prices

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && price[stack.peek()] <= price[i]) {
                stack.pop();
            }
            span[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());
            stack.push(i);
        }
        return span;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + Arrays.toString(solve(new int[]{100,80,60,70,60,75,85})));
        // expected: [1, 1, 1, 2, 1, 4, 6]
        System.out.println("Test 2: " + Arrays.toString(solve(new int[]{10,4,5,90,120,80})));
        // expected: [1, 1, 2, 4, 5, 1]
    }
}
