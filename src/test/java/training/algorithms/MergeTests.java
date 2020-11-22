package training.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeTests {

    private final RehearseMergeSort rehearseMergeSort = new RehearseMergeSort();

    @Test
    public void twoOneElementArraysInSortedOrder_shouldReturn_oneTwoElementSortedArray() {
        assertArrayEquals(new int[] {1, 2}, rehearseMergeSort.merge(new int[2], new int[] {1}, new int[] {2}));
    }

    @Test
    public void twoOneElementArraysInReverseOrder_shouldReturn_oneTwoElementSortedArray() {
        assertArrayEquals(new int[] {1, 2}, rehearseMergeSort.merge(new int[2], new int[] {2}, new int[] {1}));
    }

    @Test
    public void oneFiveElementArray_and_OneThreeElementArray_shouldReturn_oneEightElementSortedArray() {
        assertArrayEquals(new int[] {1, 2, 2, 2, 8, 12, 13, 14}, rehearseMergeSort.merge(new int[8], new int[] {2, 8, 12, 13, 14}, new int[] {1, 2, 2}));
    }
}
