package de;

public class Schaltjahr {
    public static boolean isSchaltjahr(int jahr) {
        return jahr % 400 == 0 || 
                jahr % 4 == 0 && jahr % 100 != 0;
    }
}
