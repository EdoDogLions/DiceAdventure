package exe;

import fight.*;
import playable.*;
import utilities.RandomWeaponGenerator;
import weapons.*;

public class Main {


	public static void main (String[] args) {
		
		/*
		 * GAME INTRODUCTION
		 */
		System.out.println("Welcome to DiceAdventure");
		System.out.println("Welcome to DiceAdventure");
		System.out.println("Welcome to DiceAdventure");
		System.out.println("Welcome to DiceAdventure");
		
		FightWithAWeapon fas = new FightWithAWeapon();
		Player player = new Player(100, 15, fas);
		Combat combat = new Combat(player);
		
		combat.fight();
		
	}
		
		
}
