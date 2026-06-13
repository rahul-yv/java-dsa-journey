/*
 * Problem: Sort a Stack (using recursion)
 *
 * Sort a stack so the largest element ends up on top, using only stack
 * operations (push, pop, peek, isEmpty) - no extra array.
 *
 * Example:
 * Input (top -> bottom): [34, 3, 31, 98, 92, 23]
 * Output (top -> bottom): [98, 92, 34, 31, 23, 3]
 *
 * Approach: recursion. pop the top, sort the smaller remaining stack, then insert
 *           the popped element back into its sorted position. "insert sorted"
 *           itself is recursive: if the element is bigger than the top, pop the
 *           top, insert deeper, then put the top back. kind of like insertion
 *           sort but on a stack. mind-bending the first time.
 *
 * Time: O(n^2)
 * Space: O(n) recursion stack
 *
 * Topics: stack, recursion
 */

import java.util.Stack;

public class SortAStack {

    static void sort(Stack<Integer> s) {
        if (s.isEmpty()) return;
        int top = s.pop();
        sort(s);                  // sort the rest
        insertSorted(s, top);     // put top back in the right place
    }

    static void insertSorted(Stack<Integer> s, int val) {
        if (s.isEmpty() || s.peek() <= val) {
            s.push(val);
            return;
        }
        int top = s.pop();        // top is bigger, set it aside
        insertSorted(s, val);     // insert deeper
        s.push(top);              // restore
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        for (int x : new int[]{23, 92, 98, 31, 3, 34}) s.push(x); // 34 on top
        sort(s);
        System.out.println("Test 1 (top->bottom): " + s); // expected bottom->top: [3, 23, 31, 34, 92, 98]
    }
}
