/*
 * Problem: Palindrome Check
 * Source: Practice
 * Difficulty: Easy
 *
 * Approach:
 * Compare characters from the start and end moving inward.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class PalindromeCheckForString {

    public static void main(String[] args) {
        String text = "madam";
        System.out.println(isPalindrome(text));
    }

    static boolean isPalindrome(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
