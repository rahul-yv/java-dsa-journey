/*
 * Problem: Lowest Common Ancestor of a Binary Tree
 *
 * Given two nodes p and q, find their lowest (deepest) common ancestor - the
 * deepest node that has both p and q somewhere below it (a node can be its own
 * ancestor).
 *
 * Example tree:
 *        3
 *       / \
 *      5   1
 *     / \
 *    6   2
 * LCA(6, 2) = 5,  LCA(6, 1) = 3
 *
 * Approach: recurse. if the current node is null or equals p or q, return it.
 *           recurse left and right. if BOTH sides return non-null, then p and q
 *           are split across this node, so THIS node is the LCA. if only one side
 *           returns something, the LCA is down that side. took a couple reads to
 *           trust this but it's beautifully short.
 *
 * Time: O(n)
 * Space: O(h)
 *
 * Topics: binary tree, recursion, DFS
 */
public class LowestCommonAncestor {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static Node lca(Node root, Node p, Node q) {
        if (root == null || root == p || root == q) return root;
        Node left = lca(root.left, p, q);
        Node right = lca(root.right, p, q);

        if (left != null && right != null) return root;  // split point -> this is LCA
        return (left != null) ? left : right;            // both on one side
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        Node five = new Node(5);
        Node one = new Node(1);
        Node six = new Node(6);
        Node two = new Node(2);
        root.left = five; root.right = one;
        five.left = six;  five.right = two;

        System.out.println("Test 1 LCA(6,2): " + lca(root, six, two).val); // expected: 5
        System.out.println("Test 2 LCA(6,1): " + lca(root, six, one).val); // expected: 3
        System.out.println("Test 3 LCA(5,2): " + lca(root, five, two).val); // expected: 5
    }
}
