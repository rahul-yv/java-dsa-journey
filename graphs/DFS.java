/*
 * Problem: Depth First Search (DFS) on a graph
 *
 * Visit all vertices reachable from a start, going as deep as possible before
 * backtracking. Return the visit order.
 *
 * Example graph:
 *   0 - 1, 0 - 2, 1 - 3, 2 - 3, 3 - 4
 * DFS from 0: [0, 1, 3, 2, 4]   (depends on neighbour order)
 *
 * Approach: recursion is the natural fit. visit a node, mark it, then recurse into
 *           each unvisited neighbour. the call stack handles the "go deep then
 *           come back" automatically. (you can also do it iteratively with an
 *           explicit stack - same idea.)
 *
 * Time: O(V + E)
 * Space: O(V)
 *
 * Topics: graph, DFS, recursion
 */

import java.util.ArrayList;
import java.util.List;

public class DFS {

    static List<Integer> dfs(int start, List<List<Integer>> adj) {
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        explore(start, adj, visited, order);
        return order;
    }

    static void explore(int node, List<List<Integer>> adj, boolean[] visited, List<Integer> order) {
        visited[node] = true;
        order.add(node);
        for (int nb : adj.get(node)) {
            if (!visited[nb]) explore(nb, adj, visited, order);  // go deeper
        }
    }

    public static void main(String[] args) {
        int v = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) adj.add(new ArrayList<>());
        int[][] edges = {{0,1},{0,2},{1,3},{2,3},{3,4}};
        for (int[] e : edges) { adj.get(e[0]).add(e[1]); adj.get(e[1]).add(e[0]); }

        System.out.println("DFS from 0: " + dfs(0, adj)); // expected: [0, 1, 3, 2, 4]
    }
}
