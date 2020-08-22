package training.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortTests {

    private final MergeSort sort = new MergeSort();

    @Test
    public void oneElementSort_shouldReturn_thatOneElement() {
        assertArrayEquals(new int[] { 1 }, sort.sort(1));
    }

    @Test
    public void twoElementsInAscendingOrderSort_shouldReturn_thoseTwoElementsInAscendingOrder() {
        assertArrayEquals(new int[] { 1, 2 }, sort.sort(1, 2));
    }

    @Test
    public void twoElementsInDescendingOrderSort_shouldReturn_thoseTwoElementsInAscendingOrder() {
        assertArrayEquals(new int[] { 1, 2 }, sort.sort(2, 1));
    }

    @Test
    public void threeElementsInAscendingOrderSort_shouldReturn_thoseThreeElementsInAscendingOrder() {
        assertArrayEquals(new int[] { 1, 2, 3 }, sort.sort(1, 2, 3));
    }
}
