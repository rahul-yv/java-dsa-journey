/*
 * Problem: Reverse a Linked List
 *
 * Reverse a singly linked list and return the new head.
 *
 * Example:
 * Input:  1 -> 2 -> 3 -> 4 -> 5
 * Output: 5 -> 4 -> 3 -> 2 -> 1
 *
 * Approach: walk the list flipping each node's .next to point backwards. you need
 *           3 pointers: prev (the reversed part so far), cur (node we're flipping),
 *           and next (saved so we don't lose the rest of the list when we re-point).
 *           the order of the 4 lines inside the loop matters a LOT - I messed it
 *           up the first few times by losing the rest of the list.
 *
 * Time: O(n)
 * Space: O(1)
 *
 * Topics: linked list, pointers
 */
public class ReverseLinkedList {

    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    static Node reverse(Node head) {
        Node prev = null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;  // save the rest before we lose it
            cur.next = prev;       // flip the pointer
            prev = cur;            // move prev forward
            cur = next;            // move cur forward
        }
        return prev;   // prev ends up as the new head
    }

    // helpers to build/print so main is readable
    static Node build(int[] vals) {
        Node dummy = new Node(0), tail = dummy;
        for (int v : vals) { tail.next = new Node(v); tail = tail.next; }
        return dummy.next;
    }
    static String print(Node head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) { sb.append(head.val); if (head.next != null) sb.append(" -> "); head = head.next; }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + print(reverse(build(new int[]{1,2,3,4,5})))); // 5 -> 4 -> 3 -> 2 -> 1
        System.out.println("Test 2: " + print(reverse(build(new int[]{1,2}))));       // 2 -> 1
        System.out.println("Test 3: " + print(reverse(build(new int[]{}))));          // (empty)
    }
}
