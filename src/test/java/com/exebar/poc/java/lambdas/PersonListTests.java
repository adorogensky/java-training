package com.exebar.poc.java.lambdas;

import com.exebar.poc.java.common.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.exebar.poc.java.common.PersonTestData.*;
import static java.util.Arrays.asList;

class PersonListTests {

    @Test
    void printAgesOfPeopleSortedByLastName() {
        List<Person> personList = asList(mikeWalsh(), jakeHillis(), sumitChaudhari());

        personList.sort(
                (left, right) -> left.getLastName().compareTo(right.getLastName())
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
