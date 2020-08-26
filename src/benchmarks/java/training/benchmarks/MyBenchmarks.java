package training.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class MyBenchmarks {

    @Benchmark
    @Warmup(iterations = 10, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 10, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public void do_nothing() {}
}
