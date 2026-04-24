import java.util.LinkedList;
import java.util.Queue;

/*
 * Problem: Generate Binary Numbers from 1 to N
 * Source: Practice
 * Difficulty: Medium
 *
 * Approach:
 * Use a queue to build the next binary strings by appending 0 and 1.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class GenerateBinaryNumbersUsingQueue {

    public static void main(String[] args) {
        generateBinaryNumbers(5);
    }

    static void generateBinaryNumbers(int count) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("1");

        for (int i = 0; i < count; i++) {
            String current = queue.poll();
            System.out.print(current + " ");
            queue.offer(current + "0");
            queue.offer(current + "1");
        }
    }
}
