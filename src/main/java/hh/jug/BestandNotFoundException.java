package hh.jug;

import java.time.LocalDate;

public class BestandNotFoundException extends RuntimeException {
	public BestandNotFoundException(String kontonr, LocalDate now) {
	}
}
