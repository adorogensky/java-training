package com.exebar.poc.java.threads;

import com.exebar.poc.common.ServiceThatReturnsValue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServiceManager {

	Callable<String> serviceA;

	Callable<String> serviceB;

	public final ExecutorService threadPool = Executors.newFixedThreadPool(2);

	ServiceManager(Callable<String> serviceA, Callable<String> serviceB) {
		this.serviceA = serviceA;
		this.serviceB = serviceB;
	}

	List<String> run() throws InterruptedException, ExecutionException {

		Future<String> serviceAFutureResult = threadPool.submit(serviceA);
		Future<String> serviceBFutureResult = threadPool.submit(serviceB);

		while (!serviceAFutureResult.isDone() || !serviceBFutureResult.isDone()) {
			System.out.println(
				"Waiting for future execution results in the main thread with id = " +
				Thread.currentThread().getId()
			);
			Thread.sleep(1000);
		}

		return Arrays.asList(serviceAFutureResult.get(), serviceBFutureResult.get());
	}

	public static void main(String... args) throws Exception {
		ServiceManager serviceManager = new ServiceManager(
			new ServiceThatReturnsValue<>(
				"callA()", "A", 5
			),
			new ServiceThatReturnsValue<>(
				"callB()", "BB", 6
			)
		);

		List<String> result = serviceManager.run();

		System.out.println(
			"Result: " + Arrays.toString(
				new String[] {
					result.get(0),
					result.get(1)
				}
			)
		);
	}
}
