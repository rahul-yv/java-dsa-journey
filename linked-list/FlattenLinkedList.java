/*
 * Problem: Flatten a Multilevel Linked List
 *
 * Each node has a "next" pointer (to the right) and a "down" pointer (to a sorted
 * sub-list). Every sub-list is sorted. Flatten everything into one single sorted
 * list using only the down pointers.
 *
 * Example:
 * 3 -> 7 -> 10 -> 22
 * |    |     |     |
 * 8    11    20    35
 * |          |
 * 31         28
 * Output (down-linked): 3 8 11 20 22 28 31 35 ... (fully sorted)
 *
 * Approach: it's basically "merge k sorted lists" but done by repeatedly merging
 *           two at a time from the right. recurse to flatten the rest first, then
 *           merge the current column's down-list with the already-flattened rest
 *           using the standard two-pointer sorted merge (on the down pointers).
 *
 * Time: O(n * total nodes)
 * Space: O(1) extra (recursion aside)
 *
 * Topics: linked list, merge, recursion
 */
public class FlattenLinkedList {

    static class Node {
        int val;
        Node next;   // right
        Node down;   // down
        Node(int val) { this.val = val; }
    }

    static Node flatten(Node head) {
        if (head == null || head.next == null) return head;
        head.next = flatten(head.next);        // flatten everything to the right first
        head = merge(head, head.next);          // merge this column with the rest
        return head;
    }

    // merge two down-linked sorted lists
    static Node merge(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) { tail.down = a; a = a.down; }
            else                { tail.down = b; b = b.down; }
            tail = tail.down;
        }
        tail.down = (a != null) ? a : b;
        return dummy.down;
    }

    public static void main(String[] args) {
        // build 4 columns
        Node h = new Node(3);
        h.next = new Node(7);
        h.next.next = new Node(10);
        h.next.next.next = new Node(22);

        h.down = new Node(8); h.down.down = new Node(31);
        h.next.down = new Node(11);
        h.next.next.down = new Node(20); h.next.next.down.down = new Node(28);
        h.next.next.next.down = new Node(35);

        Node flat = flatten(h);
        StringBuilder sb = new StringBuilder();
        while (flat != null) { sb.append(flat.val).append(" "); flat = flat.down; }
        System.out.println("Test 1: " + sb.toString().trim());
        // expected: 3 7 8 10 11 20 22 28 31 35
    }
}
