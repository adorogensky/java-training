package com.exebar.poc.java.lambdas;

public class Greeter {

    private void perform() {
        System.out.println("Hello");
    }

    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        greeter.perform();
    }
}
