package training.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class AsyncServiceOrchestrator<ResultType> {

	private Service<ResultType>[] services;

	public final ExecutorService threadPool;

	public AsyncServiceOrchestrator(Service<ResultType>... services) {
		this.services = services;
		threadPool = Executors.newFixedThreadPool(services.length);
	}

	List<ResultType> run() {
		List<Future<ResultType>> futures = Arrays.stream(services).map(
			threadPool::submit
		).collect(Collectors.toList());

		List<ResultType> result = new ArrayList<>();
		futures.forEach(
			future -> {
				try {
					result.add(future.get());
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			}
		);

		return result;
	}
}
