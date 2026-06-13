/*
 * Problem: Huffman Coding
 *
 * Given characters and their frequencies, build an optimal prefix code (the
 * Huffman tree) that minimizes the total encoded length. Print each character's
 * binary code.
 *
 * Example:
 * chars = a,b,c,d,e,f  freqs = 5,9,12,13,16,45
 * Output: codes where frequent chars get SHORT codes (f gets the shortest)
 *
 * Approach: greedy with a min-heap. put every character in as a leaf node keyed by
 *           frequency. repeatedly pull the TWO smallest-frequency nodes and merge
 *           them under a new parent whose frequency is their sum; push the parent
 *           back. keep going until one tree remains. then walk the tree assigning
 *           0 for left, 1 for right - that path is each char's code. merging the
 *           two rarest first is what makes the codes optimal.
 *
 * Time: O(n log n)
 * Space: O(n)
 *
 * Topics: greedy, heap, tree, encoding
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {

    static class Node {
        char ch;
        int freq;
        Node left, right;
        Node(char ch, int freq) { this.ch = ch; this.freq = freq; }
        Node(int freq, Node l, Node r) { this.freq = freq; this.left = l; this.right = r; }
    }

    static Map<Character, String> build(char[] chars, int[] freqs) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.freq - b.freq);
        for (int i = 0; i < chars.length; i++) pq.offer(new Node(chars[i], freqs[i]));

        while (pq.size() > 1) {
            Node a = pq.poll();      // two rarest
            Node b = pq.poll();
            pq.offer(new Node(a.freq + b.freq, a, b));   // merge under a parent
        }

        Map<Character, String> codes = new HashMap<>();
        assign(pq.poll(), "", codes);
        return codes;
    }

    static void assign(Node node, String code, Map<Character, String> codes) {
        if (node == null) return;
        if (node.left == null && node.right == null) {   // leaf = a real character
            codes.put(node.ch, code.isEmpty() ? "0" : code);
            return;
        }
        assign(node.left, code + "0", codes);
        assign(node.right, code + "1", codes);
    }

    public static void main(String[] args) {
        char[] chars = {'a','b','c','d','e','f'};
        int[] freqs = {5,9,12,13,16,45};
        Map<Character, String> codes = build(chars, freqs);
        for (char c : chars) System.out.println(c + " -> " + codes.get(c));
        // 'f' (most frequent) should get the shortest code
    }
}
