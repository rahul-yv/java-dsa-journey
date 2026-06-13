/*
 * Problem: Prim's Minimum Spanning Tree
 *
 * Same goal as Kruskal - minimum weight to connect all vertices - but grown a
 * different way.
 *
 * Example:
 *   edges: (0-1,10) (0-2,6) (0-3,5) (1-3,15) (2-3,4)
 * MST weight = 19
 *
 * Approach: grow the tree from one starting vertex. keep a min-heap of edges that
 *           cross from "inside the tree" to "outside". repeatedly pull the cheapest
 *           crossing edge whose target isn't in the tree yet, add that vertex,
 *           then push its outgoing edges. it's like Dijkstra but we care about the
 *           single edge weight to reach a node, not the cumulative path.
 *
 * Time: O(E log V)
 * Space: O(V + E)
 *
 * Topics: graph, MST, Prim, priority queue, greedy
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsAlgorithm {

    @SuppressWarnings("unchecked")
    static int mstWeight(int v, int[][] edges) {
        List<int[]>[] adj = new List[v];
        for (int i = 0; i < v; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {              // undirected
            adj[e[0]].add(new int[]{e[1], e[2]});
            adj[e[1]].add(new int[]{e[0], e[2]});
        }

        boolean[] inTree = new boolean[v];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [node, weightToReach]
        pq.offer(new int[]{0, 0});  // start at vertex 0, cost 0

        int total = 0;
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int node = top[0], w = top[1];
            if (inTree[node]) continue;   // already added
            inTree[node] = true;
            total += w;
            for (int[] edge : adj[node]) {
                if (!inTree[edge[0]]) pq.offer(new int[]{edge[0], edge[1]});
            }
        }
        return total;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1,10},{0,2,6},{0,3,5},{1,3,15},{2,3,4}};
        System.out.println("MST weight: " + mstWeight(4, edges)); // expected: 19
    }
}
