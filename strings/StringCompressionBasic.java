/*
 * Problem: String Compression
 * Source: Practice
 * Difficulty: Easy
 *
 * Approach:
 * Count consecutive repeated characters and append the character with its count.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class StringCompressionBasic {

    public static void main(String[] args) {
        String text = "aaabbccccd";
        System.out.println(compress(text));
    }

    static String compress(String text) {
        if (text.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 1; i <= text.length(); i++) {
            if (i < text.length() && text.charAt(i) == text.charAt(i - 1)) {
                count++;
            } else {
                result.append(text.charAt(i - 1));
                result.append(count);
                count = 1;
            }
        }

        return result.toString();
    }
}
