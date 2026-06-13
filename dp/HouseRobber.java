/*
 * Problem: House Robber
 *
 * Houses in a row each have some money. You can't rob two ADJACENT houses (alarm
 * goes off). Maximize the money you can rob.
 *
 * Example:
 * Input: [2,7,9,3,1]
 * Output: 12   (rob house 0, 2, 4 -> 2 + 9 + 1)
 *
 * Approach: for each house you choose: rob it (money + best up to house i-2) or
 *           skip it (best up to house i-1). take the max. so
 *           dp[i] = max(dp[i-1], dp[i-2] + nums[i]). only need the last two values
 *           so I keep two variables instead of a full array.
 *
 * Time: O(n)
 * Space: O(1)
 *
 * Topics: dp, bottom-up
 */
public class HouseRobber {

    static int solve(int[] nums) {
        int prev = 0;   // best up to house i-1
        int prev2 = 0;  // best up to house i-2
        for (int money : nums) {
            int cur = Math.max(prev, prev2 + money); // skip vs rob
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{2,7,9,3,1})); // expected: 12
        System.out.println("Test 2: " + solve(new int[]{1,2,3,1}));   // expected: 4
        System.out.println("Test 3: " + solve(new int[]{5}));          // expected: 5
    }
}
