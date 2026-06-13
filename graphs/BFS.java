/*
 * Problem: Breadth First Search (BFS) on a graph
 *
 * Visit all vertices reachable from a start vertex, level by level (closest
 * first). Return the visit order.
 *
 * Example graph:
 *   0 - 1, 0 - 2, 1 - 3, 2 - 3, 3 - 4
 * BFS from 0: [0, 1, 2, 3, 4]
 *
 * Approach: use a queue. start by visiting the start vertex and adding it to the
 *           queue. then repeatedly pop a vertex, and for each unvisited neighbour
 *           mark it visited and add it to the queue. the visited[] set stops us
 *           from revisiting and looping forever. "mark visited when you ENQUEUE"
 *           (not when you dequeue) so you don't add a node twice.
 *
 * Time: O(V + E)
 * Space: O(V)
 *
 * Topics: graph, BFS, queue
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    static List<Integer> bfs(int start, List<List<Integer>> adj) {
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.add(start);
        while (!q.isEmpty()) {
            int node = q.poll();
            order.add(node);
            for (int nb : adj.get(node)) {
                if (!visited[nb]) {
                    visited[nb] = true;   // mark on enqueue
                    q.add(nb);
                }
            }
        }
        return order;
    }

    public static void main(String[] args) {
        int v = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) adj.add(new ArrayList<>());
        int[][] edges = {{0,1},{0,2},{1,3},{2,3},{3,4}};
        for (int[] e : edges) { adj.get(e[0]).add(e[1]); adj.get(e[1]).add(e[0]); }

        System.out.println("BFS from 0: " + bfs(0, adj)); // expected: [0, 1, 2, 3, 4]
    }
}
