/*
 * Problem: Group Anagrams
 *
 * Group words that are anagrams of each other (same letters, different order).
 *
 * Example:
 * Input: ["eat","tea","tan","ate","nat","bat"]
 * Output: [["eat","tea","ate"],["tan","nat"],["bat"]]
 *
 * Approach: two words are anagrams iff their sorted letters are equal.
 *           so use the sorted version of each word as a hashmap key, and bucket
 *           all words that share that key together. simple once you see it.
 *
 * Time: O(n * k log k)   n words, k = word length (for the sort)
 * Space: O(n * k)
 *
 * Topics: hashmap, string, sorting
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {

    static List<List<String>> solve(String[] words) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String w : words) {
            char[] c = w.toCharArray();
            Arrays.sort(c);
            String key = new String(c);          // e.g. "eat" -> "aet"
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(w);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new String[]{"eat","tea","tan","ate","nat","bat"}));
        // expected (order may vary): [[eat, tea, ate], [tan, nat], [bat]]
        System.out.println("Test 2: " + solve(new String[]{""}));   // expected: [[]]
        System.out.println("Test 3: " + solve(new String[]{"a"}));  // expected: [[a]]
    }
}
