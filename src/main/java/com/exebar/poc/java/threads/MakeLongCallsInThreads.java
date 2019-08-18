package com.exebar.poc.java.threads;

import com.exebar.poc.common.LongCallServiceNoReturnValue;

public class MakeLongCallsInThreads {

	void run() {
		Runnable threadBodyA = new LongCallServiceNoReturnValue("callA()", 5);
		Runnable threadBodyB = new LongCallServiceNoReturnValue("callB()", 13);

		new Thread(threadBodyA).start();
		new Thread(threadBodyB).start();
	}

	public static void main(String... args) {
		MakeLongCallsInThreads test = new MakeLongCallsInThreads();
		test.run();
	}
}
