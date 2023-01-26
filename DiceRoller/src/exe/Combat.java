package exe;

import dices.Dice;
import playable.*;
import utilities.Luck;

public class Combat {

	/*
	 * La classe Combat predispone l'intero svolgimento dell'applicazione
	 */

	/*
	 * Le costanti indicano i punti di partenza del primo MOB e i coefficenti di
	 * difficoltà successivi Da modificare per renderele sceglibili al giocatore
	 */

	private static final Integer START_CA = 10;
	private static final Integer START_LIFE = 10;
	private static final Integer LAST_MOB = 10; // Quanti Mob fronteggiare
	private static final Double INCREASE_HP = 1.2;
	private static final Integer INCREASE_CA = 1;
	private static final Integer MAX_CA = 20;

	/*
	 * Nei campi abbiamo il Giocatore, una Factory di Mob e tre campi per la prima
	 * inizializzazione del mob
	 */
	private final Player player;
	private final MobFactory mobFactory;
	private Integer mobFighted; //Contatore dei Mob affrontati
	private Integer mobPf; //Statistiche del mob incrementali
	private Integer mobCa;

	public Combat(Player player) {

		this.player = player;
		this.mobFactory = new MobFactory();
		this.mobPf = START_LIFE;
		this.mobCa = START_CA;
		this.mobFighted = 1;

	}

	public void fight() {
		
		while (this.mobFighted < LAST_MOB && player.isAlive()) {
			/*
			 * Creo un Mob
			 */

			Mob actualMob = mobFactory.createMob(this.mobPf, this.mobCa);
			System.out.println("\n*******INCONTRO NUMERO: " + mobFighted + "*******\n");
			/*
			 * Stampo Player e Mob
			 */
			this.randomAdvDSV(actualMob);
			System.out.println(player);
			System.out.println(actualMob);

			while (actualMob.isAlive() && player.isAlive()) {

				/*
				 * L'iniziativa determina chi attacca per primo
				 */

				if (player.getInitiative() > actualMob.getInitiative()) {

					/*
					 * Inizia il player, colpisce per primo
					 */

					System.out.println("\n" + player.getName() + " Attacks");
					player.getFightStyle().useWeapon(mobCa);
					actualMob.setDamage(player.getFightStyle().getLastDmgHit());

					/*
					 * Successivamente colpisce il Mob se è vivo
					 */
					if (actualMob.isAlive()) {

						System.out.println("\n" + actualMob.getName() + " Attacks");
						actualMob.getFightStyle().useWeapon(mobCa);
						player.setDamage(actualMob.getFightStyle().getLastDmgHit());

					} else {

						System.out.println("\n" + actualMob.getName() + " has been defeated");

					}

					/*
					 * Se il mob è vivo e ha iniziativa colpisce per primo
					 */
				} else if (actualMob.getInitiative() >= player.getInitiative() && actualMob.isAlive()) {

					/*
					 * Inizia il Mob, colpisce per primo
					 */
					System.out.println("\n" + actualMob.getName() + " Attacks");
					actualMob.getFightStyle().useWeapon(mobCa);
					player.setDamage(actualMob.getFightStyle().getLastDmgHit());

					if (player.isAlive()) {

						/*
						 * Poi colpisce il Player
						 */

						System.out.println("\n" + player.getName() + " Attacks");
						player.getFightStyle().useWeapon(mobCa);
						actualMob.setDamage(player.getFightStyle().getLastDmgHit());

						if (!actualMob.isAlive()) {

							System.out.println("\n" + actualMob.getName() + " has been defeated");
						}
					}
				}

			}

			this.mobFighted++;
			this.nextLevel();

			System.out.println("\n" + player);
			/*
			 * Resoconto di fine Combat
			 */

			if (player.isAlive() && mobFighted < LAST_MOB) {
				System.out.println(player.getName() + " is restoring HP");
				this.restorePlayer();

			} else if (player.isAlive() && mobFighted == LAST_MOB) {

				System.out.println(player.getName() + " has defeated every enemy");
				//System.ouScanner s = new Scanner(System.in);t.println(actualMob); era per controllare se effettivamente uccidesse l'ultimo

			} else {
				System.out.println(player.getName() + " is dead fighting his " + this.mobFighted + "° "
						+ actualMob.getClass().getName() + "\n" + actualMob);
			}

		}

	}

	public void setMobPf() {
		this.mobPf = (int) (mobPf * INCREASE_HP);
	}

	public void setMobCa() {
		
		if (this.mobCa < MAX_CA){
			this.mobCa = mobCa + INCREASE_CA;
		}else {
			this.mobCa = MAX_CA;
		}
		
	}

	private void nextLevel() {

		this.setMobPf();
		this.setMobCa();
		//this.pauseOutput();

	}

	private void restorePlayer() {

		this.player.setInitiative();
		this.player.restoreHp();
	}

	/*
	 * Luck è un enumeratore che gestisce i 6 casi di fortuna/sfrotuna
	 */

	private void randomAdvDSV(Mob mob) {

		Dice chooseRandom = new Dice(Luck.values().length);
		Integer randomValue = chooseRandom.roll();

		if (randomValue == Luck.GOOD_FOR_PLAYER.getDiceValue()) {
			this.player.getFightStyle().setAdvantage();
			mob.getFightStyle().resetAdDisvantage();

		} else if (randomValue == Luck.BAD_FOR_PLAYER.getDiceValue()) {
			this.player.getFightStyle().setDisadvantage();
			mob.getFightStyle().resetAdDisvantage();

		} else if (randomValue == Luck.GOOD_FOR_MOB.getDiceValue()) {
			mob.getFightStyle().setAdvantage();
			this.player.getFightStyle().resetAdDisvantage();

		} else if (randomValue == Luck.BAD_FOR_MOB.getDiceValue()) {
			mob.getFightStyle().setDisadvantage();
			this.player.getFightStyle().resetAdDisvantage();

		} else if (randomValue == Luck.GOOD_FOR_BOTH.getDiceValue()) {
			this.player.getFightStyle().setAdvantage();
			mob.getFightStyle().setAdvantage();

		} else if (randomValue == Luck.NOTHING.getDiceValue()) {
			this.player.getFightStyle().resetAdDisvantage();
			mob.getFightStyle().resetAdDisvantage();
		}

	}
//	@SuppressWarnings("resource")
//	private void pauseOutput() {
//		
//		System.out.println("Press Enter Key To Continue...");
//		new java.util.Scanner(System.in).nextLine();
//	}
}
