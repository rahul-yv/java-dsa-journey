/*
 * Problem: Invert Binary Tree  (LeetCode #226 - Easy)
 *
 * Mirror a binary tree - swap every node's left and right child.
 *
 * Example:
 *      4               4
 *     / \             / \
 *    2   7    ->      7   2
 *   / \ / \          / \ / \
 *  1  3 6  9         9  6 3  1
 *
 * Approach: dead simple recursion. swap the current node's two children, then
 *           recurse into both. (this is the famous "couldn't invert a binary tree
 *           so no job at Google" tweet problem - it's honestly just 3 lines.)
 *
 * Time: O(n)
 * Space: O(h)
 *
 * Topics: binary tree, recursion
 */
public class InvertBinaryTree {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static Node invert(Node root) {
        if (root == null) return null;
        Node temp = root.left;          // swap the children
        root.left = invert(root.right);
        root.right = invert(temp);
        return root;
    }

    // inorder print just to show it flipped
    static void inorder(Node n, StringBuilder sb) {
        if (n == null) return;
        inorder(n.left, sb);
        sb.append(n.val).append(" ");
        inorder(n.right, sb);
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(7);
        root.left.left = new Node(1);
        root.left.right = new Node(3);

        StringBuilder before = new StringBuilder(); inorder(root, before);
        System.out.println("inorder before: " + before.toString().trim()); // 1 2 3 4 7
        invert(root);
        StringBuilder after = new StringBuilder(); inorder(root, after);
        System.out.println("inorder after:  " + after.toString().trim());  // 7 4 3 2 1
    }
}
