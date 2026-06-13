/*
 * Problem: Sort Colors  (LeetCode #75 - Medium)
 *
 * Array of 0s, 1s, and 2s (red/white/blue). Sort it in place in one pass without
 * a counting sort.
 *
 * Example:
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Approach: the Dutch National Flag algorithm. three pointers: low (boundary of
 *           0s), high (boundary of 2s), and mid (current). when nums[mid] is 0,
 *           swap it to the low region; if 2, swap it to the high region (and DON'T
 *           advance mid, since the swapped-in value is unchecked); if 1, just move
 *           mid on. one clean pass, O(1) space. the "don't advance mid after a 2
 *           swap" detail is the classic bug.
 *
 * Time: O(n)
 * Space: O(1)
 *
 * Topics: array, three pointers, Dutch national flag
 */

import java.util.Arrays;

public class SortColors {

    static int[] solve(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low++, mid++);
            } else if (nums[mid] == 1) {
                mid++;
            } else {                       // nums[mid] == 2
                swap(nums, mid, high--);   // note: don't advance mid here
            }
        }
        return nums;
    }

    static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + Arrays.toString(solve(new int[]{2,0,2,1,1,0}))); // [0,0,1,1,2,2]
        System.out.println("Test 2: " + Arrays.toString(solve(new int[]{2,0,1})));       // [0,1,2]
    }
}
