package training.threads;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Runs tasks parallelly and returns an array of task results
 * in the order the tasks were passed to the constructor
 * @param <ResultType>
 */
public class ParallelTaskRunner<ResultType> {

	private DelayedTaskStub<ResultType>[] tasks;

	public final ExecutorService threadPool;

	@SafeVarargs
	public ParallelTaskRunner(DelayedTaskStub<ResultType>... tasks) {
		this.tasks = tasks;
		threadPool = Executors.newFixedThreadPool(tasks.length);
	}

	public List<ResultType> run() {
		LocalTime startTime = LocalTime.now();
		List<Future<ResultType>> futures = Arrays.stream(tasks).map(
			threadPool::submit
		).collect(Collectors.toList());

		List<ResultType> result = new ArrayList<>();
		futures.forEach(
			future -> {
				try {
					result.add(future.get());
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			}
		);

		System.out.printf(
			"[Thread ID = %d] All tasks took %d ms to complete parallelly\n",
			Thread.currentThread().getId(),
			Duration.between(startTime, LocalTime.now()).toMillis()
		);

		return result;
	}
}
