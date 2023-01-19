package exe;

import playable.*;

public class Main {

	/*
	 * Appunti per l'applicazione:
	 * 
	 * 3 livelli di difficoltà EASY = 1.5, MEDIUM = 2, HARD = 2.5 //Moltiplicativo HP non intacco la CA
	 * 
	 * Numero di nemici affrontabili modificabile in home
	 * 
	 * Generatore di descrizione degli eventi per rendere la narrazione più interessante, a seguito di ogni scontro
	 * (Es. mancato -> Il movimento bla bla ti impedisce ... ; colpito -> La tua abilità...; crit-> INCREDIBILE
	 * 
	 * Come sfruttare al meglio VANTAGGI e SVANTAGGI nel corso del fight
	 * (Creare situazioni casuali per adv/dsv) DONE
	 */

	public static void main (String[] args) {
		
		/*
		 * GAME INTRODUCTION
		 */
		System.out.println("Welcome to DiceAdventure");
		
		Player player = new Player(100, "Pippo Baudo");
		Combat combat = new Combat(player);
		
		combat.fight();
		
	}
		
		
}
