import java.util.Stack;

/*
 * Problem: Valid Parentheses
 * Source: LeetCode
 * Difficulty: Easy
 *
 * Approach:
 * Push opening brackets and match each closing bracket with the latest opening bracket.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ValidParenthesesUsingStack {

    public static void main(String[] args) {
        String text = "{[()]}";
        System.out.println(isValid(text));
    }

    static boolean isValid(String text) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);

            if (current == '(' || current == '{' || current == '[') {
                stack.push(current);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if ((current == ')' && top != '(')
                        || (current == '}' && top != '{')
                        || (current == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
