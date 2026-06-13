/*
 * Problem: Insertion Sort
 *
 * Sort by building the sorted part one element at a time, inserting each new
 * element into its right spot among the ones before it.
 *
 * Example:
 * Input:  [12, 11, 13, 5, 6]
 * Output: [5, 6, 11, 12, 13]
 *
 * Approach: same way you sort playing cards in your hand. take the next element
 *           (key) and shift everything bigger than it one step right, then drop
 *           the key into the gap. fast on nearly-sorted data (close to O(n)).
 *
 * Time: O(n^2) worst, O(n) best (already sorted)
 * Space: O(1)
 *
 * Topics: sorting, in-place
 */

import java.util.Arrays;

public class InsertionSort {

    static int[] sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            // shift bigger elements one position to the right
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;   // drop key into the open slot
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + Arrays.toString(sort(new int[]{12,11,13,5,6}))); // [5, 6, 11, 12, 13]
        System.out.println("Test 2: " + Arrays.toString(sort(new int[]{1,2,3})));        // [1, 2, 3]
        System.out.println("Test 3: " + Arrays.toString(sort(new int[]{3,1,2})));        // [1, 2, 3]
    }
}
