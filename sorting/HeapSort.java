/*
 * Problem: Heap Sort
 *
 * Sort an array using a binary max-heap.
 *
 * Example:
 * Input:  [12, 11, 13, 5, 6, 7]
 * Output: [5, 6, 7, 11, 12, 13]
 *
 * Approach: first "heapify" the array into a max-heap (biggest element at root,
 *           index 0). then repeatedly swap the root (max) with the last element,
 *           shrink the heap by one, and sift the new root back down to fix the
 *           heap. each extraction puts the next-largest at the end, so the array
 *           ends up sorted. heap is stored in the array itself - children of i
 *           are at 2i+1 and 2i+2.
 *
 * Time: O(n log n)
 * Space: O(1)
 *
 * Topics: heap, sorting, in-place
 */

import java.util.Arrays;

public class HeapSort {

    static int[] sort(int[] a) {
        int n = a.length;

        // build max-heap: start from last non-leaf node and sift down
        for (int i = n / 2 - 1; i >= 0; i--) siftDown(a, n, i);

        // pull max to the end one by one
        for (int end = n - 1; end > 0; end--) {
            swap(a, 0, end);          // biggest goes to its sorted spot
            siftDown(a, end, 0);      // restore heap on the shrunk part
        }
        return a;
    }

    // push a[i] down until both children are smaller (max-heap), size = heap size
    static void siftDown(int[] a, int size, int i) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1, right = 2 * i + 2;
            if (left < size && a[left] > a[largest]) largest = left;
            if (right < size && a[right] > a[largest]) largest = right;
            if (largest == i) break;  // already a heap here
            swap(a, i, largest);
            i = largest;
        }
    }

    static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + Arrays.toString(sort(new int[]{12,11,13,5,6,7}))); // [5, 6, 7, 11, 12, 13]
        System.out.println("Test 2: " + Arrays.toString(sort(new int[]{4,10,3,5,1})));     // [1, 3, 4, 5, 10]
    }
}
