package hh.jug.b_structure_and_refac.sceneb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BargangFachlichTest {
	@Test
	@DisplayName("Minor + Adult Bar = no fun")
	void minorsHaveNoFunInAdultsOnlyBar() {
		Person child = new Person(17);
		Venue bar = new AdultsOnlyBar();

		FunTimeResult result = child.tryHaveFun(bar);
		assertEquals(FunTimeResult.UNHAPPY_BECAUSE_NO_ENTRY, result);
	}

	@Test
	@DisplayName("Minor + Kids Bar = fun")
	void minorsHaveFunInKidsBar() {
		Person child = new Person(17);
		Venue bar = new KidsBar();

		FunTimeResult result = child.tryHaveFun(bar);
		assertEquals(FunTimeResult.MUCH_FUN, result);
	}

	@Test
	@DisplayName("Minor + Open Bar = fun")
	void minorsHaveFunInOpenBar() {
		Person child = new Person(17);
		Venue bar = new OpenBar();

		FunTimeResult result = child.tryHaveFun(bar);
		assertEquals(FunTimeResult.MUCH_FUN, result);
	}

	@Test
	@DisplayName("Adult + Adult Bar = fun")
	void adultsHaveFunInAdultsOnlyBar() {
		Person child = new Person(18);
		Venue bar = new AdultsOnlyBar();

		FunTimeResult result = child.tryHaveFun(bar);
		assertEquals(FunTimeResult.MUCH_FUN, result);
	}

	@Test
	@DisplayName("Adult + Kids Bar = no fun")
	void adultsHaveNoFunInKidsBar() {
		Person child = new Person(18);
		Venue bar = new KidsBar();

		FunTimeResult result = child.tryHaveFun(bar);
		assertEquals(FunTimeResult.UNHAPPY_BECAUSE_NO_ENTRY, result);
	}

	@Test
	@DisplayName("Adult + OpenBar = fun")
	void adultsHaveFunInOpenBar() {
		Person child = new Person(18);
		Venue bar = new OpenBar();

		FunTimeResult result = child.tryHaveFun(bar);
		assertEquals(FunTimeResult.MUCH_FUN, result);
	}
}
