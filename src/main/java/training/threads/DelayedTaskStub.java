package training.threads;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.Callable;

/*
	Simulates task execution with a given result that takes a certain time.
 */
public class DelayedTaskStub<ResultType> implements Callable<ResultType> {

	private final String name;

	private final int delayInSeconds;

	private ResultType result;

	public DelayedTaskStub(String name, ResultType result, int delayInSeconds) {
		this.name = name;
		this.result = result;
		this.delayInSeconds = delayInSeconds;
	}

	public String getName() {
		return name;
	}

	public ResultType getResult() {
		return result;
	}

	public ResultType call() {
		LocalTime taskStartTime = LocalTime.now();

		try {
			Thread.sleep(1000 * delayInSeconds);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(ex);
		}

		System.out.printf(
			"[Thread ID = %d] Task %s took %d ms to complete with a result \"%s\"\n",
			Thread.currentThread().getId(),
			getName(),
			Duration.between(taskStartTime, LocalTime.now()).toMillis(),
			result
		);

		return result;
	}
}
