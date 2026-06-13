/*
 * Problem: Jump Game
 *
 * Each element is the maximum jump length from that position. Starting at index 0,
 * can you reach the last index?
 *
 * Example:
 * Input: [2,3,1,1,4]
 * Output: true
 *
 * Input: [3,2,1,0,4]
 * Output: false   (you always get stuck at the 0 at index 3)
 *
 * Approach: greedy, track the FARTHEST index reachable so far. walk left to right;
 *           at each index, if it's beyond what we can reach, we're stuck -> false.
 *           otherwise update the farthest reach with i + nums[i]. if the reach ever
 *           covers the last index, we're good. no need to try every jump path -
 *           just keep extending the frontier.
 *
 * Time: O(n)
 * Space: O(1)
 *
 * Topics: greedy, array
 */
public class JumpGame {

    static boolean solve(int[] nums) {
        int farthest = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > farthest) return false;            // can't even get to i
            farthest = Math.max(farthest, i + nums[i]); // extend the frontier
            if (farthest >= nums.length - 1) return true;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{2,3,1,1,4})); // expected: true
        System.out.println("Test 2: " + solve(new int[]{3,2,1,0,4})); // expected: false
        System.out.println("Test 3: " + solve(new int[]{0}));          // expected: true
    }
}
