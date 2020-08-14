package training.streams;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class FibonacciTest {

    @Test
    void printFibonacciNumbers() {
        Stream.iterate(
            new int[] { 0, 1 },
            twoPreviousNumbers ->
                new int[] {
                    twoPreviousNumbers[1],
                    twoPreviousNumbers[0] + twoPreviousNumbers[1]
                }
        ).limit(10).mapToInt(
            twoNumbers -> twoNumbers[0]
        ).forEach(x -> System.out.print(x + " "));

        System.out.println();
    }
}
