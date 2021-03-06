package training.lambdas;

import org.junit.jupiter.api.Test;

import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

class LambdaTests {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";

    private void println(String text, String color) {
        System.out.println(color + text + ANSI_RESET);
    }

    @Test
    void testNoParametersLambdaFunctionsThatReturnVoid() {
        Greeter greeter = new Greeter();

        greeter.greet(() -> println("Hello!", ANSI_BLUE));
        greeter.greet(new EnglishGreeting());
        greeter.greet(new Greeting() {
            @Override
            public void perform() {
                println( "Hello!", ANSI_BLUE);
            }
        });

        greeter.greet(() -> { println( "Привет!", ANSI_GREEN); });
        greeter.greet(new RussianGreeting());
    }

    @Test
    void testOneParameterLambdaFunctionsThatReturnValue() {
        Stream.of(
            (ToIntFunction<Integer>) (Integer i) -> { return i * i; },
            (Integer i) -> { return i * i; },
            (Integer i) -> i * i,
            (i) -> { return i * i; },
            (i) -> i * i,
            i -> i * i
        ).forEach(
            squared -> System.out.printf("5 * 5 = %2d\n", squared.applyAsInt(5))
        );
    }

    @Test
    void testTwoParametersLambdaFunctionsThatReturnValue() {
        Stream.of(
            (ToIntBiFunction<Integer, Integer>) (i, j) -> i + j,
            (i, j) -> i + j,
            Integer::sum,
            (Integer i, Integer j) -> i + j
        ).forEach(
            sum -> System.out.printf("2 + 3 = %2d\n", sum.applyAsInt(2, 3))
        );
    }
}
