import java.util.Stack;

/*
 * Problem: Implement Queue Using Stacks
 * Source: LeetCode
 * Difficulty: Easy
 *
 * Approach:
 * Move elements to the output stack only when needed for dequeue or peek.
 *
 * Time Complexity: Amortized O(1)
 * Space Complexity: O(n)
 */
public class QueueUsingTwoStacks {

    public static void main(String[] args) {
        StackBasedQueue queue = new StackBasedQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);

        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }
}

class StackBasedQueue {
    private final Stack<Integer> input = new Stack<>();
    private final Stack<Integer> output = new Stack<>();

    void push(int value) {
        input.push(value);
    }

    int pop() {
        shiftStacks();
        return output.pop();
    }

    int peek() {
        shiftStacks();
        return output.peek();
    }

    boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

    private void shiftStacks() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
    }
}
