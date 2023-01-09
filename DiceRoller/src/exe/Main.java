package exe;

import fight.*;
import weapons.*;
import mob.*;

public class Main {

	public static void main (String[] args) {
		
		Weapons spada = new GenericWeapon(8);
		FightStyle fs = new FightWithAWeapon((GenericWeapon) spada);
		Player pippo = new Player(100, 20, fs);
		System.out.println(pippo);
		//fs.useAWeapon(1);
		
		System.out.println(fs);
		
		
	}
		
		
}
