/*
 * Problem: Largest Rectangle in Histogram
 *
 * Given bar heights of width 1, find the area of the biggest rectangle that fits
 * inside the histogram.
 *
 * Example:
 * Input: [2,1,5,6,2,3]
 * Output: 10   (the 5 and 6 bars -> 5*2=10)
 *
 * Approach: for each bar, the biggest rectangle using it as the height stretches
 *           left and right until it hits a shorter bar. naive way scans both ways
 *           = O(n^2). the stack trick: keep a stack of bar indices with increasing
 *           heights. when a shorter bar appears, pop taller bars and compute their
 *           rectangle (the popped bar's height * width between the new boundaries).
 *           took me a while, watch a visualization for the width math.
 *
 * Time: O(n)
 * Space: O(n)
 *
 * Topics: stack, monotonic stack
 */

import java.util.Stack;

public class LargestRectangleInHistogram {

    static int solve(int[] heights) {
        Stack<Integer> stack = new Stack<>();  // holds indices, heights increasing
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int curHeight = (i == n) ? 0 : heights[i];  // 0 at end flushes the stack
            while (!stack.isEmpty() && heights[stack.peek()] > curHeight) {
                int h = heights[stack.pop()];
                // width: from one past the new top of stack, up to i-1
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{2,1,5,6,2,3})); // expected: 10
        System.out.println("Test 2: " + solve(new int[]{2,4}));         // expected: 4
        System.out.println("Test 3: " + solve(new int[]{2,1,2}));       // expected: 3
    }
}
