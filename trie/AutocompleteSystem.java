/*
 * Problem: Autocomplete / Search Suggestions using a Trie
 *
 * Build a dictionary, then for any typed prefix return all words that start with
 * it (the autocomplete suggestions), in sorted order.
 *
 * Example:
 * words: ["cat","car","card","dog","do"]
 * suggest("ca") -> [car, card, cat]
 * suggest("do") -> [do, dog]
 *
 * Approach: a trie is perfect for this. insert all words. to autocomplete a
 *           prefix, first walk down to the node where the prefix ends. then do a
 *           DFS from that node collecting every word underneath it (those are
 *           exactly the words sharing the prefix). because we explore children in
 *           a..z order, results come out sorted for free.
 *
 * Time: O(L) to find the prefix + O(k) to gather k matches
 * Space: O(total chars)
 *
 * Topics: trie, DFS, strings, autocomplete
 */

import java.util.ArrayList;
import java.util.List;

public class AutocompleteSystem {

    static class Node {
        Node[] children = new Node[26];
        boolean isEnd;
    }

    Node root = new Node();

    void insert(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (cur.children[i] == null) cur.children[i] = new Node();
            cur = cur.children[i];
        }
        cur.isEnd = true;
    }

    List<String> suggest(String prefix) {
        List<String> res = new ArrayList<>();
        Node cur = root;
        for (char c : prefix.toCharArray()) {       // walk to end of prefix
            int i = c - 'a';
            if (cur.children[i] == null) return res; // nothing matches
            cur = cur.children[i];
        }
        collect(cur, new StringBuilder(prefix), res); // gather everything below
        return res;
    }

    // DFS collecting all complete words under this node
    void collect(Node node, StringBuilder path, List<String> res) {
        if (node.isEnd) res.add(path.toString());
        for (int i = 0; i < 26; i++) {              // a..z -> sorted output
            if (node.children[i] != null) {
                path.append((char) ('a' + i));
                collect(node.children[i], path, res);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        AutocompleteSystem ac = new AutocompleteSystem();
        for (String w : new String[]{"cat","car","card","dog","do"}) ac.insert(w);
        System.out.println("suggest(ca): " + ac.suggest("ca")); // expected: [car, card, cat]
        System.out.println("suggest(do): " + ac.suggest("do")); // expected: [do, dog]
        System.out.println("suggest(z): " + ac.suggest("z"));   // expected: []
    }
}
