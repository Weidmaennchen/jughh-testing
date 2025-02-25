package hh.jug.b_structure_and_refac.sceneb;

public record Person(int age) {
    public static Person ofAge(int age) {
        return new Person(age);
    }

    public FunTimeResult haveFunTime(Venue venue) {
        return venue instanceof AdultsOnlyBar && age < 18 ?
                FunTimeResult.UNHAPPY_BECAUSE_NO_ENTRY :
                FunTimeResult.MUCH_FUN;
    }
}
