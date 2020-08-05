package training.collections;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HashMapWritePerformanceTests {

    private int maxPermCount = 1_000_000;

    private int permCount = 0;

    private LocalTime startTime;

    private final Map<String, String> map = new HashMap<>();

    private final Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>());

    private void benchmarkMapWritePerformance(char startChar, int len, int pos, char[] wip, Map<String, String> map) {
        for (char c = startChar; c < startChar + len && permCount < maxPermCount; c++) {
            wip[pos] = c;

            if (pos < len - 1) {
                benchmarkMapWritePerformance(startChar, len, pos + 1, wip, map);
            } else {
                map.put(new String(wip), new String(wip));
                permCount++;
            }
        }
    }

    @Test
    public void test_HashMap_vs_synchronizedHashMap() {
        int hashMapWriteTime = 0;
        int maxAttemps = 1;

        for (int attemptNo = 1; attemptNo <= maxAttemps; attemptNo++) {
            map.clear();
            startTime = LocalTime.now();
            permCount = 0;
            benchmarkMapWritePerformance('a', 26, 0, new char[26], map);
            hashMapWriteTime += Duration.between(startTime, LocalTime.now()).toMillis();
        }

        System.out.printf("HashMap average write time of %d records: %d ms\n", maxPermCount, hashMapWriteTime / maxAttemps);

        int synchronizedHashMapWriteTime = 0;

        for (int attemptNo = 1; attemptNo <= maxAttemps; attemptNo++) {
            syncMap.clear();
            startTime = LocalTime.now();
            permCount = 0;
            benchmarkMapWritePerformance('a', 26, 0, new char[26], syncMap);
            synchronizedHashMapWriteTime += Duration.between(startTime, LocalTime.now()).toMillis();
        }

        System.out.printf("Synchronized HashMap average write time of %d records: %d ms\n", maxPermCount, synchronizedHashMapWriteTime / maxAttemps);

        System.out.printf("Synchronized HashMap average write time is slower by %2.2f%%\n", ((float) synchronizedHashMapWriteTime / hashMapWriteTime - 1) * 100);
    }
}
