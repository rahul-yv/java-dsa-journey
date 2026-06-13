/*
 * Problem: 3Sum
 *
 * Find all unique triplets in the array that add up to 0.
 *
 * Example:
 * Input: [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 *
 * Approach: sort the array first. then fix one number (nums[i]) and use the
 *           classic two-pointer trick on the rest to find pairs that sum to
 *           -nums[i]. sorting also makes it easy to skip duplicates so we don't
 *           get repeated triplets.
 *           brute force would be 3 nested loops = O(n^3), this is O(n^2).
 *
 * Time: O(n^2)
 * Space: O(1) ignoring the output list
 *
 * Topics: sorting, two pointers, array
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    static List<List<Integer>> solve(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip dup for first number

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    // skip dups for the pair too
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < 0) {
                    left++;   // need a bigger sum
                } else {
                    right--;  // need a smaller sum
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{-1,0,1,2,-1,-4})); // expected: [[-1, -1, 2], [-1, 0, 1]]
        System.out.println("Test 2: " + solve(new int[]{0,1,1}));          // expected: []
        System.out.println("Test 3: " + solve(new int[]{0,0,0}));          // expected: [[0, 0, 0]]
    }
}
