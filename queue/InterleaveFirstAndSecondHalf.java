/*
 * Problem: Interleave the First and Second Half of a Queue
 *
 * Given a queue with an even number of elements, rearrange it so the first half
 * and second half are interleaved.
 *
 * Example:
 * Input:  [1, 2, 3, 4, 5, 6]
 * Output: [1, 4, 2, 5, 3, 6]
 *
 * Approach: dequeue the first half into a temporary stack... actually a temp
 *           queue is cleaner here. push first half into a helper queue, then
 *           alternate: take one from the helper (first half) and one from the
 *           original (second half), pushing both to the back. classic use of an
 *           auxiliary queue.
 *
 * Time: O(n)
 * Space: O(n)
 *
 * Topics: queue, simulation
 */

import java.util.LinkedList;
import java.util.Queue;

public class InterleaveFirstAndSecondHalf {

    static Queue<Integer> solve(Queue<Integer> q) {
        int n = q.size();
        Queue<Integer> firstHalf = new LinkedList<>();

        // move first half out
        for (int i = 0; i < n / 2; i++) firstHalf.add(q.poll());

        // interleave: one from first half, one from what's left (second half)
        while (!firstHalf.isEmpty()) {
            q.add(firstHalf.poll());
            q.add(q.poll());          // rotate the front (a second-half element) to back
        }
        return q;
    }

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        for (int x : new int[]{1,2,3,4,5,6}) q.add(x);
        System.out.println("Test 1: " + solve(q)); // expected: [1, 4, 2, 5, 3, 6]

        Queue<Integer> q2 = new LinkedList<>();
        for (int x : new int[]{11,12,13,14}) q2.add(x);
        System.out.println("Test 2: " + solve(q2)); // expected: [11, 13, 12, 14]
    }
}
