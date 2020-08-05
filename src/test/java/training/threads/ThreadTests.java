package training.threads;

import org.junit.jupiter.api.Test;

public class ThreadTests {

    /**
     * Thread id is a positive long number generated when a thread is created
     * (not necessarily when it's in a running state).
     * It is unique and remain unchanged during its lifetime. When a thread is
     * terminated, this thread ID may be reused.
     */
    @Test
    public void testThreadId() {
        long newThreadId = new Thread().getId();
        System.out.printf("New thread ID is %d\n", newThreadId);
    }
}
