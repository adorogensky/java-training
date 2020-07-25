package com.exebar.poc.common;

public abstract class AbstractService {

	private final String callName;

	private final int callDuration;

	AbstractService(String callName, int callDuration) {
		this.callName = callName;
		this.callDuration = callDuration;
	}

	String getCallName() {
		return callName;
	}

	public void run() {
		System.out.printf(
			"Simulating %d s call %s in thread with id = %d",
			callDuration, callName, Thread.currentThread().getId()
		);

		try {
			Thread.sleep(1000 * callDuration);
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}
}
