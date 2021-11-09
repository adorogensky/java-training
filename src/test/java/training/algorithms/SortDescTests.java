package training.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortDescTests {

    private final QuickSortDesc sort = new QuickSortDesc();

    @Test
    public void oneElementSort_shouldReturn_thatOneElement() {
        assertArrayEquals(new int[] { 1 }, sort.sort(1));
    }

    @Test
    public void twoEqualElementsSort_shouldReturn_thoseTwoElements() {
        assertArrayEquals(new int[] { 2, 2 }, sort.sort(2, 2));
    }

    @Test
    public void twoElementsInAscendingOrderSort_shouldReturn_thoseTwoElementsInAscendingOrder() {
        assertArrayEquals(new int[] { 2, 1 }, sort.sort(1, 2));
    }

    @Test
    public void twoElementsInDescendingOrderSort_shouldReturn_thoseTwoElementsInAscendingOrder() {
        assertArrayEquals(new int[] { 2, 1 }, sort.sort(2, 1));
    }

    @Test
    public void threeEqualElementsSort_shouldReturn_thoseThreeElements() {
        assertArrayEquals(new int[] { 3, 3, 3 }, sort.sort(3, 3, 3));
    }

    @Test
    public void threeElementsInAscendingOrderSort_shouldReturn_thoseThreeElementsInAscendingOrder() {
        assertArrayEquals(new int[] { 3, 2, 1 }, sort.sort(3, 2, 1));
    }

    @Test
    public void threeElementsInDescendingOrderSort_shouldReturn_thoseThreeElementsInAscendingOrder() {
        assertArrayEquals(new int[] { 3, 2, 1 }, sort.sort(1, 2, 3));
    }

    @Test
    public void threeElementsInRandomOrderSort_shouldReturn_thoseThreeElementsInAscendingOrder() {
        assertArrayEquals(new int[] { 3, 2, 1 }, sort.sort(3, 1, 2));
    }

    @Test
    public void oneElementAndTwoEqualElementsSort_shouldReturn_thoseThreeElementsInAscendinOrder() {
        assertArrayEquals(new int[] { 3, 1, 1 }, sort.sort(1, 1, 3));
    }

    @Test
    public void fourElementsInAscendingOrderSort_shouldReturn_thoseFourElementsInAscendingOrder() {
        assertArrayEquals(new int[] { 4, 3, 2, 1 }, sort.sort(4, 3, 2, 1));
    }

    @Test
    public void fourElementsInDescendingOrderSort_shouldReturn_thoseFourElementsInAscendingOrder() {
        assertArrayEquals(new int[] { 4, 3, 2, 1 }, sort.sort(1, 2, 3, 4));
    }

    @Test
    public void fourElementsRandomOrderSort_shouldReturn_thoseFourElementsInAscendingOrder() {
        assertArrayEquals(new int[] { 4, 3, 2, 1 }, sort.sort(4, 1, 3, 2));
    }
}
