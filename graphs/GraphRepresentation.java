/*
 * Problem: Graph Representation
 *
 * Show the two common ways to store a graph: adjacency LIST and adjacency MATRIX.
 * Build the same small graph both ways and print the neighbours of each vertex.
 *
 * Example graph (undirected), 4 vertices:
 *   0 - 1
 *   0 - 2
 *   1 - 2
 *   2 - 3
 *
 * Approach:
 *   - adjacency list: for each vertex keep a list of its neighbours. great when
 *     the graph is sparse (few edges), uses O(V + E) space. this is what I use 90%
 *     of the time.
 *   - adjacency matrix: a V x V grid where matrix[a][b] = 1 means an edge a-b.
 *     O(1) edge lookup but O(V^2) space, wasteful for sparse graphs.
 *
 * Topics: graph, adjacency list, adjacency matrix
 */

import java.util.ArrayList;
import java.util.List;

public class GraphRepresentation {

    static List<List<Integer>> buildList(int v, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);   // undirected -> both directions
        }
        return adj;
    }

    static int[][] buildMatrix(int v, int[][] edges) {
        int[][] m = new int[v][v];
        for (int[] e : edges) {
            m[e[0]][e[1]] = 1;
            m[e[1]][e[0]] = 1;
        }
        return m;
    }

    public static void main(String[] args) {
        int v = 4;
        int[][] edges = {{0,1},{0,2},{1,2},{2,3}};

        List<List<Integer>> adj = buildList(v, edges);
        System.out.println("adjacency list:");
        for (int i = 0; i < v; i++) System.out.println("  " + i + " -> " + adj.get(i));

        int[][] m = buildMatrix(v, edges);
        System.out.println("adjacency matrix:");
        for (int[] row : m) System.out.println("  " + java.util.Arrays.toString(row));
        // vertex 2 should connect to 0, 1, 3
    }
}
