/*
 * Problem: Longest Palindromic Substring
 *
 * Given a string, return the longest substring that reads the same forwards
 * and backwards.
 *
 * Example:
 * Input: "babad"
 * Output: "bab"   ("aba" is also fine)
 *
 * Approach: "expand around center". a palindrome mirrors around its middle, so
 *           try every possible center and stretch outward while both sides match.
 *           tricky bit: palindromes can have an odd center (single char like aba)
 *           OR an even center (two chars like abba), so we check both for each i.
 *           brute force checking every substring is O(n^3), this is O(n^2).
 *
 * Time: O(n^2)
 * Space: O(1)
 *
 * Topics: string, two pointers
 */
public class LongestPalindromicSubstring {

    static String solve(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int odd = expand(s, i, i);       // center is one char
            int even = expand(s, i, i + 1);  // center is between two chars
            int len = Math.max(odd, even);
            if (len > end - start + 1) {
                // recompute boundaries around center i
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    // returns length of palindrome expanded from the given center
    static int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve("babad")); // expected: bab (or aba)
        System.out.println("Test 2: " + solve("cbbd"));  // expected: bb
        System.out.println("Test 3: " + solve("a"));     // expected: a
    }
}
