/*
 * Problem: Contains Duplicate  (LeetCode #217 - Easy)
 *
 * Return true if any value appears at least twice.
 *
 * Example:
 * Input: [1,2,3,1]
 * Output: true
 *
 * Approach: throw everything into a HashSet as you go. a set rejects duplicates,
 *           so the moment add() returns false (meaning it was already there) you've
 *           found a duplicate. easy O(n). sorting + checking neighbours also works
 *           but that's O(n log n).
 *
 * Time: O(n)
 * Space: O(n)
 *
 * Topics: hashset, array
 */

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    static boolean solve(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int n : nums) {
            if (!seen.add(n)) return true;   // add returns false if already present
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{1,2,3,1}));         // true
        System.out.println("Test 2: " + solve(new int[]{1,2,3,4}));         // false
        System.out.println("Test 3: " + solve(new int[]{1,1,1,3,3,4,3,2,4,2})); // true
    }
}
