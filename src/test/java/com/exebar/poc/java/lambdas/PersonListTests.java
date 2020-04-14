package com.exebar.poc.java.lambdas;

import com.exebar.poc.java.common.Person;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.exebar.poc.java.common.PersonTestData.*;
import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;

class PersonListTests {

    private final List<Person> people = asList(mikeWalsh(), jakeHillis(), sumitChaudhari());

    private final Consumer<Person> printPerson =
            person -> System.out.printf(
                    "firstName = %-5s, lastName = %-9s, age = %2d\n",
                    person.getFirstName(),
                    person.getLastName(),
                    person.getAge()
            );

    @Test
    void printAgesOfPeopleSortedByLastName() {
        people.stream().sorted(
                comparing(Person::getLastName)
        ).forEach(printPerson);
    }

    @Test
    void printPeople() {
        print(people);
    }

    @Test
    void printPeopleLastNameStartsWith_C() {
        print(people, person -> person.getLastName().startsWith("C"));
    }

    private void print(List<Person> people) {
        print(people, person -> true);
    }

    private void print(List<Person> people, Predicate<Person> filter) {
        people.stream().filter(filter).forEach(printPerson);
    }
}
