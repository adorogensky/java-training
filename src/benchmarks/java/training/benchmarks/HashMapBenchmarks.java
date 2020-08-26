package training.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class HashMapBenchmarks {

    private volatile Map<String, String> sharedHashMap = new HashMap<>();

    private Map<String, String> sharedSyncHashMap = Collections.synchronizedMap(new HashMap<>());

    @Benchmark
    @Warmup(iterations = 10, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 10, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public Map<String, String> hash_map() {
        Map<String, String> hashMap = new HashMap<>();
        byte[] randomBytes = new byte[7];
        new Random().nextBytes(randomBytes);
        String str = new String(randomBytes);
        hashMap.put(str, "_" + str + "_");
        return hashMap;
    }

    @Benchmark
    @Warmup(iterations = 10, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 10, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public Map<String, String> sync_hash_map() {
        Map<String, String> synchronizedHashMap = Collections.synchronizedMap(new HashMap<>());
        byte[] randomBytes = new byte[7];
        new Random().nextBytes(randomBytes);
        String str = new String(randomBytes);
        synchronizedHashMap.put(str, "_" + str + "_");
        return synchronizedHashMap;
    }

    @Benchmark
    @Warmup(iterations = 10, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 10, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public void shared_hash_map() {
        byte[] randomBytes = new byte[7];
        new Random().nextBytes(randomBytes);
        String str = new String(randomBytes);
        sharedHashMap.put(str, "_" + str + "_");
    }

    @Benchmark
    @Warmup(iterations = 10, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 10, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public void shared_synch_hash_map() {
        byte[] randomBytes = new byte[7];
        new Random().nextBytes(randomBytes);
        String str = new String(randomBytes);
        sharedSyncHashMap.put(str, "_" + str + "_");
    }
}
