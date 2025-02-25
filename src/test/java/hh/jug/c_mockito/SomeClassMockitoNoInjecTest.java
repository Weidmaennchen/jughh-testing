package hh.jug.c_mockito;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SomeClassMockitoNoInjecTest {
    @Test
    void testSomeMethod() {
        SomeClassNoInjec someClass = new SomeClassNoInjec();
        LocalDate mockedDate = LocalDate.of(2000, 1, 5);

        try (MockedStatic<LocalDate> topDateTimeUtilMock = Mockito.mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS)) {
            topDateTimeUtilMock.when(() -> LocalDate.now()).thenReturn(mockedDate);
            long returnedDays = someClass.daysSince2000();
            assertEquals(4, returnedDays);
        }
    }
}
