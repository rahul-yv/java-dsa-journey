import java.util.LinkedList;
import java.util.Queue;

/*
 * Problem: First Negative Number in Every Window of Size K
 * Source: Practice
 * Difficulty: Medium
 *
 * Approach:
 * Keep indices of negative numbers in the current window and remove expired ones.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(k)
 */
public class FirstNegativeNumberInEveryWindow {

    public static void main(String[] args) {
        int[] numbers = {12, -1, -7, 8, -15, 30, 16, 28};
        int windowSize = 3;
        printFirstNegatives(numbers, windowSize);
    }

    static void printFirstNegatives(int[] numbers, int windowSize) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 0) {
                queue.offer(i);
            }

            if (!queue.isEmpty() && queue.peek() <= i - windowSize) {
                queue.poll();
            }

            if (i >= windowSize - 1) {
                if (queue.isEmpty()) {
                    System.out.print("0 ");
                } else {
                    System.out.print(numbers[queue.peek()] + " ");
                }
            }
        }
    }
}
