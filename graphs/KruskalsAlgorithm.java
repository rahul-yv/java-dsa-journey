/*
 * Problem: Kruskal's Minimum Spanning Tree
 *
 * Find the minimum total weight needed to connect all vertices (a spanning tree
 * with the smallest possible sum of edge weights).
 *
 * Example:
 *   edges: (0-1,10) (0-2,6) (0-3,5) (1-3,15) (2-3,4)
 * MST weight = 19   (picks 2-3=4, 0-3=5, 0-1=10)
 *
 * Approach: sort all edges by weight, then greedily add the cheapest edge that
 *           DOESN'T create a cycle. "does it create a cycle?" is answered with a
 *           Union-Find (DSU) structure: two endpoints already in the same set ->
 *           adding the edge would loop, so skip it. otherwise union them and take
 *           the edge. stop after V-1 edges. union-find with path compression is
 *           the part worth learning here.
 *
 * Time: O(E log E)
 * Space: O(V)
 *
 * Topics: graph, MST, Kruskal, union-find / DSU, greedy
 */

import java.util.Arrays;

public class KruskalsAlgorithm {

    static int[] parent;

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);  // path compression
        return parent[x];
    }

    static boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;   // same set -> would make a cycle
        parent[ra] = rb;
        return true;
    }

    // edges as [u, v, weight]
    static int mstWeight(int v, int[][] edges) {
        Arrays.sort(edges, (x, y) -> x[2] - y[2]);  // cheapest first
        parent = new int[v];
        for (int i = 0; i < v; i++) parent[i] = i;

        int total = 0, used = 0;
        for (int[] e : edges) {
            if (union(e[0], e[1])) {   // edge connects two different components
                total += e[2];
                if (++used == v - 1) break;  // MST complete
            }
        }
        return total;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1,10},{0,2,6},{0,3,5},{1,3,15},{2,3,4}};
        System.out.println("MST weight: " + mstWeight(4, edges)); // expected: 19
    }
}
