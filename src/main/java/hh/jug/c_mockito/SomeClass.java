package hh.jug.c_mockito;

import java.time.Clock;
import java.time.LocalDate;

public class SomeClass {
    private final Clock clock;

    public SomeClass(Clock clock) {
        this.clock = clock;
    }

    public LocalDate someMethod(){
        return LocalDate.now(clock);
    }
}
