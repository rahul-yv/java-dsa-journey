/*
 * Problem: Majority Element  (LeetCode #169 - Easy)
 *
 * Return the element that appears more than n/2 times. It's guaranteed to exist.
 *
 * Example:
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 *
 * Approach: Boyer-Moore voting. keep a candidate and a count. when count is 0,
 *           adopt the current number as candidate. then +1 if it matches the
 *           candidate, -1 if not. because the majority appears more than half the
 *           time, it can never be fully cancelled out and survives as the candidate.
 *           O(1) space, no hashmap. clever once it clicks.
 *
 * Time: O(n)
 * Space: O(1)
 *
 * Topics: array, Boyer-Moore voting
 */
public class MajorityElement {

    static int solve(int[] nums) {
        int candidate = nums[0], count = 0;
        for (int n : nums) {
            if (count == 0) candidate = n;     // pick a new candidate
            count += (n == candidate) ? 1 : -1;
        }
        return candidate;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{2,2,1,1,1,2,2})); // expected: 2
        System.out.println("Test 2: " + solve(new int[]{3,2,3}));         // expected: 3
        System.out.println("Test 3: " + solve(new int[]{1}));             // expected: 1
    }
}
