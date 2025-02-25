package hh.jug.c_mockito;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SomeClassNoMockitoTest {
	@Test
	void testSomeMethod() {
		LocalDate someDate = LocalDate.of(2000, 1, 5);
		SomeClassWithSupplier someClass = new SomeClassWithSupplier(() -> someDate);

		long returnedDays = someClass.daysSince2000();

		assertEquals(4, returnedDays);
	}
}
