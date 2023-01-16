package utilities;

import java.util.ArrayList;
import dices.Dice;
import weapons.*;

public class RandomWeaponGenerator {

	private static final Integer WEAPONS = 4;
	private static final Integer ARRAY_START = 1;

	private Sword sword;
	private Bow bow;
	private Dagger dagger;
	private Broadsword broadsword;
	private Dice random = new Dice(WEAPONS);
	private ArrayList<WeaponInterface> weaponArray;

	public RandomWeaponGenerator() {

		this.sword = new Sword();
		this.bow = new Bow();
		this.dagger = new Dagger();
		this.broadsword = new Broadsword();
		this.weaponArray = new ArrayList<WeaponInterface>();


	}

	public WeaponInterface generateRandomWeapon() {

		/*
		 * Aggiungo le 4 armi all'array
		 */
		weaponArray.add(this.sword);
		weaponArray.add(this.bow);
		weaponArray.add(this.dagger);
		weaponArray.add(this.broadsword);
		
		return this.weaponArray.get(random.roll() - ARRAY_START);
	}

}
