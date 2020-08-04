package training.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AsyncServiceOrchestratorTest {

	@Mock
	private Service<String> serviceA;

	@Mock
	private Service<String> serviceB;

	LocalTime testStartTime;

	@BeforeEach
	public void setUp() {
		testStartTime = LocalTime.now();
	}

	@Test
	void testShortRunningServiceCalls() {
		when(serviceA.call()).thenReturn("A");
		when(serviceB.call()).thenReturn("BB");

		AsyncServiceOrchestrator<String> asyncServiceOrchestrator = new AsyncServiceOrchestrator(serviceA, serviceB);
		List<String> result = asyncServiceOrchestrator.run();

		System.out.println("Call A delay: none");
		System.out.println("Call B delay: none");
		System.out.printf("Aggregate call elapsed time: %d ms\n", Duration.between(testStartTime, LocalTime.now()).toMillis());
		assertArrayEquals(new String[] {"A", "BB"}, result.toArray());
	}

	@Test
	void testLongRunningServiceCalls() {
		int callADelay = 2;
		int callBDelay = 3;

		when(serviceA.call()).thenAnswer(
			invocationOnMock -> {
				Thread.sleep(callADelay * 1000);
				return "A";
			}
		);

		when(serviceB.call()).thenAnswer(
			invocationOnMock -> {
				Thread.sleep(callBDelay * 1000);
				return "BB";
			}
		);

		AsyncServiceOrchestrator<String> asyncServiceOrchestrator = new AsyncServiceOrchestrator(serviceA, serviceB);
		List<String> result = asyncServiceOrchestrator.run();

		System.out.printf("Call A delay: %ss\n", callADelay);
		System.out.printf("Call B delay: %ss\n", callBDelay);
		System.out.printf("Aggregate call elapsed time: %d ms\n", Duration.between(testStartTime, LocalTime.now()).toMillis());
		assertArrayEquals(new String[] {"A", "BB"}, result.toArray());
	}
}
