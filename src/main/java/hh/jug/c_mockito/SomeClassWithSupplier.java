package hh.jug.c_mockito;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

public class SomeClassWithSupplier {
    public static final LocalDate BEGINNING_OF_2000 = LocalDate.of(2000, 1, 1);
    private final Supplier<LocalDate> now;

    public SomeClassWithSupplier(Supplier<LocalDate> now) {
        this.now = now;
    }

    public long daysSince2000() {
        return ChronoUnit.DAYS.between(BEGINNING_OF_2000, now.get());
    }
}
