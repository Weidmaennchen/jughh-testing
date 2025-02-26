package hh.jug.d_rendite;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

public class KontorenditeService {
    private final KontoDatenbank kontoDatenbank;

    public KontorenditeService(KontoDatenbank kontoDatenbank) {
        this.kontoDatenbank = kontoDatenbank;
    }

    public Rendite calculateJahresrenditeThisYearSoFar(String kontonr) {
        LocalDate now = LocalDate.now();
        LocalDate firstDayOfYear = now.with(firstDayOfYear());

        Kontostand anfangsWert = kontoDatenbank.getKontostand(kontonr, firstDayOfYear);
        if (anfangsWert == null) {
            throw new BestandNotFoundException(kontonr, firstDayOfYear);
        }
        Kontostand endwert = kontoDatenbank.getKontostand(kontonr, now);
        if (endwert == null) {
            throw new BestandNotFoundException(kontonr, now);
        }

        double diff = endwert.wert() - anfangsWert.wert();
        List<Transaktion> transaktionen = kontoDatenbank.getTransaktionen(kontonr, firstDayOfYear, now);

        double renditeAbsolut = profitInWaehrung(diff, transaktionen);
        Double renditeInPercent = renditeInPercent(anfangsWert, renditeAbsolut);

        return new Rendite(renditeAbsolut, renditeInPercent);
    }

    private Double renditeInPercent(Kontostand anfangsWert, double renditeAbsolut) {
        if (anfangsWert.wert() == 0)
            return null;
        return renditeAbsolut / anfangsWert.wert();
    }

    private double profitInWaehrung(double diff, List<Transaktion> transaktionen) {
        double result = diff;
        for (Transaktion transaktion : transaktionen) {
            if (transaktion.art() == Transaktion.Art.BUCHUNG) {
                result -= transaktion.wert();
            }
        }
        return result;
    }
}