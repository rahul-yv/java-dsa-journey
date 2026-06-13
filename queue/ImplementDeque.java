/*
 * Problem: Implement a Deque (double-ended queue)
 *
 * A deque lets you add/remove from BOTH ends. Support addFront, addRear,
 * removeFront, removeRear, peekFront, peekRear.
 *
 * Approach: circular array again, but now front can also move backward. addFront
 *           moves front one step back (with wraparound, so -1 becomes cap-1) and
 *           writes there. addRear writes at (front+size)%cap like a normal queue.
 *           the (front - 1 + cap) % cap trick avoids negative indices.
 *
 * Time: O(1) per op
 * Space: O(n)
 *
 * Topics: deque, array, circular buffer, data structure design
 */
public class ImplementDeque {

    int[] data;
    int front, size;

    ImplementDeque(int capacity) {
        data = new int[capacity];
        front = 0;
        size = 0;
    }

    boolean isFull()  { return size == data.length; }
    boolean isEmpty() { return size == 0; }

    void addFront(int x) {
        if (isFull()) { System.out.println("full"); return; }
        front = (front - 1 + data.length) % data.length; // step back, wrap
        data[front] = x;
        size++;
    }

    void addRear(int x) {
        if (isFull()) { System.out.println("full"); return; }
        int rear = (front + size) % data.length;
        data[rear] = x;
        size++;
    }

    int removeFront() {
        if (isEmpty()) throw new RuntimeException("empty");
        int val = data[front];
        front = (front + 1) % data.length;
        size--;
        return val;
    }

    int removeRear() {
        if (isEmpty()) throw new RuntimeException("empty");
        int rear = (front + size - 1) % data.length;
        size--;
        return data[rear];
    }

    int peekFront() { return data[front]; }
    int peekRear()  { return data[(front + size - 1) % data.length]; }

    public static void main(String[] args) {
        ImplementDeque dq = new ImplementDeque(5);
        dq.addRear(10);
        dq.addRear(20);
        dq.addFront(5);
        System.out.println("front: " + dq.peekFront()); // expected: 5
        System.out.println("rear: " + dq.peekRear());    // expected: 20
        System.out.println("removeFront: " + dq.removeFront()); // expected: 5
        System.out.println("removeRear: " + dq.removeRear());   // expected: 20
        System.out.println("front: " + dq.peekFront()); // expected: 10
    }
}
