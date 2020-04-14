package com.exebar.poc.java.lambdas;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// of(2), ofNullable, empty
// map, mapToInt, mapToLong, mapToDouble
// flatMap, flatMapToInt, flatMapToLong, flatMapToDouble
// distinct, min, max, count, reduce(3)
// anyMatch, allMatch, noneMatch
// filter, sorted(2), peek
// concat, generate, iterate, forEach, forEachOrdered, collect(3) ??
// findFirst, findAny
// takeWhile, dropWhile ???

class ArrayTests {

    private final int [] numbers = { 1, 2, 3, 4 };

    private final int [][] inversions = { { 1, 2 }, { 5, 8 }, { 9, 11 } };

    @Test
    void processAndPrintNumbers() {
        IntStream.of(numbers).map(x -> x / 2).forEach(System.out::println);

    }

    @Test
    void processAndPrintNumbersWithDebuggingInfo() {
        IntStream.of(numbers).peek(
            x -> System.out.println("original = " + x)
        ).map(x -> x / 2).forEach(System.out::println);

    }

    @Test
    void printInversionIndexes() {
        Arrays.stream(inversions).flatMapToInt(Arrays::stream).forEach(System.out::println);
    }

    @Test
    void countDistinctNumbers() {
        assertEquals(numbers.length, IntStream.of(numbers).distinct().count());
    }

    @Test
    void findMaxNumber() {
        assertEquals(4, IntStream.of(numbers).max().getAsInt());
    }

    @Test
    void findMinNumber() {
        assertEquals(1, IntStream.of(numbers).min().getAsInt());
    }

    @Test
    void calculateFactorials() {
        assertEquals(2, factorial(2));
        assertEquals(6, factorial(3));
        assertEquals(24, factorial(4));
    }

    private int factorial(int n) {
        return IntStream.range(1, n + 1).reduce(1, (x, y) -> x * y);
    }

    @Test
    void matchNumbers() {
        assertTrue(IntStream.of(numbers).noneMatch(i -> i == 5));
        assertTrue(IntStream.of(numbers).anyMatch(i -> i == 4));
        assertTrue(IntStream.of(numbers).allMatch(i -> i < 5));
    }

    @Test
    void printNumbers() {
        Stream.iterate(0, i -> i + 1).limit(numbers.length).forEach(
                i -> System.out.println(numbers[i])
        );

        Stream.iterate(0, i -> i < numbers.length, i -> i + 1).forEach(
                i -> System.out.println(numbers[i])
        );
    }

    @Test
    void printFiveRandomNumbers() {
        Stream.generate(() -> new Random().nextInt(10)).limit(5).forEach(System.out::println);
    }

    @Test
    void printFiveRandomUUIDs() {
        Stream.generate(UUID::randomUUID).limit(5).forEach(System.out::println);
    }

    @Test
    void printFibbonaciNumbers() {
        // 0, 1 => 0, 1, 1 => 0, 1, 1, 2 => 0, 1, 1, 2, 3 => 0, 1, 1, 2, 3, 5
        Stream.iterate(
                new int[] { 0, 1 },
                twoPreviousNumbers -> new int[] { twoPreviousNumbers[1], twoPreviousNumbers[0] + twoPreviousNumbers[1] }
        ).limit(10).mapToInt(
                twoNumbers -> twoNumbers[0]
        ).forEach(System.out::println);
    }

    @Test
    void printNumbersSeparatedBySpace() {
        List<Integer> numbersList = IntStream.of(numbers).boxed().collect(toList());
        LinkedList<Integer> numbersLinkedList = IntStream.of(numbers).boxed().collect(
                toCollection(LinkedList::new)
        );

        assertTrue(numbersList.containsAll(numbersLinkedList));
        assertTrue(numbersLinkedList.containsAll(numbersList));

        assertEquals("1 2 3 4", numbersList.stream().map(i -> "" + i).collect(joining(" ")));
        assertEquals("1 2 3 4", numbersList.stream().map(i -> "" + i).reduce("", (x, y) -> x + " " + y).trim());
    }

    @Test
    void printIndexAndNameMap() {
        String[] names = {"alex", "nancy", "john"};

        Stream.iterate(0, i -> i + 1).limit(names.length).collect(
                toMap(Function.identity(), i -> names[i])
        ).forEach(
                (key, value) -> System.out.printf("(%d, %s)\n", key, value)
        );
    }
}
