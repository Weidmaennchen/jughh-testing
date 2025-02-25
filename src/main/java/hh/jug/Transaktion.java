package hh.jug;

public record Transaktion(Art art, double wertInKontowaehrung, double wertInEuro) {

	public enum Art {
		BUCHUNG,
		GEBUEHR,
		ZINSEN
	}
}
