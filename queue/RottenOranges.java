/*
 * Problem: Rotten Oranges
 *
 * Grid: 0 = empty, 1 = fresh orange, 2 = rotten. Every minute, a rotten orange
 * rots its 4-directional fresh neighbors. Return minutes until no fresh orange
 * remains, or -1 if some can never rot.
 *
 * Example:
 * Input: [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Approach: this is multi-source BFS. start by putting ALL rotten oranges into
 *           the queue at once (minute 0). then process level by level - each BFS
 *           level is one minute - rotting fresh neighbors and counting them down.
 *           at the end if any fresh orange is left it's unreachable, return -1.
 *
 * Time: O(m*n)
 * Space: O(m*n)
 *
 * Topics: BFS, queue, matrix
 */

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

    static int solve(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;

        // seed the queue with every rotten orange
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) queue.add(new int[]{r, c});
                else if (grid[r][c] == 1) fresh++;
            }

        if (fresh == 0) return 0; // nothing to rot

        int minutes = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!queue.isEmpty() && fresh > 0) {
            int levelSize = queue.size();   // all oranges rotten so far this minute
            for (int i = 0; i < levelSize; i++) {
                int[] cell = queue.poll();
                for (int[] d : dirs) {
                    int nr = cell[0] + d[0], nc = cell[1] + d[1];
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;       // rot it
                        fresh--;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
            minutes++;
        }
        return fresh == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[][]{{2,1,1},{1,1,0},{0,1,1}})); // expected: 4
        System.out.println("Test 2: " + solve(new int[][]{{2,1,1},{0,1,1},{1,0,1}})); // expected: -1
        System.out.println("Test 3: " + solve(new int[][]{{0,2}}));                    // expected: 0
    }
}
