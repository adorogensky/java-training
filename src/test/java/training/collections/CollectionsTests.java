package training.collections;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * List
 * Set, SortedSet, NavigableSet
 * Map, SortedMap, NavigableMap
 * Enumeration
 * Iterator
 * ListIterator
 * Comparator
 */
public class CollectionsTests {

    @Test
    public void testEmptyList() {
        List<String> checkedEmptyList = Collections.emptyList();
        // List<String> uncheckedEmptyList = Collections.EMPTY_LIST;
        assertEquals(0, checkedEmptyList.size());
        assertThrows(UnsupportedOperationException.class, () -> checkedEmptyList.add(""));
    }

    @Test
    public void testAddAll() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4);
        assertArrayEquals(new Integer[] { 1, 2, 3, 4 }, list.toArray());
    }

    @Test
    public void testFill() {
        List<Integer> list = new ArrayList<>();
        Collections.fill(list, 1);

        assertTrue(list.isEmpty());

        list.add(2);
        Collections.fill(list, 3);

        assertEquals(1, list.size());
        assertEquals(3, list.get(0));
    }

    @Test
    public void testDisjoint() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        assertTrue(Collections.disjoint(list1, list2));
    }

    @Test
    public void test_nCopies() {
        List<String> threeHello = Collections.nCopies(3, "hello");
        assertEquals(3, threeHello.size());
        assertArrayEquals(new String[] { "hello", "hello", "hello" }, threeHello.toArray());
    }

    @Test
    public void testBinarySearch() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        assertEquals(2, Collections.binarySearch(numbers, 3));

        // binary search on an array of numbers sorted in reverse order
        // will not find elements in the right half of the array
        numbers = Arrays.asList(4, 3, 2, 1);
        assertEquals(-1, Collections.binarySearch(numbers, 2));
    }

    @Test
    public void testFrequency() {
        List<Integer> numbers = Arrays.asList(2, 2, 3, 4, 5, 2);
        assertEquals(3, Collections.frequency(numbers, 2));
        assertEquals(0, Collections.frequency(numbers, 6));
    }

    @Test
    public void testCopy() {
        List<Integer> numbers = Arrays.asList(5, 3, 2, 7);

        // Because the dest list should have at least the number of elements from the src list
        // I can't even think of any real world applications for this use case..
        assertThrows(
            IndexOutOfBoundsException.class,
            () -> Collections.copy(new ArrayList<>(), numbers),
            "Source does not fit in dest"
        );
    }

    @Test
    public void testIndexOfSubList() {
        List<Integer> numbers = Arrays.asList(2, 4, 5, 7, 8, 2, 4);

        // returns lowest index of a sublist
        assertEquals(0, Collections.indexOfSubList(numbers, Arrays.asList(2, 4)));
    }

    @Test
    public void testLastIndexOfSubList() {
        List<Integer> numbers = Arrays.asList(2, 4, 5, 7, 8, 2, 4);

        assertEquals(5, Collections.lastIndexOfSubList(numbers, Arrays.asList(2, 4)));
    }

    @Test
    public void testList() {
        Vector<Integer> vector = new Vector<>(Arrays.asList(1, 2, 3));
        assertEquals(Arrays.asList(1, 2, 3), Collections.list(vector.elements()));
    }

    @Test
    public void testMax() {
        List<Integer> numbers = Arrays.asList(2, 4, 6);
        assertEquals(6, Collections.max(numbers));

        assertEquals(2, Collections.max(numbers, (a, b) -> b - a));
    }

    @Test
    public void testMin() {
        List<Integer> numbers = Arrays.asList(2, 4, 6);
        assertEquals(2, Collections.min(numbers));

        assertEquals(6, Collections.min(numbers, (a, b) -> b - a));
    }

    @Test
    public void testReplaceAll() {
        List<Integer> numbers = Arrays.asList(4, 5, 3, 4);
        assertTrue(Collections.replaceAll(numbers, 4, 1));
        assertEquals(
            Arrays.asList(1, 5, 3, 1), numbers
        );
    }


}
