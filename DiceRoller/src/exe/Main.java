package exe;

import fightStyle.*;
import weapons.*;

public class Main {

	public static void main (String[] args) {
		
		WeaponsFactory swordFactory = new GenericWeaponFactory();
		Sword sword = (Sword) swordFactory.createWeapon();
		FightWithAWeapon fs = new FightWithAWeapon(sword);
		
		fs.useAWeapon(10);
		System.out.println(fs.toString());
		
	}
		
		
}
