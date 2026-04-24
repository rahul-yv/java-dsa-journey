/*
 * Problem: Valid Anagram
 * Source: LeetCode
 * Difficulty: Easy
 *
 * Approach:
 * Count letter frequencies for both strings and compare the counts.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class ValidAnagram {

    public static void main(String[] args) {
        String first = "listen";
        String second = "silent";
        System.out.println(isAnagram(first, second));
    }

    static boolean isAnagram(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }

        int[] count = new int[26];

        for (int i = 0; i < first.length(); i++) {
            count[first.charAt(i) - 'a']++;
            count[second.charAt(i) - 'a']--;
        }

        for (int value : count) {
            if (value != 0) {
                return false;
            }
        }

        return true;
    }
}
