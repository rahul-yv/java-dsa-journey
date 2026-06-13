/*
 * Problem: Singly Linked List Basics
 *
 * Build a singly linked list from scratch and support the basic operations:
 * insert at head, insert at tail, delete a value, search, and print/traverse.
 *
 * A linked list is just nodes where each node points to the next one. No indexes
 * like an array - you walk from the head following .next pointers.
 *
 * Approach: a Node holds a value and a reference to the next node. We keep a
 *           "head" reference to the start. Most ops are just pointer juggling -
 *           the thing to be careful about is updating head when inserting/deleting
 *           at the front, and not losing the rest of the list when you re-point.
 *
 * Time: insert head O(1), insert tail / delete / search O(n)
 * Space: O(1) per op
 *
 * Topics: linked list, pointers, data structure design
 */
public class SinglyLinkedListBasics {

    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    Node head;

    void insertAtHead(int val) {
        Node node = new Node(val);
        node.next = head;   // point new node to old head
        head = node;        // new node is the head now
    }

    void insertAtTail(int val) {
        Node node = new Node(val);
        if (head == null) { head = node; return; }
        Node cur = head;
        while (cur.next != null) cur = cur.next;  // walk to the last node
        cur.next = node;
    }

    void delete(int val) {
        if (head == null) return;
        if (head.val == val) { head = head.next; return; }  // deleting the head
        Node cur = head;
        while (cur.next != null && cur.next.val != val) cur = cur.next;
        if (cur.next != null) cur.next = cur.next.next;     // skip over the node
    }

    boolean search(int val) {
        Node cur = head;
        while (cur != null) {
            if (cur.val == val) return true;
            cur = cur.next;
        }
        return false;
    }

    String print() {
        StringBuilder sb = new StringBuilder();
        Node cur = head;
        while (cur != null) {
            sb.append(cur.val);
            if (cur.next != null) sb.append(" -> ");
            cur = cur.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedListBasics list = new SinglyLinkedListBasics();
        list.insertAtTail(1);
        list.insertAtTail(2);
        list.insertAtTail(3);
        list.insertAtHead(0);
        System.out.println("List: " + list.print());        // expected: 0 -> 1 -> 2 -> 3
        System.out.println("search(2): " + list.search(2));  // expected: true
        list.delete(2);
        System.out.println("after delete(2): " + list.print()); // expected: 0 -> 1 -> 3
        list.delete(0);
        System.out.println("after delete(0): " + list.print()); // expected: 1 -> 3
    }
}
