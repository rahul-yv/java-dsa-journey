/*
 * Problem: Reverse a String
 * Source: Practice
 * Difficulty: Easy
 *
 * Approach:
 * Swap characters from both ends of the array until the pointers meet.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ReverseAString {

    public static void main(String[] args) {
        String text = "rahul";
        char[] chars = text.toCharArray();

        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        System.out.println(new String(chars));
    }
}
