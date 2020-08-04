package training.java;

public class ServiceProcedure extends AbstractService implements Runnable {

	public ServiceProcedure(String callName, int callDuration) {
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
