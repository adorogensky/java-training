package training.collections;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void emptyListIsImmutable() {
        List<String> checkedEmptyList = Collections.emptyList();
        // List<String> uncheckedEmptyList = Collections.EMPTY_LIST;
        assertThrows(UnsupportedOperationException.class, () -> checkedEmptyList.add(""));
    }
}
