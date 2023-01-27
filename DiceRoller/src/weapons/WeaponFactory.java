package weapons;

import utilities.RandomWeaponGenerator;
/*
 * Beta
 */
public class WeaponFactory implements WeaponFactoryInterface{

	public WeaponInterface createWeapon() {
		
		RandomWeaponGenerator weaponGen = new RandomWeaponGenerator();
		
		return weaponGen.generateRandomWeapon();
	}

}
