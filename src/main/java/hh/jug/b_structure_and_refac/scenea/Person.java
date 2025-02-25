package hh.jug.b_structure_and_refac.scenea;

public record Person(int age) {
	public FunTimeResult tryHaveFun(Venue venue) {
		if (venue.canEnter(this))
			return FunTimeResult.MUCH_FUN;
		return FunTimeResult.UNHAPPY_BECAUSE_NO_ENTRY;
	}
}
