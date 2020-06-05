# java-poc
Proof of non-trivial Java Concepts (Threads, Class Loading, I/O, Generics, Lambdas, etc

# Java Interview Tests
* There's a list flights e.g.
```
NY -> SF
SF -> San Jose
San Jose -> San Diego
San Diego -> Phoenix
Phoenix -> Chicago
Print all itineraries such as
NY -> SF -> San Jose -> San Diego -> Phoenix -> Chicago
Phoenix -> Chicago
etc
```

```
private static String buildItinerary(Map<String, String> flights, StringBuilder itinerary, String source) {
    String target = flights.get(source);

    if (target != null) {
        itinerary.append(" -> ").append(target);
        return buildItinerary(flights, itinerary, target);
    } else {
        return itinerary.toString();
    }
}

public static void main(String[] args) {
    Map<String, String> flights = new HashMap<>();
    flights.put("NY", "SF");
    flights.put("SF", "San Jose");
    flights.put("San Jose", "San Diego");
    flights.put("San Diego", "Phoenix");
    flights.put("Phoenix", "Chicago");

    flights.keySet().forEach(
        source -> System.out.println(
            buildItinerary(flights, new StringBuilder(source), source)
        )
    );
}
```

* Given a list of integers numbers find out the length of the list with Java Stream API
```
    List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
    System.out.println(
        numbers.stream().reduce(0, (subtotal, element) -> subtotal + 1).intValue()
    );
```
* Give a list of students where each student can have a list of borrowed books, find out all the unique books
```
class Student {
    private String name;
    private Set<String> books = new HashSet<>();

    void addBook(String book) {
        books.add(book);
    }

    void setName(String name) {
        this.name = name;
    }

    Set<String> getBooks() {
        return books;
    }
}

public static void main(String[] args) {
    List<Student> students = new ArrayList<>();
    Student newStudent = new Student();
    newStudent.setName("Alex");
    newStudent.addBook("The Job");
    newStudent.addBook("Conviction");

    students.add(newStudent);

    newStudent = new Student();
    newStudent.setName("Travis");
    newStudent.addBook("The Job");
    newStudent.addBook("The Education of a Coroner");

    students.add(newStudent);

    students.stream().flatMap(
        student -> student.getBooks().stream()
    ).collect(
        Collectors.toCollection(HashSet::new)
    ).forEach(System.out::println);
}

```


* What is the output from running main?

```
public class PrettyPrint {
    public void doAction(Queue q) {
        if (q.isEmpty()) return;
        Object first = q.remove();
        doAction(q);
        System.out.println(first.toString());
    }

    public static void main(String[] args) {
        LinkedList<String> starterList = new LinkedList<>(
                Arrays.asList("A", "B", "C", "D", "E")
        );
        PrettyPrint pp = new PrettyPrint();
        pp.doAction(starterList);
    }
}
```
* Given these 3 classes what is the output from running the main method?
    
```
class BaseA {
    private String instanceId;

    public BaseA(String instanceId) {
        this.instanceId = instanceId;
    }

    public void printInstanceName() {
        System.out.println(this.instanceId);
    }
}

class ParentA extends BaseA {
    public ParentA(String instanceId) {
        super(instanceId);
    }
}

public class GenericTest {
    static public <T extends BaseA> void printGeneric(T instance) {
        instance.printInstanceName();
    }

    public static void main(String[] args) {
        BaseA baseA = new BaseA("BaseA-1");
        ParentA parentA = new ParentA("ParentA-5");

        printGeneric(parentA);
        printGeneric(baseA);
    }
}
```
