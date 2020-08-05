package training.threads;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadStateTests {

    /**
     * NEW - has not yet started
     * RUNNABLE - executing in the JVM but may be waiting for operating system resources
     * TERMINATED - completed execution
     */
    @Test
    public void testThreadStates_beforeThreadStarted_afterThreadStarted_afterThreadFinished() throws Exception {
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

    /*
        BLOCKED
     */
    @Test
    public void testThreadStates_beforeSynchronizedBlock() throws Exception {
        Thread t = new Thread(
            () -> {
                synchronized (this) { }
            }
        );

        synchronized (this) {
            t.start();
            Thread.sleep(100);
            System.out.printf(
                    "Thread state before synchronized block: %s\n", t.getState()
            );
        }
    }

    /*
        WAITING
     */
    @Test
    public void testThreadStates_beforeReentrantLock_lock() throws Exception {
        final Lock monitor = new ReentrantLock();
        monitor.lock();

        Thread t = new Thread(monitor::lock);

        t.start();
        Thread.sleep(100);
        System.out.printf(
            "Thread state before ReentrantLock.lock(): %s\n", t.getState()
        );
    }

    /*
        WAITING
     */
    @Test
    public void testThreadStates_beforeWait() throws Exception {
        Thread t = new Thread(
            () -> {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(ex);
                    }
                }
            }
        );

        t.start();
        Thread.sleep(100);
        System.out.printf(
            "Thread state before wait(): %s\n", t.getState()
        );
    }

    /*
      TIMED_WAITING
     */
    @Test
    public void testThreadStates_beforeTimedWait() throws Exception {
        Thread t = new Thread(
            () -> {
                synchronized (this) {
                    try {
                        this.wait(500);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(ex);
                    }
                }
            }
        );

        t.start();
        Thread.sleep(100);
        System.out.printf(
            "Thread state before wait(): %s\n", t.getState()
        );
    }
}
