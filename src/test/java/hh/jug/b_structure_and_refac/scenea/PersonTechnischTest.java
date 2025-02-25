package hh.jug.b_structure_and_refac.scenea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTechnischTest {
	@Test
	void personHasFunWhenMayEnterVenue() {
		Person person = new Person(1);
		Venue venueWithEntry = new Venue() {
			@Override public boolean canEnter(Person p) {
				return true;
			}
		};

		FunTimeResult funResult = person.tryHaveFun(venueWithEntry);

		assertEquals(FunTimeResult.MUCH_FUN, funResult);
	}

	@Test
	void personHasNoFunWhenMayNotEnterVenue() {
		Person person = new Person(1);
		Venue venueWithEntry = new Venue() {
			@Override public boolean canEnter(Person p) {
				return false;
			}
		};

		FunTimeResult funResult = person.tryHaveFun(venueWithEntry);

		assertEquals(FunTimeResult.UNHAPPY_BECAUSE_NO_ENTRY, funResult);
	}
}
