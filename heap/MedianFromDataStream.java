/*
 * Problem: Find Median from Data Stream
 *
 * Numbers arrive one at a time. After each, be able to report the median of all
 * numbers seen so far. addNum() and findMedian() should be fast.
 *
 * Example:
 * addNum(1); addNum(2); findMedian() -> 1.5
 * addNum(3); findMedian() -> 2.0
 *
 * Approach: keep two heaps splitting the data in half:
 *   - a MAX-heap for the smaller half (so its top is the biggest of the small half)
 *   - a MIN-heap for the larger half (so its top is the smallest of the large half)
 *   keep them balanced in size. the median is either the top of the bigger heap
 *   (odd count) or the average of both tops (even count). balancing the two heaps
 *   is the whole idea - took me a bit to get the rebalance rules right.
 *
 * Time: O(log n) per add, O(1) for median
 * Space: O(n)
 *
 * Topics: heap, two heaps, design
 */

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFromDataStream {

    // small half: max-heap.  large half: min-heap.
    PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> large = new PriorityQueue<>();

    void addNum(int num) {
        small.offer(num);
        large.offer(small.poll());          // push the small-half max into large half
        if (large.size() > small.size()) {  // rebalance so small >= large in size
            small.offer(large.poll());
        }
    }

    double findMedian() {
        if (small.size() > large.size()) return small.peek();   // odd count
        return (small.peek() + large.peek()) / 2.0;             // even count
    }

    public static void main(String[] args) {
        MedianFromDataStream m = new MedianFromDataStream();
        m.addNum(1);
        m.addNum(2);
        System.out.println("median: " + m.findMedian()); // expected: 1.5
        m.addNum(3);
        System.out.println("median: " + m.findMedian()); // expected: 2.0
        m.addNum(4);
        System.out.println("median: " + m.findMedian()); // expected: 2.5
    }
}
