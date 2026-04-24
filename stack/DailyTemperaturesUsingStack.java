import java.util.Stack;

/*
 * Problem: Daily Temperatures
 * Source: LeetCode
 * Difficulty: Medium
 *
 * Approach:
 * Maintain indices in decreasing temperature order and resolve warmer days as they appear.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class DailyTemperaturesUsingStack {

    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] answer = dailyTemperatures(temperatures);

        for (int value : answer) {
            System.out.print(value + " ");
        }
    }

    static int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                answer[index] = i - index;
            }

            stack.push(i);
        }

        return answer;
    }
}
