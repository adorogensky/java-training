package com.exebar.poc.java.threads;

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
	private Callable<String> serviceA;

	@Mock
	private Callable<String> serviceB;

	LocalTime testStartTime;

	@BeforeEach
	public void setUp() {
		testStartTime = LocalTime.now();
	}

	@Test
	void testFastServiceCalls() throws Exception {
		when(serviceA.call()).thenReturn("A");
		when(serviceB.call()).thenReturn("BB");

		ServiceManager serviceManager = new ServiceManager(serviceA, serviceB);
		List<String> result = serviceManager.run();

		System.out.printf("Elapsed time: %d ms\n", Duration.between(testStartTime, LocalTime.now()).toMillis());
		assertArrayEquals(new String[] {"A", "BB"}, result.toArray());
	}

	@Test
	void testLongServiceCalls() throws Exception {
		when(serviceA.call()).thenAnswer(
			invocationOnMock -> {
				Thread.sleep(5000);
				return "A";
			}
		);

		when(serviceB.call()).thenAnswer(
			invocationOnMock -> {
				Thread.sleep(7000);
				return "BB";
			}
		);

		ServiceManager serviceManager = new ServiceManager(serviceA, serviceB);
		List<String> result = serviceManager.run();

		System.out.printf("Elapsed time: %d ms\n", Duration.between(testStartTime, LocalTime.now()).toMillis());
		assertArrayEquals(new String[] {"A", "BB"}, result.toArray());
	}
}
