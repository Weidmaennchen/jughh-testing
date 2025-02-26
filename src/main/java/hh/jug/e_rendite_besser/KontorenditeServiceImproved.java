package hh.jug.e_rendite_besser;

import hh.jug.d_rendite.BestandNotFoundException;
import hh.jug.d_rendite.Kontostand;
import hh.jug.d_rendite.Rendite;
import hh.jug.d_rendite.Transaktion;

import java.time.LocalDate;
import java.util.function.Supplier;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

public class KontorenditeServiceImproved {
    private final Transaktionen transaktionen;
    private final Bestaende bestaende;
    private final Supplier<LocalDate> now;

    public KontorenditeServiceImproved(Transaktionen transaktionen, Bestaende bestaende, Supplier<LocalDate> now) {
        this.transaktionen = transaktionen;
        this.bestaende = bestaende;
        this.now = now;
    }

    public Rendite calculateJahresrenditeThisYearSoFar(String kontonr) {
        LocalDate firstDayOfYear = now.get().with(firstDayOfYear());

        double rendite = findRenditeFromTransaktionen(kontonr, firstDayOfYear, transaktionen);
        double anfangsbestand = findBestand(kontonr, firstDayOfYear, bestaende);

        Double renditeInPercent = nullSafeDivision(rendite, anfangsbestand);

        return new Rendite(rendite, renditeInPercent);
    }

    private static double findRenditeFromTransaktionen(String kontonr, LocalDate firstDayOfYear, Transaktionen transaktionen) {
        Iterable<Transaktion> thisYearTransaktionen = transaktionen.findAllYoungerThan(kontonr, firstDayOfYear);
        double rendite = 0;
        for (Transaktion transaktion : thisYearTransaktionen) {
            switch (transaktion.art()) {
                case BUCHUNG -> {
                    // Buchung does not change Rendite
                }
                case GEBUEHR, ZINSEN -> rendite += transaktion.wert();
            }
        }
        return rendite;
    }

    private static Double findBestand(String kontonr, LocalDate firstDayOfYear, Bestaende bestaende) {
        return bestaende.findBestand(kontonr, firstDayOfYear)
                .map(Kontostand::wert)
                .orElseThrow(() -> new BestandNotFoundException(kontonr, firstDayOfYear));
    }

    private static Double nullSafeDivision(double zaehler, double nenner) {
        if (nenner == 0)
            return null;
        return zaehler / nenner;
    }
}
