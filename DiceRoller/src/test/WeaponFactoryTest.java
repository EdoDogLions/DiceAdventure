package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import weapons.WeaponFactory;
import weapons.WeaponInterface;

public class WeaponFactoryTest {

	private WeaponFactory wf;

	@BeforeEach
	void setUp() {
		wf = new WeaponFactory();
	}

	@Test
	void testSetUp() {

		assertTrue(wf.createWeapon() instanceof WeaponInterface);

	}

	@Test
	void testWeaponDice() {
		for (int x = 0; x < 1000; x++) {
			WeaponInterface weapon = wf.createWeapon();
			for (int y = 0; y < 1000; y++) {
				assertTrue(weapon.rollHit() <= 20);
				if (weapon.getWeaponName().equals("Bow")) {
					assertTrue(weapon.rollDmg() <= 8);
				} else if (weapon.getWeaponName().equals("Dagger")) {
					assertTrue(weapon.rollDmg() <= 4);
				} else if (weapon.getWeaponName().equals("Hammer")) {
					assertTrue(weapon.rollDmg() <= 6);
				} else if (weapon.getWeaponName().equals("Longsword")) {
					assertTrue(weapon.rollDmg() <= 10);
				} else if (weapon.getWeaponName().equals("Sword")) {
					assertTrue(weapon.rollDmg() <= 6);
				}

			}
		}
	}
}
