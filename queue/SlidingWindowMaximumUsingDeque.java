import java.util.ArrayDeque;
import java.util.Deque;

/*
 * Problem: Sliding Window Maximum
 * Source: LeetCode
 * Difficulty: Hard
 *
 * Approach:
 * Keep indices in decreasing order so the front always stores the current maximum.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(k)
 */
public class SlidingWindowMaximumUsingDeque {

    public static void main(String[] args) {
        int[] numbers = {1, 3, -1, -3, 5, 3, 6, 7};
        int windowSize = 3;
        int[] answer = maxSlidingWindow(numbers, windowSize);

        for (int value : answer) {
            System.out.print(value + " ");
        }
    }

    static int[] maxSlidingWindow(int[] numbers, int windowSize) {
        int[] answer = new int[numbers.length - windowSize + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < numbers.length; i++) {
            while (!deque.isEmpty() && deque.peekFirst() <= i - windowSize) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && numbers[deque.peekLast()] <= numbers[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            if (i >= windowSize - 1) {
                answer[i - windowSize + 1] = numbers[deque.peekFirst()];
            }
        }

        return answer;
    }
}
