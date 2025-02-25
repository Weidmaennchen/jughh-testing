package hh.jug.b_structure_and_refac.scenea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdultsOnlyBarTest {
	@Test
	void adultMayEnterAdultsOnlyBar() {
		Person adult = new Person(18);
		AdultsOnlyBar kidsBar = new AdultsOnlyBar();

		boolean mayEnter = kidsBar.canEnter(adult);

		assertTrue(mayEnter);
	}

	@Test
	void kidMayNotEnterAdultsOnlyBar() {
		Person kid = new Person(17);
		AdultsOnlyBar kidsBar = new AdultsOnlyBar();

		boolean mayEnter = kidsBar.canEnter(kid);

		assertFalse(mayEnter);
	}
}
