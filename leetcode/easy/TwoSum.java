/*
 * LeetCode #1 - Two Sum
 * Difficulty: Easy
 * Topic: Arrays, HashMap
 *
 * Approach 1: Brute Force O(n²)
 * Approach 2: HashMap O(n) - optimal
 *
 * Time: O(n)
 * Space: O(n)
 */
import java.util.HashMap;

class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}