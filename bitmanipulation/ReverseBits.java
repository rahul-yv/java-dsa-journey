/*
 * Problem: Reverse Bits
 *
 * Reverse the bits of a 32-bit unsigned integer (bit 0 swaps with bit 31, etc).
 *
 * Example:
 * Input:  0000...0101  (5)
 * Output: 1010...0000  (a big number)
 *
 * Approach: build the result bit by bit. 32 times: shift result left by 1 to make
 *           room, grab the lowest bit of n (n & 1) and OR it into result, then shift
 *           n right by 1. so the last bit of n becomes the first bit of result and
 *           so on. (in Java I use >>> for unsigned right shift so the sign bit
 *           doesn't sneak in 1s.)
 *
 * Time: O(32) = O(1)
 * Space: O(1)
 *
 * Topics: bit manipulation
 */
public class ReverseBits {

    static int solve(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;          // make room
            result |= (n & 1);     // take n's lowest bit
            n >>>= 1;              // unsigned shift n right
        }
        return result;
    }

    public static void main(String[] args) {
        // 43261596 -> 964176192
        System.out.println("Test 1: " + solve(43261596)); // expected: 964176192
        System.out.println("Test 2: " + solve(1));        // expected: -2147483648 (1 moved to top bit)
    }
}
