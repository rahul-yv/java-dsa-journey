/*
 * Problem: Bubble Sort
 * Source: Classic DSA
 * Difficulty: Easy
 *
 * Approach:
 * Repeatedly swap adjacent elements when they are in the wrong order until
 * the array becomes sorted.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {5, 1, 4, 2, 8};

        bubbleSort(arr);

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                return;
            }
        }
    }
}
