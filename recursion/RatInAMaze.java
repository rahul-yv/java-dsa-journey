/*
 * Problem: Rat in a Maze
 *
 * A rat sits at top-left (0,0) of an n x n grid and wants to reach bottom-right.
 * 1 = open cell, 0 = blocked. It can move Down, Left, Right, Up. Find all paths
 * as strings like "DDRDRR".
 *
 * Example:
 * Input:
 *   1 0 0 0
 *   1 1 0 1
 *   1 1 0 0
 *   0 1 1 1
 * Output: [DDRDRR, DRDDRR]
 *
 * Approach: backtracking on the grid. from each cell try all 4 directions in a
 *           fixed order (D, L, R, U so output is sorted-ish), mark the cell as
 *           visited so we don't loop, recurse, then unmark. when we reach the
 *           bottom-right cell, save the path string we built up.
 *
 * Time: O(4^(n*n)) worst case
 * Space: O(n*n)
 *
 * Topics: backtracking, recursion, matrix
 */

import java.util.ArrayList;
import java.util.List;

public class RatInAMaze {

    static List<String> solve(int[][] maze) {
        List<String> paths = new ArrayList<>();
        int n = maze.length;
        boolean[][] visited = new boolean[n][n];
        if (maze[0][0] == 1) walk(maze, 0, 0, "", visited, paths);
        return paths;
    }

    static void walk(int[][] m, int r, int c, String path, boolean[][] vis, List<String> paths) {
        int n = m.length;
        if (r == n - 1 && c == n - 1) {     // reached the exit
            paths.add(path);
            return;
        }
        vis[r][c] = true;

        // directions in D, L, R, U order
        int[] dr = {1, 0, 0, -1};
        int[] dc = {0, -1, 1, 0};
        char[] dirCh = {'D', 'L', 'R', 'U'};

        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k], nc = c + dc[k];
            if (nr >= 0 && nr < n && nc >= 0 && nc < n && m[nr][nc] == 1 && !vis[nr][nc]) {
                walk(m, nr, nc, path + dirCh[k], vis, paths);
            }
        }
        vis[r][c] = false;   // backtrack so other paths can use this cell
    }

    public static void main(String[] args) {
        int[][] maze = {
            {1,0,0,0},
            {1,1,0,1},
            {1,1,0,0},
            {0,1,1,1}
        };
        System.out.println("Test 1: " + solve(maze)); // expected: [DDRDRR, DRDDRR]
        System.out.println("Test 2: " + solve(new int[][]{{1,0},{0,1}})); // expected: [] (no path)
    }
}
