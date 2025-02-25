package hh.jug.c_mockito;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

class SomeClassMockitoTest {
	private static final LocalDate LOCAL_DATE = LocalDate.of(2000, 1, 5);
	private AutoCloseable closeable;

	@InjectMocks
	private SomeClass someClass;
	@Mock
	private Clock clock;

	private Clock fixedClock;

	@BeforeEach
	void initMocks() {
		closeable = MockitoAnnotations.openMocks(this);

		//tell your tests to return the specified LOCAL_DATE when calling LocalDate.now(clock)
		fixedClock = Clock.fixed(LOCAL_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
		doReturn(fixedClock.instant()).when(clock).instant();
		doReturn(fixedClock.getZone()).when(clock).getZone();
	}

	@AfterEach
	void closeMocks() throws Exception {
		closeable.close();
	}

	@Test
	void testSomeMethod() {
		long returnedDays = someClass.daysSince2000();

		assertEquals(4, returnedDays);
	}
}
