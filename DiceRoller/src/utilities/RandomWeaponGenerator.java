package utilities;

import java.util.ArrayList;
import dices.Dice;
import weapons.*;

public class RandomWeaponGenerator {

	private static final Integer WEAPONS = 5;
	private static final Integer ARRAY_START = 1;

	private final WeaponInterface sword;
	private final WeaponInterface bow;
	private final WeaponInterface dagger;
	private final WeaponInterface broadsword;
	private final WeaponInterface hammer;
	private final Dice random = new Dice(WEAPONS);
	private final ArrayList<WeaponInterface> weaponArray;

	public RandomWeaponGenerator() {

		this.sword = new Sword();
		this.bow = new Bow();
		this.dagger = new Dagger();
		this.broadsword = new Broadsword();
		this.hammer = new Hammer();
		this.weaponArray = new ArrayList<WeaponInterface>();
		

	}

	public WeaponInterface generateRandomWeapon() {

		/*
		 * Aggiungo le 4 armi all'array
		 * Esiste un modo migliore?
		 */
		
		weaponArray.add(this.sword);
		weaponArray.add(this.bow);
		weaponArray.add(this.dagger);
		weaponArray.add(this.broadsword);
		weaponArray.add(this.hammer);
		
		return this.weaponArray.get(random.roll() - ARRAY_START);
	}

}
