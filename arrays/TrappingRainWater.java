/*
 * Problem: Trapping Rain Water
 *
 * Given heights of bars (width 1 each), how much rain water can be trapped
 * between them after it rains?
 *
 * Example:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * Approach: water sitting on top of bar i = min(tallest on left, tallest on right) - height[i].
 *           brute force: for each bar scan left and right for the max -> O(n^2).
 *           optimal: two pointers. keep leftMax and rightMax as we close in.
 *           the side with the smaller wall decides the water (because the other
 *           side already has something taller blocking). took me a bit to accept this.
 *
 * Time: O(n)
 * Space: O(1)
 *
 * Topics: two pointers, array
 */
public class TrappingRainWater {

    static int solve(int[] h) {
        int left = 0, right = h.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (left < right) {
            if (h[left] < h[right]) {
                // left wall is the shorter one, so left side decides
                if (h[left] >= leftMax) leftMax = h[left];
                else water += leftMax - h[left];
                left++;
            } else {
                if (h[right] >= rightMax) rightMax = h[right];
                else water += rightMax - h[right];
                right--;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); // expected: 6
        System.out.println("Test 2: " + solve(new int[]{4,2,0,3,2,5}));             // expected: 9
        System.out.println("Test 3: " + solve(new int[]{1,1,1}));                   // expected: 0
    }
}
