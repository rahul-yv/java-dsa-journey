/*
 * Problem: Detect Cycle in a Directed Graph
 *
 * Return true if a directed graph has a cycle.
 *
 * Example:
 *   0 -> 1 -> 2 -> 0   -> cycle (true)
 *   0 -> 1 -> 2        -> no cycle (false)
 *
 * Approach: directed cycles need a different idea than undirected. do DFS and keep
 *           TWO markers: "visited" (ever seen) and "inStack" (currently in the
 *           recursion path). if DFS hits a node that's already inStack, we've
 *           looped back along the current path -> cycle. clear inStack when the
 *           recursion for a node finishes. the inStack vs visited distinction is
 *           the key thing people miss.
 *
 * Time: O(V + E)
 * Space: O(V)
 *
 * Topics: graph, DFS, cycle detection, directed graph
 */

import java.util.ArrayList;
import java.util.List;

public class DetectCycleDirectedGraph {

    static boolean hasCycle(int v, List<List<Integer>> adj) {
        boolean[] visited = new boolean[v];
        boolean[] inStack = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i] && dfs(i, adj, visited, inStack)) return true;
        }
        return false;
    }

    static boolean dfs(int node, List<List<Integer>> adj, boolean[] visited, boolean[] inStack) {
        visited[node] = true;
        inStack[node] = true;
        for (int nb : adj.get(node)) {
            if (!visited[nb]) {
                if (dfs(nb, adj, visited, inStack)) return true;
            } else if (inStack[nb]) {
                return true;   // back-edge to a node on the current path
            }
        }
        inStack[node] = false; // done with this node's path
        return false;
    }

    static List<List<Integer>> build(int v, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(e[1]);   // directed: one way only
        return adj;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + hasCycle(3, build(3, new int[][]{{0,1},{1,2},{2,0}}))); // true
        System.out.println("Test 2: " + hasCycle(3, build(3, new int[][]{{0,1},{1,2}})));       // false
    }
}
