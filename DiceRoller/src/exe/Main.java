package exe;

import fight.*;
import playable.*;
import weapons.*;

public class Main {

	public static void main (String[] args) {
		
		WeaponInterface spada = new GenericWeapon(8);
		FightStyleInterface fs = new FightWithAWeapon((GenericWeapon) spada);
		Player pippo = new Player(100, 20, fs);
		System.out.println(pippo);
		//fs.useAWeapon(1);
		
		System.out.println(fs);
		
		
	}
		
		
}
