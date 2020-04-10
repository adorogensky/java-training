package com.exebar.poc.java.lambdas;


import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.stream.Stream;

class GreeterTest {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";

    @Test
    void testVoidLambdaFunctions() {
        Greeter greeter = new Greeter();

        Greeting englishGreetingAsLambda = () -> System.out.println(ANSI_GREEN + "Hello!" + ANSI_RESET);
        Greeting russianGreetingAsLambda = () -> System.out.println(ANSI_GREEN + "Привет!" + ANSI_RESET);

        Greeting englishGreetingAsOuterImplementation = new EnglishGreeting();
        Greeting russianGreetingAsOuterImplementation = new RussianGreeting();

        Greeting englishGreetingAsAnonymousClass = new Greeting() {
            @Override
            public void perform() {
                System.out.println(ANSI_BLUE + "Hello!" + ANSI_RESET);
            }
        };

        greeter.greet(englishGreetingAsLambda);
        greeter.greet(englishGreetingAsOuterImplementation);
        greeter.greet(englishGreetingAsAnonymousClass);

        greeter.greet(russianGreetingAsLambda);
        greeter.greet(russianGreetingAsOuterImplementation);
    }

    @Test
    void testReturnValueLambdaFunctionsForms() {
        Function<Integer, Integer> squaredLambdaExplicitParamTypeAndReturn = (Integer i) -> { return i * i; };
        Function<Integer, Integer> squaredLambdaExplicitParamTypeAndImplicitReturn = (Integer i) -> i * i;
        Function<Integer, Integer> squaredLambdaImplicitParamTypeAndExplicitReturn = (i) -> { return i * i; };
        Function<Integer, Integer> squaredLambdaImplicitParamTypeAndReturn = (i) -> i * i;
        Function<Integer, Integer> squaredLambdaImplicitSimpleParamTypeAndReturn = i -> i * i;

        Stream.of(
                squaredLambdaExplicitParamTypeAndImplicitReturn,
                squaredLambdaExplicitParamTypeAndReturn,
                squaredLambdaImplicitParamTypeAndExplicitReturn,
                squaredLambdaImplicitParamTypeAndReturn,
                squaredLambdaImplicitSimpleParamTypeAndReturn
        ).forEach(
            squared -> System.out.printf("5 squared is = %2d\n", squared.apply(5))
        );
    }
}
