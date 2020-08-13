package training.streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.averagingDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectorTests {

    @Test
    void testCollect_toLinkedList() {
        List<Integer> numbersList = Stream.of(1, 2, 3, 4).collect(toList());
        LinkedList<Integer> numbersLinkedList = Stream.of(1, 2, 3, 4).collect(
            toCollection(LinkedList::new)
        );

        assertEquals(numbersLinkedList, numbersList);
    }

    @Test
    void testCollect_joining() {
        assertEquals("1 2 3 4", IntStream.of(1, 2, 3, 4).mapToObj(i -> "" + i).collect(joining(" ")));
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
    void testCollect_collectingAndThen() {
        String[] names = { "alex", "nancy", "john" };

        Arrays.stream(names).collect(
            collectingAndThen(
                toList(),
                list -> Stream.concat(
                    list.stream(),
                    Stream.of("unknown")
                ).collect(toList())
            )
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
    void testCollect_summarizingDouble() {
        int[] numbers = { 1, 2, 3, 4 };
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
}
