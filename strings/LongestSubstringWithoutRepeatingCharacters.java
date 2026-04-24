/*
 * Problem: Longest Substring Without Repeating Characters
 * Source: LeetCode
 * Difficulty: Medium
 *
 * Approach:
 * Use a sliding window and track the last seen index of each character.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String text = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(text));
    }

    static int lengthOfLongestSubstring(String text) {
        int[] lastSeen = new int[256];

        for (int i = 0; i < lastSeen.length; i++) {
            lastSeen[i] = -1;
        }

        int left = 0;
        int best = 0;

        for (int right = 0; right < text.length(); right++) {
            char current = text.charAt(right);

            if (lastSeen[current] >= left) {
                left = lastSeen[current] + 1;
            }

            lastSeen[current] = right;
            best = Math.max(best, right - left + 1);
        }

        return best;
    }
}
