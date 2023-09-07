package weapons;

import utilities.RandomWeaponGenerator;

/**
 * @author edoardodoglioni This class is a Factory Design Pattern who generate a
 *         Weapon in a random way
 */

public class WeaponFactory implements WeaponFactoryInterface {

	public WeaponInterface createWeapon() {

		RandomWeaponGenerator weaponGen = new RandomWeaponGenerator();

		return weaponGen.generateRandomWeapon();
	}

}
