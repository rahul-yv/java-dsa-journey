/*
 * Problem: Combination Sum
 *
 * Given distinct candidates and a target, find all unique combinations that sum
 * to target. The same number may be used unlimited times.
 *
 * Example:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 *
 * Approach: backtracking. at each step either keep using the current candidate
 *           (since reuse is allowed, don't advance the index) or move on to the
 *           next candidate. subtract from the remaining target as we pick. when
 *           remaining hits 0 we found a combo; if it goes negative, dead end.
 *           passing a "start" index stops us from producing the same combo in a
 *           different order.
 *
 * Time: exponential (depends on target/candidates)
 * Space: O(target) recursion depth
 *
 * Topics: backtracking, recursion, array
 */

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    static List<List<Integer>> solve(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        build(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    static void build(List<List<Integer>> res, List<Integer> cur, int[] c, int remain, int start) {
        if (remain == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        if (remain < 0) return;   // overshot

        for (int i = start; i < c.length; i++) {
            cur.add(c[i]);
            build(res, cur, c, remain - c[i], i);  // i (not i+1) -> can reuse c[i]
            cur.remove(cur.size() - 1);            // undo
        }
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{2,3,6,7}, 7)); // expected: [[2, 2, 3], [7]]
        System.out.println("Test 2: " + solve(new int[]{2,3,5}, 8));   // expected: [[2,2,2,2],[2,3,3],[3,5]]
        System.out.println("Test 3: " + solve(new int[]{2}, 1));       // expected: []
    }
}
