/*
 * Problem: Print Subsequences
 * Source: Practice
 * Difficulty: Medium
 *
 * Approach:
 * For each index, either include the current character or skip it.
 *
 * Time Complexity: O(2^n)
 * Space Complexity: O(n)
 */
public class PrintSubsequencesUsingRecursion {

    public static void main(String[] args) {
        String text = "abc";
        printSubsequences(text, 0, "");
    }

    static void printSubsequences(String text, int index, String current) {
        if (index == text.length()) {
            System.out.println(current);
            return;
        }

        printSubsequences(text, index + 1, current + text.charAt(index));
        printSubsequences(text, index + 1, current);
    }
}
