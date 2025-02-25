package hh.jug.c_mockito;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SomeClassNoInjec {
    public static final LocalDate BEGINNING_OF_2000 = LocalDate.of(2000, 1, 1);

    public long daysSince2000() {
        LocalDate now = LocalDate.now();
        return ChronoUnit.DAYS.between(BEGINNING_OF_2000, now);
    }
}
