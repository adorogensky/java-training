package training.java;

import java.util.concurrent.Callable;

public class ServiceFunction<ResultType> extends AbstractService implements Callable<ResultType> {

	private ResultType callResult;

	public ServiceFunction(String callName, ResultType callResult, int callDuration) {
		super(callName, callDuration);
		this.callResult = callResult;
	}

	public ResultType call() {
		run();

		System.out.println(
			getCallName() + " is completed with a callResult " +
			callResult + " in thread with id = " + Thread.currentThread().getId()
		);

		return callResult;
	}
}
