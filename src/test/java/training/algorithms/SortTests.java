package training.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortTests {

    private MergeSort sort = new MergeSort();

    @Test
    public void oneElementSort_shouldReturn_thatOneElement() {
        assertArrayEquals(new int[] { 1 }, sort.sort(1));
    }
}
