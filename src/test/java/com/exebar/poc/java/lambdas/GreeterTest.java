package com.exebar.poc.java.lambdas;


import org.junit.jupiter.api.Test;

class GreeterTest {

    @Test
    void test() {
        Greeter greeter = new Greeter();
        greeter.greet(new EnglishGreeting());
        greeter.greet(new RussianGreeting());
    }
}
