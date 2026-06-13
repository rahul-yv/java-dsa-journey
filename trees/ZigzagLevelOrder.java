/*
 * Problem: Binary Tree Zigzag Level Order Traversal
 *
 * Like level order, but alternate direction each level: level 0 left-to-right,
 * level 1 right-to-left, level 2 left-to-right, and so on.
 *
 * Example tree:
 *        3
 *       / \
 *      9  20
 *        /  \
 *       15   7
 * Output: [[3], [20, 9], [15, 7]]
 *
 * Approach: regular BFS with a queue, but flip a boolean each level. when the
 *           level should go right-to-left, just insert each value at the FRONT of
 *           the level list instead of the back. the tree traversal order stays the
 *           same, we only change how we record each level.
 *
 * Time: O(n)
 * Space: O(n)
 *
 * Topics: binary tree, BFS, queue
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigzagLevelOrder {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static List<List<Integer>> zigzag(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        boolean leftToRight = true;

        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                if (leftToRight) level.addLast(node.val);
                else level.addFirst(node.val);     // reverse without reversing
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            res.add(level);
            leftToRight = !leftToRight;   // flip for next level
        }
        return res;
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);
        System.out.println("Test 1: " + zigzag(root)); // expected: [[3], [20, 9], [15, 7]]
    }
}
