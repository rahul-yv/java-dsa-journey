/*
 * Problem: Search in Rotated Sorted Array
 *
 * A sorted array got rotated at some unknown point (e.g. [4,5,6,7,0,1,2]).
 * Find the index of target, or -1. Must be O(log n).
 *
 * Example:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Approach: normal binary search but with a twist. at any mid, at least one half
 *           (left of mid or right of mid) is still properly sorted. figure out
 *           which half is sorted, check if target falls inside that sorted half's
 *           range, and move accordingly. that's the whole trick.
 *
 * Time: O(log n)
 * Space: O(1)
 *
 * Topics: binary search, array
 */
public class SearchInRotatedSortedArray {

    static int solve(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;

            if (nums[lo] <= nums[mid]) {       // left half is sorted
                if (target >= nums[lo] && target < nums[mid]) hi = mid - 1;
                else lo = mid + 1;
            } else {                           // right half is sorted
                if (target > nums[mid] && target <= nums[hi]) lo = mid + 1;
                else hi = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{4,5,6,7,0,1,2}, 0)); // expected: 4
        System.out.println("Test 2: " + solve(new int[]{4,5,6,7,0,1,2}, 3)); // expected: -1
        System.out.println("Test 3: " + solve(new int[]{1}, 1));             // expected: 0
    }
}
