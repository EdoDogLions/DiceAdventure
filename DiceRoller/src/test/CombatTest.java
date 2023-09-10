package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import playable.Mob;
import playable.Player;
import utilities.Combat;

public class CombatTest {

	private Combat combat;
	private Player player;
	private String s;
	private int combatCounter = 0;

	@BeforeEach
	void setUp() {

		player = new Player(100, "Paolo");
		combat = new Combat(player);
	}

	@Test
	void testSetUp() {

		assertTrue(combat.getMobFighted() <= 0);
		assertTrue(combat.getActualMob() instanceof Mob);
		assertTrue(combat.getOutputString() instanceof String);

	}


	@Test
	void testFight() {

		while (combat.getMobFighted() < combat.getLastMob() && player.isAlive()) {
			combat.fight();
			combatCounter++;
			assertTrue(player.getHealthPoints() <= player.getMaxHP());
			assertTrue(combat.getMobFighted().equals(combatCounter));

		}

	}

	@Test
	void testTestFight() {

		for (int x = 0; x < 1000000; x++) {
			testFight();

		}
	}
	
	@Test
	void testOutputString() {

		s = combat.getOutputString();
		assertEquals(combat.getOutputString(), s);
		combat.fight();
		assertFalse(combat.getOutputString().equals(s));
	}

}
