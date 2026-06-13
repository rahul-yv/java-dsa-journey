/*
 * Problem: Set Matrix Zeroes
 *
 * If an element in the matrix is 0, set its entire row and column to 0.
 * Do it in place.
 *
 * Example:
 * Input:  [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 *
 * Approach: easy way uses extra row+col sets to remember which to zero -> O(m+n) space.
 *           the slick O(1) trick: use the first row and first col of the matrix
 *           itself as the "remember" markers. need one extra flag for the first
 *           column because it overlaps with cell [0][0]. a little fiddly but cool.
 *
 * Time: O(m*n)
 * Space: O(1)
 *
 * Topics: matrix, in-place
 */

import java.util.Arrays;

public class SetMatrixZeroes {

    static int[][] solve(int[][] m) {
        int rows = m.length, cols = m[0].length;
        boolean firstColZero = false;

        for (int r = 0; r < rows; r++) {
            if (m[r][0] == 0) firstColZero = true;   // handle col 0 separately
            for (int c = 1; c < cols; c++) {
                if (m[r][c] == 0) {
                    m[r][0] = 0;   // mark this row
                    m[0][c] = 0;   // mark this col
                }
            }
        }

        // use the markers (go from bottom-right so we don't clobber markers early)
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = cols - 1; c >= 1; c--) {
                if (m[r][0] == 0 || m[0][c] == 0) m[r][c] = 0;
            }
            if (firstColZero) m[r][0] = 0;
        }
        return m;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + Arrays.deepToString(solve(new int[][]{{1,1,1},{1,0,1},{1,1,1}})));
        // expected: [[1, 0, 1], [0, 0, 0], [1, 0, 1]]
        System.out.println("Test 2: " + Arrays.deepToString(solve(new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}})));
        // expected: [[0, 0, 0, 0], [0, 4, 5, 0], [0, 3, 1, 0]]
    }
}
