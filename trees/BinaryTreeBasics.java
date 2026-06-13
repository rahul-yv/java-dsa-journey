/*
 * Problem: Binary Tree Basics + Traversals
 *
 * Build a binary tree and do the three classic depth-first traversals:
 *   - inorder   (left, node, right)
 *   - preorder  (node, left, right)
 *   - postorder (left, right, node)
 *
 * Example tree:
 *        1
 *       / \
 *      2   3
 *     / \
 *    4   5
 * inorder:   4 2 5 1 3
 * preorder:  1 2 4 5 3
 * postorder: 4 5 2 3 1
 *
 * Approach: each node points to a left and right child. the traversals are just
 *           recursion - the ONLY difference between them is WHEN you visit the
 *           node (print it) relative to recursing into the children. that little
 *           reordering is the whole trick, it confused me until I saw it that way.
 *
 * Time: O(n)
 * Space: O(h)  h = tree height (recursion stack)
 *
 * Topics: binary tree, recursion, DFS traversal
 */

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeBasics {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static void inorder(Node n, List<Integer> out) {
        if (n == null) return;
        inorder(n.left, out);
        out.add(n.val);          // visit in the MIDDLE
        inorder(n.right, out);
    }

    static void preorder(Node n, List<Integer> out) {
        if (n == null) return;
        out.add(n.val);          // visit FIRST
        preorder(n.left, out);
        preorder(n.right, out);
    }

    static void postorder(Node n, List<Integer> out) {
        if (n == null) return;
        postorder(n.left, out);
        postorder(n.right, out);
        out.add(n.val);          // visit LAST
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        List<Integer> in = new ArrayList<>();   inorder(root, in);
        List<Integer> pre = new ArrayList<>();  preorder(root, pre);
        List<Integer> post = new ArrayList<>(); postorder(root, post);

        System.out.println("inorder:   " + in);   // expected: [4, 2, 5, 1, 3]
        System.out.println("preorder:  " + pre);  // expected: [1, 2, 4, 5, 3]
        System.out.println("postorder: " + post); // expected: [4, 5, 2, 3, 1]
    }
}
