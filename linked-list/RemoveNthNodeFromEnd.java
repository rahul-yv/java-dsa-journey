/*
 * Problem: Remove Nth Node From End of List
 *
 * Remove the nth node from the end of the list and return the head.
 *
 * Example:
 * Input:  1 -> 2 -> 3 -> 4 -> 5, n = 2
 * Output: 1 -> 2 -> 3 -> 5     (removed the 4)
 *
 * Approach: one-pass two-pointer trick. move a "fast" pointer n nodes ahead first.
 *           then move "fast" and "slow" together until fast hits the end. now slow
 *           sits right before the node to delete, so we skip it. using a dummy
 *           node before head saves us from special-casing "remove the head".
 *
 * Time: O(n)  (single pass)
 * Space: O(1)
 *
 * Topics: linked list, two pointers
 */
public class RemoveNthNodeFromEnd {

    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    static Node removeNth(Node head, int n) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node fast = dummy, slow = dummy;

        for (int i = 0; i < n; i++) fast = fast.next;  // give fast a head start of n

        while (fast.next != null) {                     // move both till fast is last
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;   // skip the target node
        return dummy.next;
    }

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
        System.out.println("Test 1: " + print(removeNth(build(new int[]{1,2,3,4,5}), 2))); // 1 -> 2 -> 3 -> 5
        System.out.println("Test 2: " + print(removeNth(build(new int[]{1}), 1)));         // (empty)
        System.out.println("Test 3: " + print(removeNth(build(new int[]{1,2}), 2)));       // 2
    }
}
