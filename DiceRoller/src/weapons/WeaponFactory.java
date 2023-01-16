package weapons;

import utilities.RandomWeaponGenerator;

public class WeaponFactory implements WeaponFactoryInterface{

	@Override
	public WeaponInterface createWeapon() {
		
		RandomWeaponGenerator weaponGen = new RandomWeaponGenerator();
		
		return weaponGen.generateRandomWeapon();
	}

}
