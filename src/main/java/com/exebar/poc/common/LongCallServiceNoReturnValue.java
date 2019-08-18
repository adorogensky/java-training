package com.exebar.poc.common;

public class LongCallServiceNoReturnValue extends LongCallService implements Runnable {

	public LongCallServiceNoReturnValue(String callName, int callDuration) {
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
