/*
 * Problem: Valid Palindrome  (LeetCode #125 - Easy)
 *
 * Return true if a string is a palindrome considering only letters and digits, and
 * ignoring case.
 *
 * Example:
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 *
 * Approach: two pointers from both ends. skip any non-alphanumeric chars, then
 *           compare the lowercased characters. if a mismatch -> not a palindrome.
 *           the only fiddly part is the inner while loops that skip punctuation and
 *           spaces.
 *
 * Time: O(n)
 * Space: O(1)
 *
 * Topics: string, two pointers
 */
public class ValidPalindrome {

    static boolean solve(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve("A man, a plan, a canal: Panama")); // true
        System.out.println("Test 2: " + solve("race a car")); // false
        System.out.println("Test 3: " + solve(" "));          // true (empty after cleaning)
    }
}
