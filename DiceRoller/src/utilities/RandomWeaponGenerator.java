package utilities;

import java.util.ArrayList;
import dices.Dice;
import weapons.*;

/**
 * @author edoardodoglioni This class generate a Random Weapon
 */
public class RandomWeaponGenerator {

	private static final Integer WEAPONS = 5;
	private static final Integer ARRAY_START = 1;

	private final WeaponInterface sword;
	private final WeaponInterface bow;
	private final WeaponInterface dagger;
	private final WeaponInterface longsword;
	private final WeaponInterface hammer;
	private final Dice random = new Dice(WEAPONS);
	private final ArrayList<WeaponInterface> weaponArray;

	public RandomWeaponGenerator() {

		this.sword = new Sword();
		this.bow = new Bow();
		this.dagger = new Dagger();
		this.longsword = new Longsword();
		this.hammer = new Hammer();
		this.weaponArray = new ArrayList<WeaponInterface>();

	}

	/*
	 * This method add all kind of weapons to an array and choose one of them
	 * randomly
	 * 
	 * @return an Object who implements WeaponInterface
	 */

	public WeaponInterface generateRandomWeapon() {

		weaponArray.add(this.sword);
		weaponArray.add(this.bow);
		weaponArray.add(this.dagger);
		weaponArray.add(this.longsword);
		weaponArray.add(this.hammer);

		return this.weaponArray.get(random.roll() - ARRAY_START);
	}

}
