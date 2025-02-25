package hh.jug.b_structure_and_refac.sceneb;

public record Person(int age) {
	public static Person ofAge(int age) {
		return new Person(age);
	}

	public FunTimeResult tryHaveFun(Venue venue) {
		if (venue instanceof AdultsOnlyBar && age < 18)
			return FunTimeResult.UNHAPPY_BECAUSE_NO_ENTRY;
		if (venue instanceof KidsBar && age >= 18)
			return FunTimeResult.UNHAPPY_BECAUSE_NO_ENTRY;

		return FunTimeResult.MUCH_FUN;
	}
}
