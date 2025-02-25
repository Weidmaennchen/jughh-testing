package hh.jug;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StupidTests {

    @Test
    void minorsMayNotEnterAdultsOnlyBar() {
        Person child = Person.ofAge(17);
        Venue bar = new AdultsOnlyBar();

        FunTimeResult result = child.haveFunTime(bar);

        assertEquals(FunTimeResult.UNHAPPY_BECAUSE_NO_ENTRY, result);
    }

    private record Person(int age) {
        public static Person ofAge(int age) {
            return new Person(age);
        }

        public FunTimeResult haveFunTime(Venue venue) {
            return venue instanceof AdultsOnlyBar && age < 18 ?
                    FunTimeResult.UNHAPPY_BECAUSE_NO_ENTRY :
                    FunTimeResult.MUCH_FUN;
        }
    }

    private interface Venue {
    }

    private static class AdultsOnlyBar implements Venue {

    }

    private enum FunTimeResult{
        MUCH_FUN,
        UNHAPPY_BECAUSE_NO_ENTRY
    }
}
