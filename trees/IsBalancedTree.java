/*
 * Problem: Balanced Binary Tree
 *
 * A tree is height-balanced if for EVERY node, the left and right subtree heights
 * differ by at most 1. Return true/false.
 *
 * Example:
 *        3
 *       / \
 *      9  20
 *        /  \
 *       15   7      -> balanced (true)
 *
 * Approach: naive way checks balance at each node and recomputes heights = O(n^2).
 *           better: one bottom-up recursion that returns the height, but returns
 *           -1 as a "sentinel" the moment it finds an unbalanced subtree. once a
 *           -1 bubbles up we just keep propagating it. clean O(n).
 *
 * Time: O(n)
 * Space: O(h)
 *
 * Topics: binary tree, recursion, DFS
 */
public class IsBalancedTree {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static boolean isBalanced(Node root) {
        return check(root) != -1;
    }

    // returns height, or -1 if this subtree is unbalanced
    static int check(Node n) {
        if (n == null) return 0;
        int left = check(n.left);
        if (left == -1) return -1;        // left already unbalanced
        int right = check(n.right);
        if (right == -1) return -1;       // right already unbalanced
        if (Math.abs(left - right) > 1) return -1;  // this node is unbalanced
        return 1 + Math.max(left, right);
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);
        System.out.println("Test 1: " + isBalanced(root)); // expected: true

        // skewed tree: 1 -> 2 -> 3 (left chain) is unbalanced
        Node skew = new Node(1);
        skew.left = new Node(2);
        skew.left.left = new Node(3);
        System.out.println("Test 2: " + isBalanced(skew)); // expected: false
    }
}
