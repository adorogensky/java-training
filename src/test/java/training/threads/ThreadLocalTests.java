package training.threads;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.*;

public class ThreadLocalTests {

    private static class Task implements Callable<String> {

        private static final ThreadLocal<Long> threadLocal = ThreadLocal.withInitial(
            () -> Thread.currentThread().getId()
        );

        private final String name;

        private Task(String name) {
            this.name = name;
        }

        @Override
        public String call() {
            return name + threadLocal.get();
        }
    }

    @Test
    public void test() throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        threadPool.invokeAll(
            Arrays.asList(
                new Task("A"),
                new Task("B")
            )
        ).forEach(
            taskResult -> {
                try {
                    System.out.println(taskResult.get());
                } catch (InterruptedException | ExecutionException ex) {
                    throw new RuntimeException(ex);
                }
            }
        );
    }
}
