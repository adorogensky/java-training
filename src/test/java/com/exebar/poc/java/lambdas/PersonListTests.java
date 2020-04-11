package com.exebar.poc.java.lambdas;

import com.exebar.poc.java.common.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.exebar.poc.java.common.PersonTestData.*;
import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;

class PersonListTests {

    private final List<Person> personList = asList(mikeWalsh(), jakeHillis(), sumitChaudhari());

    @Test
    void printAgesOfPeopleSortedByLastName() {
        personList.sort(
                comparing(Person::getLastName)
        );

        personList.forEach(
                person ->
                        System.out.printf(
                                "lastName = %9s, age = %2d\n",
                                person.getLastName(), person.getAge()
                        )
        );
    }

    @Test
    void printPeople() {
        personList.forEach(
                person ->
                        System.out.printf(
                                "firstName = %-5s, lastName = %-9s, age = %2d\n",
                                person.getFirstName(),
                                person.getLastName(),
                                person.getAge()
                        )
        );
    }

    @Test
    void printPeopleLastNameStartsWith_C() {
        personList.stream().filter(
                person -> person.getLastName().startsWith("C")
        ).forEach(
                person ->
                        System.out.printf(
                                "firstName = %-5s, lastName = %-9s, age = %2d\n",
                                person.getFirstName(),
                                person.getLastName(),
                                person.getAge()
                        )
        );
    }
}
