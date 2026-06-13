/*
 * Problem: Kth Smallest Element in a BST
 *
 * Return the kth smallest value (1-indexed) in a binary search tree.
 *
 * Example:
 *      3
 *     / \
 *    1   4
 *     \
 *      2
 * k = 1 -> 1
 *
 * Approach: key insight - an INORDER traversal of a BST visits values in sorted
 *           order. so just do inorder and stop at the kth value visited. I keep a
 *           counter; when it reaches k I record the answer. no need to collect the
 *           whole list, can bail early.
 *
 * Time: O(h + k)
 * Space: O(h)
 *
 * Topics: BST, inorder traversal, recursion
 */
public class KthSmallestInBST {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static int count;
    static int answer;

    static int kthSmallest(Node root, int k) {
        count = 0;
        answer = -1;
        inorder(root, k);
        return answer;
    }

    static void inorder(Node n, int k) {
        if (n == null || answer != -1) return;   // bail once we've found it
        inorder(n.left, k);
        count++;
        if (count == k) { answer = n.val; return; }
        inorder(n.right, k);
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(1);
        root.right = new Node(4);
        root.left.right = new Node(2);

        System.out.println("Test 1 (k=1): " + kthSmallest(root, 1)); // expected: 1
        System.out.println("Test 2 (k=2): " + kthSmallest(root, 2)); // expected: 2
        System.out.println("Test 3 (k=4): " + kthSmallest(root, 4)); // expected: 4
    }
}
