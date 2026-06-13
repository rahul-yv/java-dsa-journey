/*
 * Problem: Missing Number
 *
 * Array contains n distinct numbers taken from 0..n (so exactly one is missing).
 * Find the missing one.
 *
 * Example:
 * Input: [3,0,1]
 * Output: 2
 *
 * Approach: XOR trick again. XOR all the indices 0..n together with all the array
 *           values. every number that's present cancels with its matching index,
 *           and the missing number is left over. (the gauss-sum approach -
 *           n*(n+1)/2 minus the actual sum - also works and is easier to remember,
 *           but XOR avoids any overflow risk.)
 *
 * Time: O(n)
 * Space: O(1)
 *
 * Topics: bit manipulation, XOR
 */
public class MissingNumber {

    static int solve(int[] nums) {
        int n = nums.length;
        int x = n;                  // start with n since the loop covers indices 0..n-1
        for (int i = 0; i < n; i++) {
            x ^= i;                 // XOR the index
            x ^= nums[i];           // XOR the value
        }
        return x;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{3,0,1}));        // expected: 2
        System.out.println("Test 2: " + solve(new int[]{0,1}));          // expected: 2
        System.out.println("Test 3: " + solve(new int[]{9,6,4,2,3,5,7,0,1})); // expected: 8
    }
}
