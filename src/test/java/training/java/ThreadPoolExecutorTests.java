package training.java;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.*;

public class ThreadPoolExecutorTests {

    /*
        TODO: How is this max thread limit calculated?
        TODO: Is there a way to increase it?
        Failed to start thread - pthread_create failed (EAGAIN) for attributes: stacksize: 1024k, guardsize: 0k, detached.
        TODO: Why is this limit different when running from toolbox vs running from intellij?
     */
    @Test
    public void testMaxThreads() {
        LocalTime startTime = LocalTime.now();

        // when running single test from IntelliJ
        // int maxThreadCount = 18_674;

        // when running single test from toolbox
        // ./gradlew clean test --tests ThreadPoolExecutorTests
        int maxThreadCount = 1_935;
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                maxThreadCount,
                maxThreadCount,
                Long.MAX_VALUE,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()
        );

        threadPool.prestartAllCoreThreads();
        System.out.println("Pool size: " + threadPool.getPoolSize());
        System.out.printf("Elapsed time %d ms\n", Duration.between(startTime, LocalTime.now()).toMillis());
    }

}
