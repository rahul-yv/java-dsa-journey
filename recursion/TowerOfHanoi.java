/*
 * Problem: Tower of Hanoi
 * Source: Practice
 * Difficulty: Medium
 *
 * Approach:
 * Move n - 1 disks to helper, move the largest disk, then move n - 1 disks again.
 *
 * Time Complexity: O(2^n)
 * Space Complexity: O(n)
 */
public class TowerOfHanoi {

    public static void main(String[] args) {
        int disks = 3;
        solve(disks, 'A', 'C', 'B');
    }

    static void solve(int disks, char source, char destination, char helper) {
        if (disks == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }

        solve(disks - 1, source, helper, destination);
        System.out.println("Move disk " + disks + " from " + source + " to " + destination);
        solve(disks - 1, helper, destination, source);
    }
}
