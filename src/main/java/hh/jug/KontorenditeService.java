package hh.jug;

import jdk.jshell.spi.ExecutionControl;

import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

public class KontorenditeService {
    private final WaehrungskursService waehrungskursService;
    private final KontoDatenbank kontoDatenbank;

    public KontorenditeService(WaehrungskursService waehrungskursService, KontoDatenbank kontoDatenbank) {
        this.waehrungskursService = waehrungskursService;
        this.kontoDatenbank = kontoDatenbank;
    }

    public Jahresrendite calculateJahresrendite(String kontonr) {
        LocalDate now = LocalDate.now();
        Kontostand anfangsstand = kontoDatenbank.getKontostand(kontonr,now);
        Kontostand endstand = kontoDatenbank.getKontostand(kontonr, now.with(firstDayOfYear()));

        if(anfangsstand == null) {
            throw new InvalidKontonummerException(kontonr);
        }

        throw new RuntimeException();
    }
}