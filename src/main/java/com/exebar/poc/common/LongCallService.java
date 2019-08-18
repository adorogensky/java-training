package com.exebar.poc.common;

import java.util.Date;

public abstract class LongCallService {

	private String callName;

	private int callDuration;

	LongCallService(String callName, int callDuration) {
		this.callName = callName;
		this.callDuration = callDuration;
	}

	String getCallName() {
		return callName;
	}

	public void run() {
		Long startTime = new Date().getTime();

		while (new Date().getTime() - startTime < 1000 * callDuration) {
			try {
				System.out.println(
					"Simulating " + callDuration + "s call " + callName +
					" in thread with id = " + Thread.currentThread().getId()
				);
				Thread.sleep(2000);
			} catch (InterruptedException ex) {
				System.out.println(ex);
			}
		}
	}
}
