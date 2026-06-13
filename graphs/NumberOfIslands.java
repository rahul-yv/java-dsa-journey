/*
 * Problem: Number of Islands
 *
 * Grid of '1' (land) and '0' (water). An island is land connected horizontally
 * or vertically. Count the islands.
 *
 * Example:
 * Input:
 *   1 1 0 0 0
 *   1 1 0 0 0
 *   0 0 1 0 0
 *   0 0 0 1 1
 * Output: 3
 *
 * Approach: the grid IS a graph - each land cell is a node connected to its 4
 *           land neighbours. walk every cell; when we hit an unvisited '1', that's
 *           a new island, so count it and flood-fill (DFS) the whole connected
 *           landmass, sinking it to '0' so we don't count it again. number of
 *           flood-fills = number of islands.
 *
 * Time: O(m*n)
 * Space: O(m*n) recursion worst case
 *
 * Topics: graph, DFS, flood fill, matrix
 */
public class NumberOfIslands {

    static int solve(char[][] grid) {
        if (grid.length == 0) return 0;
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    sink(grid, r, c);  // remove this whole island
                }
            }
        }
        return count;
    }

    static void sink(char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != '1') return;
        grid[r][c] = '0';   // mark visited by sinking it
        sink(grid, r + 1, c);
        sink(grid, r - 1, c);
        sink(grid, r, c + 1);
        sink(grid, r, c - 1);
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println("Test 1: " + solve(grid)); // expected: 3

        char[][] one = {{'1','1','1'},{'0','1','0'},{'1','1','1'}};
        System.out.println("Test 2: " + solve(one));  // expected: 1
    }
}
