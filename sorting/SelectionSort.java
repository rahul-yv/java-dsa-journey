/*
 * Problem: Selection Sort
 *
 * Sort an array by repeatedly picking the smallest remaining element and
 * putting it at the front.
 *
 * Example:
 * Input:  [64, 25, 12, 22, 11]
 * Output: [11, 12, 22, 25, 64]
 *
 * Approach: for each position i, scan the rest of the array to find the minimum,
 *           then swap it into position i. like sorting cards by always grabbing
 *           the smallest one left and placing it next. always O(n^2) even if
 *           already sorted, but it does the fewest swaps of the simple sorts.
 *
 * Time: O(n^2)
 * Space: O(1)
 *
 * Topics: sorting, in-place
 */

import java.util.Arrays;

public class SelectionSort {

    static int[] sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) min = j;   // track index of the smallest
            }
            // swap smallest into position i
            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + Arrays.toString(sort(new int[]{64,25,12,22,11}))); // [11, 12, 22, 25, 64]
        System.out.println("Test 2: " + Arrays.toString(sort(new int[]{5,4,3,2,1})));      // [1, 2, 3, 4, 5]
        System.out.println("Test 3: " + Arrays.toString(sort(new int[]{1})));              // [1]
    }
}
