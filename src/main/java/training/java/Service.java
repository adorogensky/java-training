package training.java;

import java.util.concurrent.Callable;

public class Service<ResultType> implements Callable<ResultType> {

	private final String callName;

	private final int callDurationInSeconds;

	private ResultType result;

	public Service(String callName, int callDurationInSeconds) {
		this.callName = callName;
		this.callDurationInSeconds = callDurationInSeconds;
	}

	String getCallName() {
		return callName;
	}

	public ResultType call() {
		try {
			Thread.sleep(1000 * callDurationInSeconds);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(ex);
		}

		// not printing to the console.. why?
		System.out.printf(
			"%s is completed with a result %s in thread with id = %d\n",
			getCallName(), result, Thread.currentThread().getId()
		);

		return result;
	}
}
