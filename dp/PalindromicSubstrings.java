/*
 * Problem: Palindromic Substrings
 *
 * Count how many substrings of s are palindromes (different positions count
 * separately, even if the text is the same).
 *
 * Example:
 * Input: "aaa"
 * Output: 6   ("a","a","a","aa","aa","aaa")
 *
 * Approach: I use the "expand around center" idea (simplest to reason about). every
 *           palindrome has a center: either a single char (odd length) or a gap
 *           between two chars (even length). there are 2n-1 possible centers; from
 *           each, expand outward while the two sides match and count every valid
 *           palindrome you pass through. (a 2D DP also works, but this is O(1) space.)
 *
 * Time: O(n^2)
 * Space: O(1)
 *
 * Topics: dp, strings, expand around center
 */
public class PalindromicSubstrings {

    static int solve(String s) {
        int count = 0;
        for (int center = 0; center < s.length(); center++) {
            count += expand(s, center, center);     // odd-length palindromes
            count += expand(s, center, center + 1); // even-length palindromes
        }
        return count;
    }

    static int expand(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;     // s[left..right] is a palindrome
            left--;
            right++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve("aaa"));  // expected: 6
        System.out.println("Test 2: " + solve("abc"));  // expected: 3
        System.out.println("Test 3: " + solve("aba"));  // expected: 4
    }
}
