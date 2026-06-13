/*
 * Problem: Binary Tree Level Order Traversal (BFS)
 *
 * Return the node values level by level, top to bottom, left to right.
 *
 * Example tree:
 *        3
 *       / \
 *      9  20
 *        /  \
 *       15   7
 * Output: [[3], [9, 20], [15, 7]]
 *
 * Approach: this is breadth-first search using a queue. push the root, then while
 *           the queue isn't empty, process exactly one level at a time: note how
 *           many nodes are in the queue right now (that's the current level size),
 *           pop them all, collect their values, and enqueue their children for the
 *           next level. capturing levelSize up front is the key to grouping levels.
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

public class LevelOrderTraversal {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int levelSize = q.size();        // nodes on this level right now
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                Node node = q.poll();
                level.add(node.val);
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            res.add(level);
        }
        return res;
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);
        System.out.println("Test 1: " + levelOrder(root)); // expected: [[3], [9, 20], [15, 7]]
        System.out.println("Test 2: " + levelOrder(null)); // expected: []
    }
}
