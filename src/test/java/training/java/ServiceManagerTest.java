package training.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceManagerTest {

	@Mock
	private ServiceFunction<String> serviceA;

	@Mock
	private ServiceFunction<String> serviceB;

	LocalTime testStartTime;

	@BeforeEach
	public void setUp() {
		testStartTime = LocalTime.now();
	}

	@Test
	void testShortRunningServiceCalls() {
		when(serviceA.call()).thenReturn("A");
		when(serviceB.call()).thenReturn("BB");

		ServiceManager<String> serviceManager = new ServiceManager(serviceA, serviceB);
		List<String> result = serviceManager.run();

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

		ServiceManager<String> serviceManager = new ServiceManager(serviceA, serviceB);
		List<String> result = serviceManager.run();

		System.out.printf("Call A delay: %ss\n", callADelay);
		System.out.printf("Call B delay: %ss\n", callBDelay);
		System.out.printf("Aggregate call elapsed time: %d ms\n", Duration.between(testStartTime, LocalTime.now()).toMillis());
		assertArrayEquals(new String[] {"A", "BB"}, result.toArray());
	}
}
