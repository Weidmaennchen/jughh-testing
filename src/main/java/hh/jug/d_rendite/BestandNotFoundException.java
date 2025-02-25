package hh.jug.d_rendite;

import java.time.LocalDate;

public class BestandNotFoundException extends RuntimeException {
    public BestandNotFoundException(String kontonr, LocalDate now) {
    }
}
