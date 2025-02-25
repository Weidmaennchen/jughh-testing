package hh.jug;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.Schaltjahr.isSchaltjahr;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SchaltjahrTest {
    @Test
    @DisplayName("Not Dividable by 4 is never Schaltjahr")
    void notDividableBy4IsNoSchaltjahr() {
        assertFalse(isSchaltjahr(1));
        assertFalse(isSchaltjahr(2));
        assertFalse(isSchaltjahr(3));
        assertFalse(isSchaltjahr(-1));
        assertFalse(isSchaltjahr(-2));
        assertFalse(isSchaltjahr(-3));
        assertFalse(isSchaltjahr(403));
        assertFalse(isSchaltjahr(100219));
    }

    @Test
    @DisplayName("Dividable by 4 is Schaltjahr")
    void dividableBy4IsSchaltjahr() {
        assertTrue(isSchaltjahr(4));
        assertTrue(isSchaltjahr(-4));
        assertTrue(isSchaltjahr(404));
        assertTrue(isSchaltjahr(104));
        assertTrue(isSchaltjahr(96));
    }

    @Test
    @DisplayName("Dividable by 100 isn't Schaltjahr")
    void dividableBy100IsNoSchaltjahr() {
        assertFalse(isSchaltjahr(100));
        assertFalse(isSchaltjahr(-100));
        assertFalse(isSchaltjahr(12300));
    }

    @Test
    @DisplayName("Dividable by 400 always is Schaltjahr")
    void dividableBy400IsSchaltjahr() {
        assertTrue(isSchaltjahr(0));
        assertTrue(isSchaltjahr(-400));
        assertTrue(isSchaltjahr(400));
        assertTrue(isSchaltjahr(4000));
        assertTrue(isSchaltjahr(4400));
    }
}
