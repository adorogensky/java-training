package training.streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

// of(2), ofNullable, empty
// map, mapToInt, mapToLong, mapToDouble
// flatMap, flatMapToInt, flatMapToLong, flatMapToDouble
// distinct, min, max, count, reduce(3)
// anyMatch, allMatch, noneMatch
// filter, sorted(2), peek
// concat, generate, iterate, forEach, forEachOrdered, collect(3)
// findFirst, findAny
// takeWhile, dropWhile ???

class IntStreamTests {

    private final int [] numbers = { 1, 2, 3, 4 };

    private final int [][] inversions = { { 1, 2 }, { 5, 8 }, { 9, 11 } };

    @Test
    void testMapAndForEach() {
        IntStream.of(numbers).map(x -> x / 2).forEach(System.out::println);
    }

    @Test
    void testPeek() {
        IntStream.of(numbers).peek(
            x -> System.out.print(x + " => ")
        ).map(x -> x / 2).forEach(System.out::println);

    }

    @Test
    void testFlatMap() {
        Arrays.stream(inversions).flatMapToInt(Arrays::stream).forEach(System.out::println);
    }

    private int factorial(int n) {
        return IntStream.range(1, n + 1).reduce(1, (x, y) -> x * y);
    }

    @Test
    void testReduce() {
        assertEquals(1, factorial(1));
        assertEquals(2, factorial(2));
        assertEquals(6, factorial(3));
        assertEquals(24, factorial(4));
    }

    @Test
    void testDistinct() {
        assertEquals(numbers.length, IntStream.of(numbers).distinct().count());
    }

    @Test
    void testMax() {
        assertEquals(4, IntStream.of(numbers).max().orElseThrow(() -> new RuntimeException("empty stream")));
    }

    @Test
    void testMin() {
        assertEquals(1, IntStream.of(numbers).min().orElseThrow(() -> new RuntimeException("empty stream")));
    }


    @Test
    void testNoneAnyAllMatch() {
        assertTrue(IntStream.of(numbers).noneMatch(i -> i == 5));
        assertTrue(IntStream.of(numbers).anyMatch(i -> i == 4));
        assertTrue(IntStream.of(numbers).allMatch(i -> i < 5));
    }

    @Test
    void testIterate() {
        IntStream.iterate(0, i -> i + 1).limit(numbers.length).forEach(
            i -> System.out.print(numbers[i] + " ")
        );

        System.out.println();

        IntStream.iterate(0, i -> i < numbers.length, i -> i + 1).forEach(
            i -> System.out.print(numbers[i] + " ")
        );
    }

    @Test
    void testGenerate_printRandomIntegers() {
        IntStream.generate(() -> new Random().nextInt(10)).limit(5).forEach(System.out::println);
    }

    @Test
    void testGenerate_printRandomUUIDs() {
        Stream.generate(UUID::randomUUID).limit(5).forEach(System.out::println);
    }

    @Test
    void testCollect_toLinkedList() {
        List<Integer> numbersList = IntStream.of(numbers).boxed().collect(toList());
        LinkedList<Integer> numbersLinkedList = IntStream.of(numbers).boxed().collect(
                toCollection(LinkedList::new)
        );

        assertTrue(numbersList.containsAll(numbersLinkedList));
        assertTrue(numbersLinkedList.containsAll(numbersList));
    }

    @Test
    void testCollect_joining() {
        assertEquals("1 2 3 4", IntStream.of(numbers).mapToObj(i -> "" + i).collect(joining(" ")));
        assertEquals("1 2 3 4", IntStream.of(numbers).mapToObj(i -> "" + i).reduce("", (x, y) -> x + " " + y).trim());
    }

    @Test
    void testCollect_toMap() {
        String[] names = {"alex", "nancy", "john"};
        Stream.iterate(0, i -> i + 1).limit(names.length).collect(
            toMap(Function.identity(), i -> names[i])
        ).forEach(
            (key, value) -> System.out.printf("(%d, %s)\n", key, value)
        );
    }

    @Test
    void testCollect_toMap_merge() {
        String[] names = {"alex", "nancy", "john"};

        Map<Integer, List<String>> namesByLength = Arrays.stream(names).collect(
            toMap(
                String::length,
                Arrays::asList,
                (oldValue, newValue) ->
                    Stream.of(oldValue, newValue).flatMap(Collection::stream).collect(toList())
            )
        );

        assertEquals(2, namesByLength.keySet().size());
        assertEquals(asList("alex", "john"), namesByLength.get(4));
        assertEquals(singletonList("nancy"), namesByLength.get(5));
    }

    @Test
    void testCollectingAndThen() {
        String[] names = { "alex", "nancy", "john" };
        Arrays.stream(names).collect(
            collectingAndThen(toList(), list -> { list.add("unknown"); return list; })
        ).forEach(System.out::println);
    }

    @Test
    void testJoining() {
        String[] words = { "hello", "this", "world" };
        assertEquals(
            "hello this world.",
            Arrays.stream(words).collect(joining(" ", "", "."))
        );
    }

//    @Test
//    void testMapping() {
//        IntStream.of(numbers).collect(toList());
//    }


    @Test
    void testSummarizingDouble() {
        DoubleSummaryStatistics statistics = IntStream.of(numbers).boxed().collect(summarizingDouble(x -> x));
        assertEquals(1, statistics.getMin());
        assertEquals(4, statistics.getMax());
        assertEquals(4, statistics.getCount());
        assertEquals(10, statistics.getSum());
        assertEquals(2.5, statistics.getAverage());

        assertEquals(1, IntStream.of(numbers).boxed().collect(minBy(Comparator.naturalOrder())).get());
        assertEquals(4, IntStream.of(numbers).boxed().collect(maxBy(Comparator.naturalOrder())).get());
        assertEquals(4, IntStream.of(numbers).boxed().collect(counting()));
        assertEquals(10, IntStream.of(numbers).boxed().collect(summingInt(x -> x)));
        assertEquals(2.5, IntStream.of(numbers).boxed().collect(averagingDouble(x -> x)));
    }

    @Test
    void printFibbonaciNumbers() {
        // [0, 1] => 0 => [1, 1] => 1 => [1, 2] => 1 => [2, 3] => 2 => [3, 5] => 3 => [5, 8] => 5 => ...
        // 0, 1, 1, 1, 2, 3, 5

        Stream.iterate(
                new int[] { 0, 1 },
                twoPreviousNumbers -> new int[] { twoPreviousNumbers[1], twoPreviousNumbers[0] + twoPreviousNumbers[1] }
        ).limit(10).mapToInt(
                twoNumbers -> twoNumbers[0]
        ).forEach(System.out::println);
    }

}
