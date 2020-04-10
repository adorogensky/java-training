package com.exebar.poc.java.lambdas;


import org.junit.jupiter.api.Test;

class GreeterTest {

    @Test
    void test() {
        Greeter greeter = new Greeter();

        Greeting englishGreeting = () -> System.out.println("Hello!");
        Greeting russianGreeting = () -> System.out.println("Привет!");

        Greeting copyOfEnglishGreeting = new EnglishGreeting();
        Greeting copyOfRussianGreeting = new RussianGreeting();

        greeter.greet(englishGreeting);
        greeter.greet(copyOfEnglishGreeting);
        greeter.greet(russianGreeting);
        greeter.greet(copyOfRussianGreeting);
    }
}
