/*
 * Problem: Minimum Window Substring  (LeetCode #76 - Hard)
 *
 * Given strings s and t, return the smallest substring of s that contains ALL the
 * characters of t (including duplicates). "" if none exists.
 *
 * Example:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 *
 * Approach: sliding window with two pointers. count how many of each char t needs.
 *           expand the right end, and each time we fully cover a needed char,
 *           decrease a "missing" counter. once missing == 0 the window is valid, so
 *           shrink from the left as far as possible while it stays valid, recording
 *           the smallest window seen. expand-then-shrink is the whole pattern; the
 *           tricky bit is tracking "have" vs "need" counts correctly.
 *
 * Time: O(s + t)
 * Space: O(unique chars)
 *
 * Topics: sliding window, two pointers, hashmap, strings
 */

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    static String solve(String s, String t) {
        if (s.length() < t.length()) return "";
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) need.merge(c, 1, Integer::sum);

        int required = need.size();   // distinct chars we still need to satisfy
        int formed = 0;
        Map<Character, Integer> window = new HashMap<>();

        int left = 0, bestLen = Integer.MAX_VALUE, bestStart = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            window.merge(c, 1, Integer::sum);
            if (need.containsKey(c) && window.get(c).intValue() == need.get(c).intValue()) {
                formed++;   // this char is now fully covered
            }

            // shrink while the window is valid
            while (formed == required) {
                if (right - left + 1 < bestLen) {
                    bestLen = right - left + 1;
                    bestStart = left;
                }
                char lc = s.charAt(left);
                window.merge(lc, -1, Integer::sum);
                if (need.containsKey(lc) && window.get(lc) < need.get(lc)) {
                    formed--;   // dropped below required -> window no longer valid
                }
                left++;
            }
        }
        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + bestLen);
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve("ADOBECODEBANC", "ABC")); // expected: BANC
        System.out.println("Test 2: " + solve("a", "a"));               // expected: a
        System.out.println("Test 3: " + solve("a", "aa"));              // expected: (empty)
    }
}
