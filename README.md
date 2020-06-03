# java-poc
Proof of non-trivial Java Concepts (Threads, Class Loading, I/O, Generics, Lambdas, etc

# Java Interview Tests
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
