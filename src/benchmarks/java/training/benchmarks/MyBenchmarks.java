package training.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1)
public class MyBenchmarks {

    @Benchmark
    @Warmup(iterations = 10, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 10, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public void do_nothing() {}
}
