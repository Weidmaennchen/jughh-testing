package hh.jug.d_rendite;

public record Transaktion(Art art, double wert) {

    public enum Art {
        BUCHUNG,
        GEBUEHR,
        ZINSEN
    }
}
