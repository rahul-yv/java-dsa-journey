/*
 * Problem: Detect Cycle in an Undirected Graph
 *
 * Return true if the undirected graph contains a cycle.
 *
 * Example:
 *   0 - 1 - 2 - 0   -> has a cycle (true)
 *   0 - 1 - 2       -> no cycle (false)
 *
 * Approach: DFS while remembering where you came from (the parent). if you reach
 *           an already-visited neighbour that ISN'T the node you just came from,
 *           that's a cycle - you found another way back to a visited node. the
 *           "ignore the parent" part is what stops the trivial 0-1 edge from
 *           looking like a cycle.
 *
 * Time: O(V + E)
 * Space: O(V)
 *
 * Topics: graph, DFS, cycle detection
 */

import java.util.ArrayList;
import java.util.List;

public class DetectCycleUndirectedGraph {

    static boolean hasCycle(int v, List<List<Integer>> adj) {
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {           // handle disconnected components
            if (!visited[i] && dfs(i, -1, adj, visited)) return true;
        }
        return false;
    }

    static boolean dfs(int node, int parent, List<List<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        for (int nb : adj.get(node)) {
            if (!visited[nb]) {
                if (dfs(nb, node, adj, visited)) return true;
            } else if (nb != parent) {
                return true;   // visited and not the parent -> cycle
            }
        }
        return false;
    }

    static List<List<Integer>> build(int v, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) { adj.get(e[0]).add(e[1]); adj.get(e[1]).add(e[0]); }
        return adj;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + hasCycle(3, build(3, new int[][]{{0,1},{1,2},{2,0}}))); // true
        System.out.println("Test 2: " + hasCycle(3, build(3, new int[][]{{0,1},{1,2}})));       // false
    }
}
