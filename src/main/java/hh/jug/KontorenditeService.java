package hh.jug;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

public class KontorenditeService {
	private final WaehrungskursHttpClient waehrungskursHttpClient;
	private final KontoDatenbank kontoDatenbank;

	public KontorenditeService(WaehrungskursHttpClient waehrungskursHttpClient, KontoDatenbank kontoDatenbank) {
		this.waehrungskursHttpClient = waehrungskursHttpClient;
		this.kontoDatenbank = kontoDatenbank;
	}

	public Rendite calculateJahresrenditeThisYearSoFar(String kontonr) {
		LocalDate now = LocalDate.now();
		LocalDate firstDayOfYear = now.with(firstDayOfYear());

		Kontostand anfangsWertInWaehrung = kontoDatenbank.getKontostand(kontonr, firstDayOfYear);
		if (anfangsWertInWaehrung == null) {
			throw new BestandNotFoundException(kontonr, firstDayOfYear);
		}
		Kontostand endwertInWaehrung = kontoDatenbank.getKontostand(kontonr, now);
		if (endwertInWaehrung == null) {
			throw new BestandNotFoundException(kontonr, now);
		}

		double diff = endwertInWaehrung.wert() - anfangsWertInWaehrung.wert();
		List<Transaktion> transaktionen = kontoDatenbank.getTransaktionen(kontonr, firstDayOfYear, now);

		double profitInKontoWaehrung = profitInKontoWaehrung(diff, transaktionen);

		double endwertInEuro = inEuro(anfangsWertInWaehrung.waehrung(), endwertInWaehrung.wert(), now);
		double anfangswertInEuro = inEuro(anfangsWertInWaehrung.waehrung(), anfangsWertInWaehrung.wert(), firstDayOfYear);
		double diffInEuro = endwertInEuro - anfangswertInEuro;
		double profitInEuro = profitInEuro(diffInEuro, transaktionen);

		return new Rendite(profitInKontoWaehrung, profitInEuro);
	}

	private double inEuro(String waehrung, double wert, LocalDate firstDayOfYear) {
		return waehrungskursHttpClient.toEuro(waehrung, wert, firstDayOfYear);
	}

	private double profitInEuro(double diff, List<Transaktion> transaktionen) {
		double result = diff;
		for (Transaktion transaktion : transaktionen) {
			if (transaktion.art() == Transaktion.Art.BUCHUNG) {
				result -= transaktion.wertInEuro();
			}
		}
		return result;
	}

	private double profitInKontoWaehrung(double diff, List<Transaktion> transaktionen) {
		double result = diff;
		for (Transaktion transaktion : transaktionen) {
			if (transaktion.art() == Transaktion.Art.BUCHUNG) {
				result -= transaktion.wertInKontowaehrung();
			}
		}
		return result;
	}
}