/*
 * Problem: First Occurrence in Sorted Array
 * Source: Classic DSA
 * Difficulty: Easy
 *
 * Approach:
 * Use binary search and continue searching on the left side after finding the
 * target so the first index is captured.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class FirstOccurrenceInSortedArray {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 4, 5, 7};
        int target = 2;

        int index = findFirstOccurrence(nums, target);

        if (index != -1) {
            System.out.println("First occurrence at index " + index);
        } else {
            System.out.println("Element not found");
        }
    }

    static int findFirstOccurrence(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int answer = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                answer = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }
}
