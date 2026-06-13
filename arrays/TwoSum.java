/*
 * Problem: Two Sum
 *
 * Given an array and a target, find two numbers that add up to the target
 * and return their indexes.
 *
 * Example:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]   (because 2 + 7 = 9)
 *
 * Approach: brute force is check every pair -> O(n^2). slow.
 *           better trick: as we walk the array, for each number we ask
 *           "have I already seen the number that completes my target?"
 *           we keep seen numbers in a hashmap (value -> index). one pass.
 *
 * Time: O(n)
 * Space: O(n)   (the map)
 *
 * Topics: hashmap, array
 */

import java.util.HashMap;

public class TwoSum {

    static int[] solve(int[] nums, int target) {
        HashMap<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];   // the partner we're looking for
            if (seen.containsKey(need)) {
                return new int[]{seen.get(need), i};
            }
            seen.put(nums[i], i);          // remember this one for later
        }
        return new int[]{-1, -1};          // shouldn't happen if answer exists
    }

    public static void main(String[] args) {
        int[] r1 = solve(new int[]{2, 7, 11, 15}, 9);
        System.out.println("Test 1: [" + r1[0] + "," + r1[1] + "]"); // expected: [0,1]

        int[] r2 = solve(new int[]{3, 2, 4}, 6);
        System.out.println("Test 2: [" + r2[0] + "," + r2[1] + "]"); // expected: [1,2]

        int[] r3 = solve(new int[]{3, 3}, 6);
        System.out.println("Test 3: [" + r3[0] + "," + r3[1] + "]"); // expected: [0,1]
    }
}
