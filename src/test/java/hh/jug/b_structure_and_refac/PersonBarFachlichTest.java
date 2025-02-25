package hh.jug.b_structure_and_refac;

import hh.jug.b_structure_and_refac.sceneb.AdultsOnlyBar;
import hh.jug.b_structure_and_refac.sceneb.FunTimeResult;
import hh.jug.b_structure_and_refac.sceneb.Person;
import hh.jug.b_structure_and_refac.sceneb.Venue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonBarFachlichTest {

    @Test
    void minorsMayNotEnterAdultsOnlyBar() {
        Person child = Person.ofAge(17);
        Venue bar = new AdultsOnlyBar();

        FunTimeResult result = child.haveFunTime(bar);
        assertEquals(FunTimeResult.UNHAPPY_BECAUSE_NO_ENTRY, result);
    }

}
