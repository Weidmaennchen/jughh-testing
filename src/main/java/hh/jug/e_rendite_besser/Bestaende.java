package hh.jug.e_rendite_besser;

import hh.jug.d_rendite.Kontostand;

import java.time.LocalDate;
import java.util.Optional;

public interface Bestaende {
    Optional<Kontostand> findBestand(String kontonummer, LocalDate date);
}
