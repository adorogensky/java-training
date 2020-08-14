package training.streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

// + of, + range, + rangeClosed, + concat, ? builder
// + asLongStream, + asDoubleStream, + boxed
// + generate, ++ iterate(2), + forEach, ? forEachOrdered
// + filter, + peek, + map, + mapToLong, + mapToDouble, + mapToObj
// + flatMap
// ++ reduce (2)
// + distinct, + sorted, + min, + max, + count, + sum, + average
// + anyMatch, + allMatch, + noneMatch
// + findFirst, + findAny
// + takeWhile, + dropWhile
// ? collect

class IntStreamTests {

    private final int [] numbers = { 1, 2, 3, 4 };

    @Test
    void testConcat() {
        IntStream.concat(IntStream.of(0), IntStream.of(numbers)).forEach(System.out::println);
    }

    @Test
    void testRange() {
        IntStream.range(1, 11).forEach(System.out::println);
    }

    @Test
    void testRangeClosed() {
        IntStream.rangeClosed(1, 10).forEach(System.out::println);
    }

    @Test
    // what is a good use case for IntStream.builder() ?
    void testBuilder() {
        IntStream.builder().add(1).add(2).add(3).build().forEach(System.out::println);
    }

    @Test
    void testAsLongStream() {
        assertArrayEquals(
            new long[] { 1, 2, 3, 4 },
            IntStream.of(numbers).asLongStream().toArray()
        );
    }

    @Test
    void testAsDoubleStream() {
        assertArrayEquals(
            new double[] { 1, 2, 3, 4 },
            IntStream.of(numbers).asDoubleStream().toArray()
        );
    }

    @Test
    void testBoxed() {
        assertEquals(
            Arrays.asList(1, 2, 3, 4),
            IntStream.of(numbers).boxed().collect(toList())
        );
    }

    @Test
    void testGenerate() {
        Stream.generate(UUID::randomUUID).limit(5).forEach(System.out::println);
    }

    @Test
    void testIterate() {
        IntStream.iterate(0, i -> i + 2).limit(6).forEach(System.out::println);
    }

    /*
        Since Java 9
     */
    @Test
    void testIterate9() {
        IntStream.iterate(0, i -> i <= 10, i -> i + 2).forEach(System.out::println);
    }

    // this case doesn't seem to depend forEachOrdered()
    // what is a good use case for IntStream.builder() ?
    @Test
    void testForEachOrdered() {
        Stream.of(1, 2, 3).sorted(Comparator.reverseOrder()).forEachOrdered(System.out::println);
    }

    @Test
    void testFilter() {
        IntStream.of(numbers).filter(
            number -> number % 2 == 0
        ).forEach(System.out::println);
    }

    @Test
    void testMap() {
        IntStream.of(numbers).map(
            x -> x / 2
        ).forEach(System.out::println);
    }

    @Test
    void testMapToLong() {
        assertArrayEquals(
            new long[] { 1, 2, 3, 4 },
            IntStream.of(numbers).mapToLong(
                number -> number
            ).toArray()
        );
    }

    @Test
    void testMapToDouble() {
        // why is cast to double needed here?
        IntStream.of(numbers).mapToDouble(
            number -> (double) number / 2
        ).forEach(System.out::println);
    }

    @Test
    void testMapToObj() {
        IntStream.of(numbers).mapToObj(x -> x + "s").forEach(System.out::println);
    }

    @Test
    void testPeek() {
        IntStream.of(numbers).peek(
            x -> System.out.print(x + " => ")
        ).map(x -> x / 2).forEach(System.out::println);
    }

    @Test
    void testFlatMap() {
        IntStream.of(1, 3, 5).flatMap(
            number -> Integer.toBinaryString(number).codePoints().map(c -> c == '1' ? 1 : 0)
        ).forEach(System.out::print);

        System.out.println();
    }

    @Test
    void testReduce_noIdentity() {
        assertEquals(1 + 2 + 3 + 4, IntStream.of(1, 2, 3, 4).reduce(Integer::sum).orElse(0));
    }

    @Test
    void testReduce() {
        assertEquals(1 + 2 + 3 + 4, IntStream.of(1, 2, 3, 4).reduce(0, Integer::sum));
    }

    @Test
    void testDistinct() {
        IntStream.of(1, 1, 2, 2, 4, 4, 4).distinct().forEach(System.out::println);
    }

    @Test
    void testCount() {
        assertEquals(4, IntStream.of(numbers).count());
    }

    @Test
    void testSum() {
        assertEquals(1 + 2 + 3 + 4, IntStream.of(numbers).sum());
    }

    @Test
    void testAverage() {
        assertEquals((double) (1 + 2 + 3 + 4) / 4, IntStream.of(numbers).average().orElse(0));
    }

    @Test
    void testSorted() {
        assertArrayEquals(
            new int[] { 1, 2, 5 },
            IntStream.of(5, 1, 2).sorted().toArray()
        );
    }

    @Test
    void testMin() {
        assertEquals(1, IntStream.of(numbers).min().orElseThrow(() -> new RuntimeException("empty stream")));
    }

    @Test
    void testMax() {
        assertEquals(4, IntStream.of(numbers).max().orElseThrow(() -> new RuntimeException("empty stream")));
    }

    @Test
    void testAnyMatch() {
        assertTrue(IntStream.of(numbers).anyMatch(i -> i == 4));
    }

    @Test
    void testAllMatch() {
        assertTrue(IntStream.of(numbers).allMatch(i -> i < 5));
    }

    @Test
    void testNoneMatch() {
        assertTrue(IntStream.of(numbers).noneMatch(i -> i == 5));
    }

    /* Since Java 9 */
    @Test
    void testTakeWhile() {
        IntStream.of(2, 2, 1, 3, 6, 2, 2, 5).takeWhile(
            number -> number % 2 == 0
        ).forEach(System.out::println);
    }

    /* Since Java 9 */
    @Test
    void dropTakeWhile() {
        IntStream.of(2, 2, 1, 3, 6, 2, 2, 5).dropWhile(
            number -> number % 2 == 0
        ).forEach(System.out::println);
    }

    @Test
    void testCollect() {
        assertEquals(
            Arrays.asList(1, 2, 3, 4),
            IntStream.of(1, 2, 3, 4).collect(
                ArrayList<Integer>::new,
                ArrayList::add,
                ArrayList::addAll
            )
        );
    }
}
