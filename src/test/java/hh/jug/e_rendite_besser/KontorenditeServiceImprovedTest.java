package hh.jug.e_rendite_besser;

import hh.jug.d_rendite.Kontostand;
import hh.jug.d_rendite.Rendite;
import hh.jug.d_rendite.Transaktion;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class KontorenditeServiceImprovedTest {
    private final LocalDate mockedDate = LocalDate.of(2024, 11, 25);
    private final LocalDate beginningOfYear = LocalDate.of(2024, 1, 1);

    @Test
    void calculatePositiveRenditeWorksWithMultipleTransaktionen() {
        double ausgangswert = 2000;
        List<Transaktion> mockedTransaktionen = List.of(
                new Transaktion(Transaktion.Art.BUCHUNG, 500),
                new Transaktion(Transaktion.Art.ZINSEN, 40),
                new Transaktion(Transaktion.Art.BUCHUNG, -200),
                new Transaktion(Transaktion.Art.GEBUEHR, -20)
        );

        KontorenditeServiceImproved service = setupService(mockedTransaktionen, ausgangswert);

        Rendite rendite = service.calculateJahresrenditeThisYearSoFar("testkontonr");

        Rendite expected = new Rendite(20, 0.01);
        assertEquals(expected, rendite);
    }

    @Test
    void zinsenArePositiveRendite() {
        double ausgangswert = 2000;
        List<Transaktion> mockedTransaktionen = List.of(
                new Transaktion(Transaktion.Art.ZINSEN, 20)
        );

        KontorenditeServiceImproved service = setupService(mockedTransaktionen, ausgangswert);

        Rendite rendite = service.calculateJahresrenditeThisYearSoFar("testkontonr");

        Rendite expected = new Rendite(20, 0.01);
        assertEquals(expected, rendite);
    }

    @Test
    void gebuehrenAreNegativeRendite() {
        double ausgangswert = 2000;
        List<Transaktion> mockedTransaktionen = List.of(
                new Transaktion(Transaktion.Art.GEBUEHR, -20)
        );

        KontorenditeServiceImproved service = setupService(mockedTransaktionen, ausgangswert);

        Rendite rendite = service.calculateJahresrenditeThisYearSoFar("testkontonr");

        Rendite expected = new Rendite(-20, -0.01);
        assertEquals(expected, rendite);
    }

    @Test
    void buchungenAreNeutralRendite() {
        double ausgangswert = 2000;
        List<Transaktion> mockedTransaktionen = List.of(
                new Transaktion(Transaktion.Art.BUCHUNG, -20)
        );

        KontorenditeServiceImproved service = setupService(mockedTransaktionen, ausgangswert);

        Rendite rendite = service.calculateJahresrenditeThisYearSoFar("testkontonr");

        Rendite expected = new Rendite(0, .0);
        assertEquals(expected, rendite);
    }

    @Test
    void relativeRenditeIsNullWhenAusgangswert0() {
        double ausgangswert = 0;
        List<Transaktion> mockedTransaktionen = List.of(
                new Transaktion(Transaktion.Art.GEBUEHR, -20)
        );

        KontorenditeServiceImproved service = setupService(mockedTransaktionen, ausgangswert);

        Rendite rendite = service.calculateJahresrenditeThisYearSoFar("testkontonr");

        assertNull(rendite.relative());
    }

    @Test
    void calculatePositiveRenditeWithoutFakes() {
        Kontostand ausgangswert = new Kontostand("EUR", 2000);
        List<Transaktion> mockedTransaktionen = List.of(
                new Transaktion(Transaktion.Art.BUCHUNG, 500),
                new Transaktion(Transaktion.Art.ZINSEN, 40),
                new Transaktion(Transaktion.Art.BUCHUNG, -200),
                new Transaktion(Transaktion.Art.GEBUEHR, -20)
        );

        KontorenditeServiceImproved service = new KontorenditeServiceImproved(
                (kontonummer, date) -> mockedTransaktionen,
                (kontonummer, date) -> Optional.of(ausgangswert),
                () -> mockedDate);

        Rendite rendite = service.calculateJahresrenditeThisYearSoFar("testkontonr");

        Rendite expected = new Rendite(20, 0.01);
        assertEquals(expected, rendite);
    }


    private KontorenditeServiceImproved setupService(List<Transaktion> mockedTransaktionen, double ausgangswert) {
        Transaktionen transaktionen = new TransaktionenFake("testkontonr", beginningOfYear, mockedTransaktionen);
        Bestaende bestaende = new BestaendeFake("testkontonr", ausgangswert, beginningOfYear);
        return new KontorenditeServiceImproved(transaktionen, bestaende, () -> mockedDate);
    }

    private static class BestaendeFake implements Bestaende {
        private final String testkontonr;
        private final double wert;
        private final LocalDate beginningOfYear;

        public BestaendeFake(String testkontonr, double wert, LocalDate beginningOfYear) {
            this.testkontonr = testkontonr;
            this.wert = wert;
            this.beginningOfYear = beginningOfYear;
        }

        @Override
        public Optional<Kontostand> findBestand(String kontonummer, LocalDate date) {
            if (kontonummer.equals(testkontonr) && date.isEqual(beginningOfYear))
                return Optional.of(new Kontostand("EUR", wert));
            return Optional.empty();
        }
    }

    private static class TransaktionenFake implements Transaktionen {
        private final String testkontonr;
        private final LocalDate beginningOfYear;
        private final List<Transaktion> mockedTransaktionen;

        public TransaktionenFake(String testkontonr, LocalDate beginningOfYear, List<Transaktion> mockedTransaktionen) {
            this.testkontonr = testkontonr;
            this.beginningOfYear = beginningOfYear;
            this.mockedTransaktionen = mockedTransaktionen;
        }

        @Override
        public Iterable<Transaktion> findAllYoungerThan(String kontonummer, LocalDate earliest) {
            if (kontonummer.equals(testkontonr) && earliest.isEqual(beginningOfYear))
                return mockedTransaktionen;
            return List.of();
        }
    }
}