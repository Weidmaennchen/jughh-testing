package hh.jug.b_structure_and_refac.scenea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OpenBarTest {
	@Test
	void adultMayEnterOpenBar() {
		Person adult = new Person(18);
		OpenBar kidsBar = new OpenBar();

		boolean mayEnter = kidsBar.canEnter(adult);

		assertTrue(mayEnter);
	}

	@Test
	void kidMayEnterOpenBar() {
		Person kid = new Person(17);
		OpenBar kidsBar = new OpenBar();

		boolean mayEnter = kidsBar.canEnter(kid);

		assertTrue(mayEnter);
	}
}
