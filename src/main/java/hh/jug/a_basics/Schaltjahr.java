package hh.jug.a_basics;

public class Schaltjahr {
    public static boolean isSchaltjahr(int jahr) {
        if (jahr % 400 == 0) {
            return true;
        }
        if (jahr % 100 == 0) {
            return false;
        }
        return jahr % 4 == 0;
    }

    // public static boolean isSchaltjahr(int jahr) {
    //     return jahr % 400 == 0 ||
    //             jahr % 4 == 0 && jahr % 100 != 0;
    // }
}
