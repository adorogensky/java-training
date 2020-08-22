package training.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Given an array of integers x, let's define inversion as
 * a pair of indexes i and j so that i < j and x[i] > x[j].
 * If the array has one element, we will say that it doesn't have any inversions.
 * The problem is to calculate the number of inversions in the array in less than O(n^2) time.
 */
public class ArrayInversionsTests {

    private final ArrayInversions arrayInversions = new ArrayInversions();

    @Test
    public void oneInteger_shouldReturn_0() {
        assertEquals(0, arrayInversions.count(1));
    }

    @Test
    public void twoIntegersInAscendingOrder_shouldReturn_0() {
        assertEquals(0, arrayInversions.count(1, 2));
    }

    @Test
    public void twoEqualIntegers_shouldReturn_0() {
        assertEquals(0, arrayInversions.count(2, 2));
    }

    @Test
    public void twoIntegersInDescendingOrder_shouldReturn_1() {
        assertEquals(1, arrayInversions.count(2, 1));
    }

    @Test
    public void threeIntegersInAscendingOrder_shouldReturn_0() {
        assertEquals(0, arrayInversions.count(1, 2, 3));
    }

    @Test
    public void threeIntegersInDescendingOrder_shouldReturn_3() {
        assertEquals(3, arrayInversions.count(3, 2, 1));
    }

    @Test
    public void threeIntegersInRandomOrder1_shouldReturn_2() {
        assertEquals(2, arrayInversions.count(3, 1, 2));
    }

    @Test
    public void threeIntegersInRandomOrder2_shouldReturn_2() {
        assertEquals(2, arrayInversions.count(2, 3, 1));
    }

    @Test
    public void fourIntegersInDescendingOrder_shouldReturn_6() {
        assertEquals(6, arrayInversions.count(4, 3, 2, 1));
    }

    @Test
    public void sevenIntegersInDescendingOrder_shouldReturn_21() {
        // 6 + 5 + 4 + 3 + 2 + 1 = 10 + 11
        assertEquals(21, arrayInversions.count(7, 6, 5, 4, 3, 2, 1 ));
    }
}