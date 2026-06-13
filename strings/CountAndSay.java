/*
 * Problem: Count and Say
 *
 * Sequence where each term describes the previous one out loud.
 * 1st = "1". To get the next term, read the current one: count consecutive
 * digits and say "<count><digit>".
 *
 * Example:
 * "1" -> "11" (one 1) -> "21" (two 1s) -> "1211" (one 2, one 1) -> ...
 * Input: n = 4
 * Output: "1211"
 *
 * Approach: start at "1" and build term by term n-1 times. for each term, walk
 *           through grouping equal consecutive chars and append count + char.
 *           literally just simulating the "say it out loud" process.
 *
 * Time: O(n * length of string)
 * Space: O(length of string)
 *
 * Topics: string, simulation
 */
public class CountAndSay {

    static String solve(int n) {
        String cur = "1";
        for (int term = 2; term <= n; term++) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < cur.length()) {
                char digit = cur.charAt(i);
                int count = 0;
                // count how many of this digit in a row
                while (i < cur.length() && cur.charAt(i) == digit) {
                    count++;
                    i++;
                }
                sb.append(count).append(digit);
            }
            cur = sb.toString();
        }
        return cur;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(1)); // expected: 1
        System.out.println("Test 2: " + solve(4)); // expected: 1211
        System.out.println("Test 3: " + solve(5)); // expected: 111221
    }
}
