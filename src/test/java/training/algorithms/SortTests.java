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
    public void twoElementsInAscendingOrderSort_shouldReturn_thoseTwoElementsInSameOrder() {
        assertArrayEquals(new int[] { 1, 2 }, sort.sort(1, 2));
    }
}
