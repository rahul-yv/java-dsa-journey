/*nums = [1, 1, 2]
k  i

i=1: nums[1] == nums[0] — duplicate — skip
i=2: nums[2] != nums[0] — unique — write karo

Result: [1, 2, _] k=2

 * LeetCode #26 - Remove Duplicates from Sorted Array
 * Difficulty: Easy
 * Topic: Arrays, Two Pointers
 *
 * Approach: Two pointer — k tracks unique position
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        int k = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}