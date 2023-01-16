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
		fs.useAWeapon(20);
		
		System.out.println(fs);
		System.out.println(pippo);
		System.out.println(pippo);
		System.out.println(pippo);
		System.out.println(pippo);
		pippo.getHealthPoints();
		pippo.restoreHp();
		System.out.println(pippo);
		pippo.getHealthPoints();
		
		
	}
		
		
}
