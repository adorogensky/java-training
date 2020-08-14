package training.streams;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTest {

    private int factorial(int n) {
        return IntStream.range(1, n + 1).reduce(1, (x, y) -> x * y);
    }

    @Test
    void testFactorial() {
        assertEquals(1, factorial(1));
        assertEquals(2, factorial(2));
        assertEquals(6, factorial(3));
        assertEquals(24, factorial(4));
    }

}
