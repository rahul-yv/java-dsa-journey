/*
 * Problem: Job Sequencing with Deadlines
 *
 * Each job has a deadline and a profit, and takes 1 unit of time. You can do one
 * job per time slot. Schedule jobs to maximize total profit (a job earns profit
 * only if finished by its deadline).
 *
 * Example:
 * jobs (deadline, profit): (4,70) (1,80) (1,30) (1,100) (1,20)
 * Output: profit 170   (do job with profit 100 at slot 1, job 70 at slot... )
 *
 * Approach: greedy. sort jobs by profit DESCENDING (most profitable first). for
 *           each job, try to place it in the LATEST free time slot at or before its
 *           deadline. scheduling it as late as possible keeps earlier slots open for
 *           other tight-deadline jobs. a boolean[] tracks which slots are taken.
 *
 * Time: O(n^2) (simple version)
 * Space: O(maxDeadline)
 *
 * Topics: greedy, sorting, scheduling
 */

import java.util.Arrays;

public class JobSequencing {

    // jobs as [deadline, profit]
    static int solve(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> b[1] - a[1]);   // highest profit first

        int maxDeadline = 0;
        for (int[] j : jobs) maxDeadline = Math.max(maxDeadline, j[0]);

        boolean[] slot = new boolean[maxDeadline + 1];  // slot[t] = is time t taken
        int profit = 0;

        for (int[] j : jobs) {
            // place at the latest free slot <= deadline
            for (int t = j[0]; t >= 1; t--) {
                if (!slot[t]) {
                    slot[t] = true;
                    profit += j[1];
                    break;
                }
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        int[][] jobs = {{4,70},{1,80},{1,30},{1,100},{1,20}};
        System.out.println("Test 1: " + solve(jobs)); // expected: 170

        int[][] jobs2 = {{2,100},{1,19},{2,27},{1,25},{3,15}};
        System.out.println("Test 2: " + solve(jobs2)); // expected: 142
    }
}
