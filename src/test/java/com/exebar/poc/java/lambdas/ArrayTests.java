package com.exebar.poc.java.lambdas;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class ArrayTests {

    private final int [] numbers = { 1, 2, 3, 4 };

    @Test
    void processAndPrintNumbers() {
        IntStream.of(numbers).map(x -> x / 2).forEach(System.out::println);
    }
}
