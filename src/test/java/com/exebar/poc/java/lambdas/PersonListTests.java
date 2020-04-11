package com.exebar.poc.java.lambdas;

import com.exebar.poc.java.common.Person;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static com.exebar.poc.java.common.PersonTestData.*;
import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;

class PersonListTests {

    @Test
    void printAgesOfPeopleSortedByLastName() {
        List<Person> personList = asList(mikeWalsh(), jakeHillis(), sumitChaudhari());

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
}
