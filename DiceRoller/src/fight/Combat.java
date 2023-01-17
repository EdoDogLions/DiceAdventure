package fight;

import playable.*;

public class Combat {

	/*
	 * La classe Combat predispone l'intero svolgimento dell'applicazione
	 */

	/*
	 * Le due costanti indicano i punti di partenza del primo MOB
	 */

	private static final Integer START_CA = 10;
	private static final Integer START_LIFE = 10;
	private static final Integer LAST_MOB = 10; // Quanti Mob fronteggiare
	private static final Double INCREASE_HP = 1.3;
	private static final Integer INCREASE_CA = 1;

	/*
	 * Nei campi abbiamo il Giocatore, una Factory di Mob e tre campi per la prima
	 * inizializzazione del mob
	 */
	private final Player player;
	private final MobFactory mobFactory;

	/*
	 * Contatore dei Mob affrontati
	 */
	private Integer mobFighted;

	/*
	 * Statistiche del mob
	 */
	private Integer mobPf;
	private Integer mobCa;
	private FightStyleInterface mobFs;

	public Combat(Player player) {

		this.player = player;
		this.mobFactory = new MobFactory();
		this.mobFs = new FightWithAWeapon();
		this.mobPf = START_LIFE;
		this.mobCa = START_CA;
		this.mobFighted = 1;

	}

	public void fight() {

		while (this.mobFighted < LAST_MOB && player.isAlive()) {

			/*
			 * Creo un Mob
			 */

			Mob actualMob = mobFactory.createMob(this.mobPf, this.mobCa, this.mobFs);
			System.out.println("\n*******INCONTRO NUMERO: " + mobFighted + "*******\n");
			/*
			 * Stampo Player e Mob
			 */
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

					System.out.println("\n" + player.getClass().getName() + " Attacks");
					player.getFightStyle().useAWeapon(mobCa);
					actualMob.setDamage(player.getFightStyle().getLastDmgHit());

					/*
					 * Successivamente colpisce il Mob se è vivo
					 */
					if (actualMob.isAlive()) {

						System.out.println("\n" + actualMob.getClass().getName() + " Attacks");
						actualMob.getFightStyle().useAWeapon(mobCa);
						player.setDamage(actualMob.getFightStyle().getLastDmgHit());

					} else {
						
						System.out.println(actualMob.getClass().getName() + " has been defeated");

					} 

					/*
					 * Se è vivo e ha iniziativa colpisce per primo
					 */
				} else if (actualMob.isAlive()) {

					/*
					 * Inizia il Mob, colpisce per primo
					 */
					System.out.println("\n" + actualMob.getClass().getName() + " Attacks");
					actualMob.getFightStyle().useAWeapon(mobCa);
					player.setDamage(actualMob.getFightStyle().getLastDmgHit());

					if (player.isAlive()) {

						/*
						 * Poi colpisce il Player
						 */

						System.out.println("\n" + player.getClass().getName() + " Attacks");
						player.getFightStyle().useAWeapon(mobCa);
						actualMob.setDamage(player.getFightStyle().getLastDmgHit());

					}
					
				} else {

					System.out.println(actualMob.getClass().getName() + " has been defeated");
				}

			}

			this.mobFighted++;
			this.nextLevel();
			System.out.println("\n" +player);
			if (player.isAlive()) {
				System.out.println(player.getClass().getName() + " is restoring HP");
				this.restorePlayer();

			} else {
				System.out.println(player.getClass().getName() + " is dead, YOU LOSE");
			}

		}

	}

	public void setMobPf() {
		this.mobPf = (int) (mobPf * INCREASE_HP);
	}

	public void setMobCa() {
		this.mobCa = mobCa + INCREASE_CA;
	}

	private void nextLevel() {

		this.setMobPf();
		this.setMobCa();

	}

	private void restorePlayer() {

		this.player.setInitiative();
		this.player.restoreHp();
	}
}
