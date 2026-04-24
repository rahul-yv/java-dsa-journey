import java.util.Stack;

/*
 * Problem: Evaluate Postfix Expression
 * Source: Practice
 * Difficulty: Medium
 *
 * Approach:
 * Push numbers and apply each operator on the latest two numbers in the stack.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class EvaluatePostfixExpression {

    public static void main(String[] args) {
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(evaluate(tokens));
    }

    static int evaluate(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int second = stack.pop();
                int first = stack.pop();

                if (token.equals("+")) {
                    stack.push(first + second);
                } else if (token.equals("-")) {
                    stack.push(first - second);
                } else if (token.equals("*")) {
                    stack.push(first * second);
                } else {
                    stack.push(first / second);
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}
