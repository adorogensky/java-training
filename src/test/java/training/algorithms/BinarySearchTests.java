package training.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTests {

    BinarySearch bs = new BinarySearch();

    @Test
    public void allTests() {
        assertEquals(0, bs.doSearch(new int[] { 1 }, 1));
        assertEquals(-1, bs.doSearch(new int[] { 1 }, 2));
        assertEquals(3, bs.doSearch(new int[] { 1, 2, 4, 7, 9, 10 }, 7));
        assertEquals(-1, bs.doSearch(new int[] { 1, 2, 4, 7, 9, 10 }, 11));
    }
}
