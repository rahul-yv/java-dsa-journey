/*
 * Problem: Search a 2D Matrix
 *
 * Matrix where each row is sorted left to right, and the first element of each
 * row is bigger than the last of the previous row. Find target. O(log(m*n)).
 *
 * Example:
 * Input: [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 *
 * Approach: because of those two properties, the whole matrix is basically one
 *           big sorted array wrapped into rows. so just binary search over
 *           indices 0..(m*n - 1) and convert a flat index back to row/col with
 *           div and mod. clean trick.
 *
 * Time: O(log(m*n))
 * Space: O(1)
 *
 * Topics: binary search, matrix
 */
public class SearchA2DMatrix {

    static boolean solve(int[][] m, int target) {
        int rows = m.length, cols = m[0].length;
        int lo = 0, hi = rows * cols - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int val = m[mid / cols][mid % cols];   // flat index -> (row, col)
            if (val == target) return true;
            else if (val < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] m = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        System.out.println("Test 1: " + solve(m, 3));  // expected: true
        System.out.println("Test 2: " + solve(m, 13)); // expected: false
        System.out.println("Test 3: " + solve(m, 60)); // expected: true
    }
}
