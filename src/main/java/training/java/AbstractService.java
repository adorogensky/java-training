package training.java;

public abstract class AbstractService {

	private final String callName;

	private final int callDurationInSeconds;

	public AbstractService(String callName, int callDurationInSeconds) {
		this.callName = callName;
		this.callDurationInSeconds = callDurationInSeconds;
	}

	String getCallName() {
		return callName;
	}

	public void run() {
		System.out.printf(
			"Simulating %d s call %s in thread with id = %d",
				callDurationInSeconds, callName, Thread.currentThread().getId()
		);

		try {
			Thread.sleep(1000 * callDurationInSeconds);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(ex);
		}
	}
}
