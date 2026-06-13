/*
 * Problem: Max Heap (built from scratch)
 *
 * Same as the min-heap but flipped: every parent is >= its children, so the
 * LARGEST element sits at the root. Support insert and extractMax.
 *
 * Approach: identical array layout (parent (i-1)/2, kids 2i+1/2i+2) and the same
 *           two sift operations - just swap the comparisons so bigger values float
 *           to the top instead of smaller ones. once you've written a min-heap,
 *           the max-heap is a 2-second change (flip < to >).
 *
 * Time: O(log n) insert and extractMax
 * Space: O(n)
 *
 * Topics: heap, priority queue, array
 */

import java.util.ArrayList;
import java.util.List;

public class MaxHeapImplementation {

    List<Integer> heap = new ArrayList<>();

    void insert(int val) {
        heap.add(val);
        bubbleUp(heap.size() - 1);
    }

    int peek() { return heap.get(0); }

    int extractMax() {
        int max = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            bubbleDown(0);
        }
        return max;
    }

    void bubbleUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap.get(i) <= heap.get(parent)) break;  // note: <= for max-heap
            swap(i, parent);
            i = parent;
        }
    }

    void bubbleDown(int i) {
        int n = heap.size();
        while (true) {
            int largest = i, left = 2 * i + 1, right = 2 * i + 2;
            if (left < n && heap.get(left) > heap.get(largest)) largest = left;
            if (right < n && heap.get(right) > heap.get(largest)) largest = right;
            if (largest == i) break;
            swap(i, largest);
            i = largest;
        }
    }

    void swap(int i, int j) {
        int t = heap.get(i); heap.set(i, heap.get(j)); heap.set(j, t);
    }

    public static void main(String[] args) {
        MaxHeapImplementation h = new MaxHeapImplementation();
        for (int x : new int[]{5, 3, 8, 1, 9, 2}) h.insert(x);
        System.out.println("peek (max): " + h.peek());      // expected: 9
        System.out.print("extract order: ");
        while (!h.heap.isEmpty()) System.out.print(h.extractMax() + " "); // expected: 9 8 5 3 2 1
        System.out.println();
    }
}
