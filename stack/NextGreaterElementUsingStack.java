import java.util.Stack;

/*
 * Problem: Next Greater Element
 * Source: Practice
 * Difficulty: Easy
 *
 * Approach:
 * Use a stack of indices and resolve answers when a greater element is found.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class NextGreaterElementUsingStack {

    public static void main(String[] args) {
        int[] numbers = {4, 5, 2, 25};
        int[] answer = nextGreater(numbers);

        for (int value : answer) {
            System.out.print(value + " ");
        }
    }

    static int[] nextGreater(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < numbers.length; i++) {
            answer[i] = -1;
        }

        for (int i = 0; i < numbers.length; i++) {
            while (!stack.isEmpty() && numbers[i] > numbers[stack.peek()]) {
                answer[stack.pop()] = numbers[i];
            }

            stack.push(i);
        }

        return answer;
    }
}
