package training.streams;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

// of(2), ofNullable, empty
// filter, peek
// map, mapToInt, mapToLong, mapToDouble
// flatMap, flatMapToInt, flatMapToLong, flatMapToDouble
// reduce(3)
// distinct, sorted(2), min, max, count
// anyMatch, allMatch, noneMatch
// concat, generate, iterate, forEach, forEachOrdered
// collect(3)
// findFirst, findAny
// takeWhile, dropWhile ???
public class StreamTests {

    private final int [] numbers = { 1, 2, 3, 4 };

    private final int [][] inversions = { { 1, 2 }, { 5, 8 }, { 9, 11 } };

    @Test
    void testConcatArrayLists() {
        List<Integer> arrayList1 = Arrays.asList(1, 2);
        List<Integer> arrayList2 = Arrays.asList(5, 8);
        List<Integer> arrayList3 = Arrays.asList(9, 11);

        // this is a great example where use of streams
        // creates unnecessary complexity with erasure types
        // e.g. collect(toList()) will not work if the array list type is ArrayList<Integer>
        Stream.of(arrayList1, arrayList2, arrayList3).reduce(
            new ArrayList<>(),
                (combined, element) ->
                    concat(combined.stream(), element.stream()).collect(toList())
        ).forEach(System.out::println);
    }

    @Test
    void testFlatMap() {
        Arrays.stream(inversions).flatMapToInt(Arrays::stream).forEach(System.out::println);
    }
}
