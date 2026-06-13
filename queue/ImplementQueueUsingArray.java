/*
 * Problem: Implement a Queue using an Array
 *
 * Build a queue (FIFO - first in first out) with a plain array. Support enqueue,
 * dequeue, peek front, isEmpty.
 *
 * Approach: use a circular array so we don't waste space. keep a front index, a
 *           size counter, and wrap indices around with % capacity. enqueue writes
 *           at (front + size) % cap; dequeue reads front and moves front forward
 *           with wraparound. the modulo wrap is the whole point of "circular".
 *
 * Time: O(1) per op
 * Space: O(n)
 *
 * Topics: queue, array, circular buffer, data structure design
 */
public class ImplementQueueUsingArray {

    int[] data;
    int front;   // index of the front element
    int size;    // how many elements currently in queue

    ImplementQueueUsingArray(int capacity) {
        data = new int[capacity];
        front = 0;
        size = 0;
    }

    void enqueue(int x) {
        if (size == data.length) {
            System.out.println("queue full, can't add " + x);
            return;
        }
        int rear = (front + size) % data.length; // wraps around
        data[rear] = x;
        size++;
    }

    int dequeue() {
        if (isEmpty()) throw new RuntimeException("queue empty");
        int val = data[front];
        front = (front + 1) % data.length;
        size--;
        return val;
    }

    int peek() {
        if (isEmpty()) throw new RuntimeException("queue empty");
        return data[front];
    }

    boolean isEmpty() { return size == 0; }

    public static void main(String[] args) {
        ImplementQueueUsingArray q = new ImplementQueueUsingArray(3);
        q.enqueue(1);
        q.enqueue(2);
        System.out.println("peek: " + q.peek());      // expected: 1
        System.out.println("dequeue: " + q.dequeue()); // expected: 1
        q.enqueue(3);
        q.enqueue(4);                                   // tests the wraparound
        System.out.println("dequeue: " + q.dequeue()); // expected: 2
        System.out.println("dequeue: " + q.dequeue()); // expected: 3
        System.out.println("dequeue: " + q.dequeue()); // expected: 4
    }
}
