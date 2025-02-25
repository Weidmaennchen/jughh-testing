package hh.jug.c_mockito;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SomeClassNoMockitoTest {
    @Test
    void testSomeMethod() {
        LocalDate someDate = LocalDate.of(1989, 1, 13);
        SomeClassWithSupplier someClass = new SomeClassWithSupplier(() -> someDate);

        LocalDate returnedLocalDate = someClass.someMethod();
        assertEquals(someDate, returnedLocalDate);
    }
}
