/*
 * Problem: Word Break
 *
 * Given a string and a dictionary of words, return true if the string can be cut
 * into a sequence of dictionary words.
 *
 * Example:
 * Input: s = "leetcode", dict = ["leet","code"]
 * Output: true   ("leet" + "code")
 *
 * Approach: dp[i] = can the first i characters of s be fully broken into dict
 *           words? dp[0] = true (empty string). for each end position i, look at
 *           every split point j before it: if dp[j] is true AND the substring
 *           s[j..i) is in the dictionary, then dp[i] is true too. answer is
 *           dp[s.length()]. it's like asking "can I reach position i?".
 *
 * Time: O(n^2) (times substring/lookup cost)
 * Space: O(n)
 *
 * Topics: dp, strings
 */

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    static boolean solve(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;   // empty prefix is trivially breakable

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;   // found one valid split, no need to keep looking
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve("leetcode", java.util.Arrays.asList("leet","code"))); // true
        System.out.println("Test 2: " + solve("applepenapple", java.util.Arrays.asList("apple","pen"))); // true
        System.out.println("Test 3: " + solve("catsandog", java.util.Arrays.asList("cats","dog","sand","and","cat"))); // false
    }
}
