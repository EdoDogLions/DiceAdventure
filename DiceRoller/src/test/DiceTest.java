package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dices.Dice;

public class DiceTest {

	private Dice dice;

	@BeforeEach
	void setUp() {
		dice = new Dice(20);
	}

	@Test
	void testSetUp() {

		assertEquals(dice.getFaces(), 21);

	}

	@Test
	void testRoll() {

		for (int x = 0; x < 100000; x++) {
			assertTrue(dice.roll() <= 20 && dice.roll() > 0);
		}

	}

	@Test
	void testDisAdvantage() {

		for (int x = 0; x < 1000000; x++) {
			assertTrue(dice.rollWithAdvantage() <= 20 && dice.rollWithAdvantage() > 0);
			assertTrue(dice.rollWithDisvantage() <= 20 && dice.rollWithDisvantage() > 0);
		}
	}

}
