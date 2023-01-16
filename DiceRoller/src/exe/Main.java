package exe;

import fight.*;
import playable.*;
import utilities.RandomWeaponGenerator;
import weapons.*;

public class Main {

	public static void main (String[] args) {
		
		FightWithAWeapon fas = new FightWithAWeapon();
		fas.useAWeapon(12);
		System.out.println(fas.getCustomWeapon());
	}
		
		
}
