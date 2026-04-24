/*
 * Problem: Circular Queue
 * Source: Practice
 * Difficulty: Medium
 *
 * Approach:
 * Use an array with front, rear, and size values that wrap using modulo arithmetic.
 *
 * Time Complexity: O(1) per operation
 * Space Complexity: O(n)
 */
public class ImplementCircularQueue {

    public static void main(String[] args) {
        CircularQueueArray queue = new CircularQueueArray(3);

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println(queue.dequeue());
        queue.enqueue(40);

        while (!queue.isEmpty()) {
            System.out.print(queue.dequeue() + " ");
        }
    }
}

class CircularQueueArray {
    private final int[] data;
    private int front;
    private int rear;
    private int size;

    CircularQueueArray(int capacity) {
        data = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    void enqueue(int value) {
        if (size == data.length) {
            System.out.println("Queue is full");
            return;
        }

        rear = (rear + 1) % data.length;
        data[rear] = value;
        size++;
    }

    int dequeue() {
        if (isEmpty()) {
            return -1;
        }

        int removed = data[front];
        front = (front + 1) % data.length;
        size--;
        return removed;
    }

    boolean isEmpty() {
        return size == 0;
    }
}
