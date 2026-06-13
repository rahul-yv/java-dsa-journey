/*
 * Problem: Sieve of Eratosthenes
 *
 * Find all prime numbers up to n efficiently.
 *
 * Example:
 * Input: n = 30
 * Output: [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
 *
 * Approach: instead of testing each number for primality, flip it around: assume
 *           everything is prime, then for each prime p, cross out all its multiples
 *           (2p, 3p, ...) - those can't be prime. start crossing from p*p (smaller
 *           multiples were already crossed by smaller primes). whatever's left
 *           uncrossed is prime. way faster than checking each number one by one.
 *
 * Time: O(n log log n)
 * Space: O(n)
 *
 * Topics: math, primes, sieve
 */

import java.util.ArrayList;
import java.util.List;

public class SieveOfEratosthenes {

    static List<Integer> solve(int n) {
        boolean[] notPrime = new boolean[n + 1];   // false = still considered prime
        List<Integer> primes = new ArrayList<>();

        for (int p = 2; p <= n; p++) {
            if (!notPrime[p]) {
                primes.add(p);
                // cross out multiples of p, starting from p*p (use long to avoid overflow)
                for (long m = (long) p * p; m <= n; m += p) notPrime[(int) m] = true;
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(30)); // [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        System.out.println("Test 2: " + solve(10)); // [2, 3, 5, 7]
        System.out.println("Test 3: " + solve(1));  // []
    }
}
