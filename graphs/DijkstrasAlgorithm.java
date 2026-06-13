/*
 * Problem: Dijkstra's Shortest Path
 *
 * Find the shortest distance from a source vertex to every other vertex in a
 * weighted graph with NON-NEGATIVE edge weights.
 *
 * Example:
 *   0 -(4)- 1, 0 -(1)- 2, 2 -(2)- 1, 1 -(1)- 3, 2 -(5)- 3
 * Shortest from 0: [0, 3, 1, 4]
 *
 * Approach: greedy + min-heap. keep a dist[] array (all infinity except source=0).
 *           push (dist, node) into a priority queue. always pop the closest
 *           unsettled node, and "relax" its edges: if going through it gives a
 *           shorter path to a neighbour, update that neighbour's dist and push it.
 *           because weights are non-negative, the first time we pop a node we've
 *           already found its shortest distance. doesn't work with negative edges
 *           (use Bellman-Ford for that).
 *
 * Time: O(E log V)
 * Space: O(V + E)
 *
 * Topics: graph, shortest path, Dijkstra, priority queue
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstrasAlgorithm {

    static int[] dijkstra(int v, List<int[]>[] adj, int src) {
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // min-heap of [distanceSoFar, node]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int d = top[0], node = top[1];
            if (d > dist[node]) continue;   // stale entry, skip

            for (int[] edge : adj[node]) {   // edge = [neighbour, weight]
                int nb = edge[0], w = edge[1];
                if (dist[node] + w < dist[nb]) {
                    dist[nb] = dist[node] + w;   // relax
                    pq.offer(new int[]{dist[nb], nb});
                }
            }
        }
        return dist;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int v = 4;
        List<int[]>[] adj = new List[v];
        for (int i = 0; i < v; i++) adj[i] = new ArrayList<>();
        // undirected weighted edges
        int[][] edges = {{0,1,4},{0,2,1},{2,1,2},{1,3,1},{2,3,5}};
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            adj[e[1]].add(new int[]{e[0], e[2]});
        }

        System.out.println("Shortest from 0: " + Arrays.toString(dijkstra(v, adj, 0)));
        // expected: [0, 3, 1, 4]
    }
}
