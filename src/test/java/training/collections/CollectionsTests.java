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
}
