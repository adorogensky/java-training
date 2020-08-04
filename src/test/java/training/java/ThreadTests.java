package training.java;

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


    /**
     * NEW, RUNNABLE, TERMINATED
     * @throws Exception
     */
    @Test
    public void testThreadStates() throws Exception {
        Thread t = new Thread(
            () -> {
                System.out.println("Thread state after start: " + Thread.currentThread().getState());
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread state after interrupt: " + Thread.currentThread().getState());
                }
            }
        );

        System.out.println("Thread state before start: " + t.getState());
        t.start();
        t.interrupt();
        t.join();
        System.out.println("Thread state after it is finished: " + t.getState());
    }
}
