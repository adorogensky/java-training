package training.java;

public class ServiceThatDoesNotReturnValue extends AbstractService implements Runnable {

	public ServiceThatDoesNotReturnValue(String callName, int callDuration) {
		super(callName, callDuration);
	}

	@Override
	public void run() {
		super.run();

		System.out.println(
			super.getCallName() + " is completed in thread with id = " + Thread.currentThread().getId()
		);
	}
}
