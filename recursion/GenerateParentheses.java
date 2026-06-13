/*
 * Problem: Generate Parentheses
 *
 * Given n pairs of parentheses, generate all valid combinations.
 *
 * Example:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 * Approach: build the string char by char with backtracking. at each step we can
 *           add a '(' if we still have opens left, or a ')' if there are more
 *           opens than closes already placed (otherwise it'd be invalid). when
 *           the string reaches length 2n it's a complete valid combo. the two
 *           "rules" guarantee we never build a broken string.
 *
 * Time: Catalan number-ish, roughly O(4^n / sqrt(n))
 * Space: O(n) recursion depth
 *
 * Topics: backtracking, recursion, strings
 */

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    static List<String> solve(int n) {
        List<String> res = new ArrayList<>();
        build(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    static void build(List<String> res, StringBuilder sb, int open, int close, int n) {
        if (sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }
        if (open < n) {                 // can still open
            sb.append('(');
            build(res, sb, open + 1, close, n);
            sb.deleteCharAt(sb.length() - 1);   // undo
        }
        if (close < open) {             // can close only if there's an unmatched open
            sb.append(')');
            build(res, sb, open, close + 1, n);
            sb.deleteCharAt(sb.length() - 1);   // undo
        }
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(3));
        // expected: [((())), (()()), (())(), ()(()), ()()()]
        System.out.println("Test 2: " + solve(1)); // expected: [()]
    }
}
