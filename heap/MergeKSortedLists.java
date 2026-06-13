/*
 * Problem: Merge k Sorted Lists
 *
 * Given k sorted linked lists, merge them into one sorted list.
 *
 * Example:
 * Input: [[1,4,5],[1,3,4],[2,6]]
 * Output: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
 *
 * Approach: put the head of every list into a min-heap (ordered by node value).
 *           repeatedly pop the smallest node, append it to the result, and push
 *           that node's next (if any) back into the heap. the heap always holds at
 *           most k nodes - one "frontier" node per list - so we always pick the
 *           global smallest in O(log k).
 *
 * Time: O(N log k)  N = total nodes
 * Space: O(k)
 *
 * Topics: heap, priority queue, linked list, merge
 */

import java.util.PriorityQueue;

public class MergeKSortedLists {

    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    static Node mergeK(Node[] lists) {
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (Node head : lists) if (head != null) heap.offer(head);

        Node dummy = new Node(0), tail = dummy;
        while (!heap.isEmpty()) {
            Node smallest = heap.poll();
            tail.next = smallest;
            tail = tail.next;
            if (smallest.next != null) heap.offer(smallest.next); // refill from that list
        }
        return dummy.next;
    }

    static Node build(int[] vals) {
        Node dummy = new Node(0), tail = dummy;
        for (int v : vals) { tail.next = new Node(v); tail = tail.next; }
        return dummy.next;
    }

    public static void main(String[] args) {
        Node[] lists = {
            build(new int[]{1,4,5}),
            build(new int[]{1,3,4}),
            build(new int[]{2,6})
        };
        Node merged = mergeK(lists);
        StringBuilder sb = new StringBuilder();
        while (merged != null) { sb.append(merged.val); if (merged.next != null) sb.append(" -> "); merged = merged.next; }
        System.out.println("Test 1: " + sb); // expected: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
    }
}
