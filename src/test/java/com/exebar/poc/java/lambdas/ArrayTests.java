package com.exebar.poc.java.lambdas;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

// of(2), ofNullable, empty
// filter
// map, mapToInt, mapToLong, mapToDouble
// flatMap, flatMapToInt, flatMapToLong, flatMapToDouble
// collect(3) ??
// distinct
// sorted(2)
// peek
// forEach, forEachOrdered
// takeWhile, dropWhile ???
// reduce(3) ?
// min, max, count
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
    void printInversionIndexes() {
        Arrays.stream(inversions).flatMapToInt(Arrays::stream).forEach(System.out::println);
    }
}
