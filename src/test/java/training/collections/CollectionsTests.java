package training.collections;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * List
 * Set, SortedSet, NavigableSet
 * Map, SortedMap, NavigableMap
 * Enumeration
 * Iterator
 * ListIterator
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
}
