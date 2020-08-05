package training.streams;

import org.junit.jupiter.api.Test;
import training.common.Person;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.util.stream.Collectors.groupingBy;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static training.common.PersonTestData.*;

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

    @Test
    void printCountOfPeopleByLengthOfFirstName() {
        people.stream().collect(groupingBy(person -> person.getFirstName().length(), toList())).forEach(
                (key, value) -> System.out.printf("firstNameLength = %d, peopleCount = %d\n", key, value.size())
        );
    }

    private void print(List<Person> people) {
        print(people, person -> true);
    }

    private void print(List<Person> people, Predicate<Person> filter) {
        people.stream().filter(filter).forEach(printPerson);
    }
}