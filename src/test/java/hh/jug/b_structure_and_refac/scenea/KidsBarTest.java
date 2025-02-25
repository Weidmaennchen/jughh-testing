package hh.jug.b_structure_and_refac.scenea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KidsBarTest {
	@Test
	void adultMayNotEnterKidsBar() {
		Person adult = new Person(18);
		KidsBar kidsBar = new KidsBar();

		boolean mayEnter = kidsBar.canEnter(adult);

		assertFalse(mayEnter);
	}

	@Test
	void kidMayEnterKidsBar() {
		Person kid = new Person(17);
		KidsBar kidsBar = new KidsBar();

		boolean mayEnter = kidsBar.canEnter(kid);

		assertTrue(mayEnter);
	}
}
