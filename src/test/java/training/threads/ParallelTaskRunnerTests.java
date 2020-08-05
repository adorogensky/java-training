package training.threads;

import org.junit.jupiter.api.Test;
import training.DelayedTaskStub;
import training.ParallelTaskRunner;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ParallelTaskRunnerTests {

	private DelayedTaskStub<String> taskA;

	private DelayedTaskStub<String> taskB;

	@Test
	void testShortRunningTasks() {
		taskA = new DelayedTaskStub<>("A", "A", 0);
		taskB = new DelayedTaskStub<>("B", "BB", 0);

		ParallelTaskRunner<String> taskRunner = new ParallelTaskRunner<>(taskA, taskB);

		assertArrayEquals(new String[] { taskA.getResult(), taskB.getResult() }, taskRunner.run().toArray());
	}

	@Test
	void testLongRunningTasks() {
		taskA = new DelayedTaskStub<>("A", "A", 3);
		taskB = new DelayedTaskStub<>("B", "BB", 2);

		ParallelTaskRunner<String> taskRunner = new ParallelTaskRunner<>(taskA, taskB);

		assertArrayEquals(new String[] { taskA.getResult(), taskB.getResult() }, taskRunner.run().toArray());
	}
}
