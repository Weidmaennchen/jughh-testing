package hh.jug.c_mockito;

import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SomeClass {
    public static final LocalDate BEGINNING_OF_2000 = LocalDate.of(2000, 1, 1);
    private final Clock clock;

    public SomeClass(Clock clock) {
        this.clock = clock;
    }

    public long daysSince2000() {
        LocalDate now = LocalDate.now(clock);
        return ChronoUnit.DAYS.between(BEGINNING_OF_2000, now);
    }
}
