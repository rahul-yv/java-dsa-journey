/*
 * Problem: Modular Arithmetic basics (with modular exponentiation)
 *
 * Show the basic modular operations used in competitive programming, especially
 * computing (base^exp) % mod for huge exponents without overflowing.
 *
 * Example:
 * Input: base = 2, exp = 10, mod = 1000
 * Output: 1024 % 1000 = 24
 *
 * Approach: the rules are: (a+b)%m = ((a%m)+(b%m))%m, and same for multiplication.
 *           the useful one is modular exponentiation: same as fast exponentiation
 *           but take % mod after every multiply so the numbers never blow up. uses
 *           long for the intermediate products. the 1_000_000_007 mod shows up
 *           constantly because it's a big prime that fits in an int after the mod.
 *
 * Time: O(log exp)
 * Space: O(1)
 *
 * Topics: math, modular arithmetic, modular exponentiation
 */
public class ModularArithmetic {

    static long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % mod;  // keep it small
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + modPow(2, 10, 1000)); // expected: 24 (1024 % 1000)
        System.out.println("Test 2: " + modPow(3, 5, 7));     // expected: 5 (243 % 7)
        // huge exponent, no overflow thanks to mod every step
        System.out.println("Test 3: " + modPow(2, 1000000000, 1000000007)); // expected: 140625001
    }
}
