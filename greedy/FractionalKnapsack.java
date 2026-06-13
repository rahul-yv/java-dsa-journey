/*
 * Problem: Fractional Knapsack
 *
 * Like 0/1 knapsack, but you CAN take fractions of an item. Maximize value in a
 * bag of capacity W.
 *
 * Example:
 * values = [60,100,120], weights = [10,20,30], W = 50
 * Output: 240.0
 *
 * Approach: greedy works here (unlike 0/1 knapsack, which needs DP). compute each
 *           item's value-per-weight ratio, sort items by that ratio descending,
 *           and keep grabbing the most "valuable per kg" item. when an item doesn't
 *           fully fit, take the fraction that does and stop. being allowed to take
 *           fractions is exactly what makes greedy optimal here.
 *
 * Time: O(n log n)
 * Space: O(n)
 *
 * Topics: greedy, sorting, knapsack
 */

import java.util.Arrays;

public class FractionalKnapsack {

    static double solve(int[] values, int[] weights, int W) {
        int n = values.length;
        double[][] items = new double[n][2];   // [ratio, weight]... store value too
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;

        // sort indices by value/weight ratio, high to low
        Arrays.sort(idx, (a, b) ->
            Double.compare((double) values[b] / weights[b], (double) values[a] / weights[a]));

        double total = 0;
        int remaining = W;
        for (int i : idx) {
            if (remaining <= 0) break;
            if (weights[i] <= remaining) {
                total += values[i];          // take the whole item
                remaining -= weights[i];
            } else {
                total += values[i] * ((double) remaining / weights[i]); // take a fraction
                remaining = 0;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{60,100,120}, new int[]{10,20,30}, 50)); // 240.0
        System.out.println("Test 2: " + solve(new int[]{500}, new int[]{30}, 10)); // 166.66...
    }
}
