package training.threads;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreadLocalTests {

    private static final ThreadLocal<String> threadId = ThreadLocal.withInitial(
        () -> "unknown"
    );

    @Test
    public void test() throws Exception {
        assertEquals("unknown", threadId.get());
        threadId.set("main");

        Thread t = new Thread(
            () -> {
                assertEquals("unknown", threadId.get());
                threadId.set("worker");
            }
        );
        t.start();
        t.join();
        assertEquals("main", threadId.get());
    }
}
