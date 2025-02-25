package hh.jug.c_mockito;

import java.time.Clock;
import java.time.LocalDate;
import java.util.function.Supplier;

public class SomeClassWithSupplier {
    private final Supplier<LocalDate> now;

    public SomeClassWithSupplier(Supplier<LocalDate> now) {
        this.now = now;
    }

    public LocalDate someMethod(){
        return now.get();
    }
}
