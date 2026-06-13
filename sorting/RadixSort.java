/*
 * Problem: Radix Sort
 *
 * Sort non-negative integers digit by digit, from least significant digit to
 * most significant.
 *
 * Example:
 * Input:  [170, 45, 75, 90, 802, 24, 2, 66]
 * Output: [2, 24, 45, 66, 75, 90, 170, 802]
 *
 * Approach: sort by the 1s digit, then the 10s digit, then 100s, and so on.
 *           the catch is each pass must be STABLE (keep equal-digit items in
 *           their previous relative order), so we use counting sort on each digit.
 *           after we've gone through the highest digit, everything's sorted.
 *           weird at first but it actually works, trust the process.
 *
 * Time: O(d * (n + b))   d = number of digits, b = base (10)
 * Space: O(n + b)
 *
 * Topics: sorting, counting sort, non-comparison
 */

import java.util.Arrays;

public class RadixSort {

    static int[] sort(int[] a) {
        if (a.length == 0) return a;
        int max = a[0];
        for (int x : a) max = Math.max(max, x);

        // exp = 1, 10, 100, ... one stable counting-sort pass per digit
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingByDigit(a, exp);
        }
        return a;
    }

    static void countingByDigit(int[] a, int exp) {
        int n = a.length;
        int[] output = new int[n];
        int[] count = new int[10];   // digits 0..9

        for (int x : a) count[(x / exp) % 10]++;
        // turn counts into positions (prefix sums)
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];

        // go right-to-left to keep it stable
        for (int i = n - 1; i >= 0; i--) {
            int digit = (a[i] / exp) % 10;
            output[--count[digit]] = a[i];
        }
        System.arraycopy(output, 0, a, 0, n);
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + Arrays.toString(sort(new int[]{170,45,75,90,802,24,2,66})));
        // [2, 24, 45, 66, 75, 90, 170, 802]
        System.out.println("Test 2: " + Arrays.toString(sort(new int[]{3,1,2}))); // [1, 2, 3]
    }
}
