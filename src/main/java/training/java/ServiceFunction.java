package training.java;

import java.util.concurrent.Callable;

public class ServiceFunction<ResultType> extends AbstractService implements Callable<ResultType> {

	private ResultType result;

	public ServiceFunction(String callName, ResultType result, int callDurationInSeconds) {
		super(callName, callDurationInSeconds);
		this.result = result;
	}

	public ResultType call() {
		run();

		System.out.printf(
			"%s is completed with a result %s in thread with id = %d\n",
			getCallName(), result, Thread.currentThread().getId()
		);

		return result;
	}
}
