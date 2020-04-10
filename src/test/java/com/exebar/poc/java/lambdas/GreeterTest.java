package com.exebar.poc.java.lambdas;


import org.junit.jupiter.api.Test;

class GreeterTest {

    @Test
    void test() {
        Greeter greeter = new Greeter();
        greeter.perform(new EnglishGreeting());
        greeter.perform(new RussianGreeting());
    }
}
