package com.exebar.poc.java.lambdas;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

// of(2), ofNullable, empty
// filter
// map, mapToInt, mapToLong, mapToDouble
// flatMap, flatMapToInt, flatMapToLong, flatMapToDouble
// distinct, min, max, count, reduce(3)
// sorted(2)
// peek, forEach, forEachOrdered
// collect(3) ??
// takeWhile, dropWhile ???
// anyMatch, allMatch, noneMatch ???
// findFirst, findAny ???
// iterate ???
// generate ???
// concat ???
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
}
