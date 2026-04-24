import java.util.Stack;

/*
 * Problem: Min Stack
 * Source: LeetCode
 * Difficulty: Medium
 *
 * Approach:
 * Store values in one stack and the current minimum in another stack.
 *
 * Time Complexity: O(1) per operation
 * Space Complexity: O(n)
 */
public class MinStackDemo {

    public static void main(String[] args) {
        SimpleMinStack stack = new SimpleMinStack();
        stack.push(5);
        stack.push(2);
        stack.push(7);
        stack.push(1);

        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.getMin());
    }
}

class SimpleMinStack {
    private final Stack<Integer> values = new Stack<>();
    private final Stack<Integer> minimums = new Stack<>();

    void push(int value) {
        values.push(value);

        if (minimums.isEmpty() || value <= minimums.peek()) {
            minimums.push(value);
        }
    }

    void pop() {
        int removed = values.pop();

        if (removed == minimums.peek()) {
            minimums.pop();
        }
    }

    int top() {
        return values.peek();
    }

    int getMin() {
        return minimums.peek();
    }
}
