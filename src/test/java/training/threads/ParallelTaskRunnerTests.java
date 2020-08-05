package training.threads;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelTaskRunnerTests {

	private DelayedTaskStub<String> taskA;

	private DelayedTaskStub<String> taskB;

	@SafeVarargs
	private final List<String> getTaskResults(DelayedTaskStub<String>... tasks) {
		return Stream.of(tasks).map(
			DelayedTaskStub::getResult
		).collect(Collectors.toList());
	}
	@Test
	void testShortRunningTasks() {
		taskA = new DelayedTaskStub<>("A", "A", 0);
		taskB = new DelayedTaskStub<>("B", "BB", 0);

		ParallelTaskRunner<String> taskRunner = new ParallelTaskRunner<>(taskA, taskB);

		assertEquals(getTaskResults(taskA, taskB), taskRunner.run());
	}

	@Test
	void testLongRunningTasks() {
		taskA = new DelayedTaskStub<>("A", "A", 3);
		taskB = new DelayedTaskStub<>("B", "BB", 2);

		ParallelTaskRunner<String> taskRunner = new ParallelTaskRunner<>(taskA, taskB);

		assertEquals(getTaskResults(taskA, taskB), taskRunner.run());
	}
}
