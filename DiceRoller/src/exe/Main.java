package exe;

import fight.*;
import playable.*;
import utilities.RandomWeaponGenerator;
import weapons.*;

public class Main {

	public static void main (String[] args) {
		
		
		WeaponFactoryInterface wf = new WeaponFactory();
		WeaponInterface arma2 = wf.createWeapon();
		System.out.println(arma2);

	}
		
		
}
