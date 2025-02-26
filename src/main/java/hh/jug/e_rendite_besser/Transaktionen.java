package hh.jug.e_rendite_besser;

import hh.jug.d_rendite.Transaktion;

import java.time.LocalDate;

public interface Transaktionen {
    Iterable<Transaktion> findAllYoungerThan(String kontonummer, LocalDate earliest);
}
