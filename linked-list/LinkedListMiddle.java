/*
 * Problem: Middle of the Linked List
 *
 * Return the middle node. If there are two middles (even length), return the
 * second one.
 *
 * Example:
 * Input:  1 -> 2 -> 3 -> 4 -> 5
 * Output: 3
 *
 * Input:  1 -> 2 -> 3 -> 4 -> 5 -> 6
 * Output: 4
 *
 * Approach: slow/fast pointers again. fast moves twice as fast as slow, so when
 *           fast reaches the end, slow is exactly halfway. no need to count the
 *           length first. this slow/fast pattern shows up everywhere in linked
 *           list problems, worth burning into memory.
 *
 * Time: O(n)
 * Space: O(1)
 *
 * Topics: linked list, two pointers
 */
public class LinkedListMiddle {

    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    static Node middle(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;        // 1 step
            fast = fast.next.next;   // 2 steps
        }
        return slow;
    }

    static Node build(int[] vals) {
        Node dummy = new Node(0), tail = dummy;
        for (int v : vals) { tail.next = new Node(v); tail = tail.next; }
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + middle(build(new int[]{1,2,3,4,5})).val);   // expected: 3
        System.out.println("Test 2: " + middle(build(new int[]{1,2,3,4,5,6})).val); // expected: 4
        System.out.println("Test 3: " + middle(build(new int[]{1})).val);           // expected: 1
    }
}
