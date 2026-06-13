/*
 * Problem: Doubly Linked List Basics
 *
 * Build a doubly linked list (each node has prev AND next) and support insert at
 * head, insert at tail, delete a value, and print forwards + backwards.
 *
 * The extra prev pointer makes deletion easier (you can reach the previous node
 * directly) and lets you walk the list both directions, at the cost of more
 * pointer bookkeeping on every operation.
 *
 * Approach: keep head and tail. every insert/delete has to fix up to 4 pointers
 *           (the new/removed node's prev & next, plus its neighbours pointing
 *           back). drawing boxes and arrows on paper saved me here.
 *
 * Time: insert head/tail O(1), delete O(n) to find the value
 * Space: O(1) per op
 *
 * Topics: doubly linked list, pointers, data structure design
 */
public class DoublyLinkedListBasics {

    static class Node {
        int val;
        Node prev, next;
        Node(int val) { this.val = val; }
    }

    Node head, tail;

    void insertAtHead(int val) {
        Node node = new Node(val);
        if (head == null) { head = tail = node; return; }
        node.next = head;
        head.prev = node;
        head = node;
    }

    void insertAtTail(int val) {
        Node node = new Node(val);
        if (tail == null) { head = tail = node; return; }
        node.prev = tail;
        tail.next = node;
        tail = node;
    }

    void delete(int val) {
        Node cur = head;
        while (cur != null && cur.val != val) cur = cur.next;
        if (cur == null) return;   // not found

        if (cur.prev != null) cur.prev.next = cur.next;
        else head = cur.next;      // deleting head

        if (cur.next != null) cur.next.prev = cur.prev;
        else tail = cur.prev;      // deleting tail
    }

    String forward() {
        StringBuilder sb = new StringBuilder();
        Node cur = head;
        while (cur != null) { sb.append(cur.val); if (cur.next != null) sb.append(" <-> "); cur = cur.next; }
        return sb.toString();
    }
    String backward() {
        StringBuilder sb = new StringBuilder();
        Node cur = tail;
        while (cur != null) { sb.append(cur.val); if (cur.prev != null) sb.append(" <-> "); cur = cur.prev; }
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedListBasics list = new DoublyLinkedListBasics();
        list.insertAtTail(2);
        list.insertAtTail(3);
        list.insertAtHead(1);
        System.out.println("forward:  " + list.forward());  // expected: 1 <-> 2 <-> 3
        System.out.println("backward: " + list.backward()); // expected: 3 <-> 2 <-> 1
        list.delete(2);
        System.out.println("after delete(2): " + list.forward()); // expected: 1 <-> 3
    }
}
