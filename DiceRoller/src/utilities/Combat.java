package utilities;

import java.util.Optional;

import dices.Dice;
import playable.*;

/*
 * Beta
 */
public class Combat {

	/*
	 * La classe Combat predispone l'intero svolgimento dell'applicazione
	 */

	/*
	 * Le costanti indicano i punti di partenza del primo MOB e i coefficenti di
	 * difficoltà successivi
	 */

	private static final Integer START_CA = 10;
	private static final Integer START_LIFE = 10;
	private static final Integer LAST_MOB = 10; // Quanti Mob fronteggiare
	private static final Double INCREASE_HP = 1.2;
	private static final Integer INCREASE_CA = 1;
	private static final Integer MAX_CA = 20;
	private static final Integer START_MOB = 0;

	/*
	 * Nei campi abbiamo il Giocatore, una Factory di Mob e tre campi per la prima
	 * inizializzazione del mob
	 */
	
	private final Player player;
	private final MobFactory mobFactory;
	private Optional <Mob> actualMob;
	private Integer mobFighted; // Contatore dei Mob affrontati
	private Integer mobPf; // Statistiche del mob incrementali
	private Integer mobCa;
	private StringBuilder outputString;

	public Combat(Player player) {

		this.player = player;
		this.mobFactory = new MobFactory();
		this.actualMob = Optional.empty();
		this.mobPf = START_LIFE;
		this.mobCa = START_CA;
		this.mobFighted = START_MOB;
		this.outputString = new StringBuilder();
	}

	/*
	 * 
	 */

	public void fight() {

		this.outputString = new StringBuilder();
		
		if (this.mobFighted < LAST_MOB && player.isAlive()) {
			
			this.outputString = new StringBuilder();
			
			/*
			 * Creo un Mob
			 */

			actualMob = Optional.ofNullable(mobFactory.createMob(this.mobPf, this.mobCa));
			System.out.println("\n*******INCONTRO NUMERO: " + mobFighted + "*******\n");

			/*
			 * Stampo Player e Mob
			 */
			this.randomAdvDSV(actualMob.get());
			System.out.println(player);
			System.out.println(actualMob.get());

			while (actualMob.get().isAlive() && player.isAlive()) {

				/*
				 * L'iniziativa determina chi attacca per primo
				 */

				if (player.getInitiative() > actualMob.get().getInitiative()) {

					/*
					 * Inizia il player, attacca per primo
					 */

					System.out.println("\n" + player.getName().get().toUpperCase() + " Attacks");
					outputString.append("\n" + player.getName().get().toUpperCase() + "\nAttacks with " + player.getFightStyle());
					
					player.getFightStyle().useWeapon(mobCa);
					outputString.append(" dealing " + player.getFightStyle().getLastDmgHit() +" damage");
					actualMob.get().setDamage(player.getFightStyle().getLastDmgHit());

					/*
					 * Successivamente colpisce il Mob se è vivo
					 */
					if (actualMob.get().isAlive()) {

						System.out.println("\n" + actualMob.get().getName().get().toUpperCase() + " Attacks");
						outputString.append("\n" + actualMob.get().getName().get().toUpperCase() + "\nAttacks with" + actualMob.get().getFightStyle());
						
						actualMob.get().getFightStyle().useWeapon(mobCa);
						outputString.append(" dealing " + actualMob.get().getFightStyle().getLastDmgHit() +" damage");
						player.setDamage(actualMob.get().getFightStyle().getLastDmgHit());

					} else {

						System.out.println("\n" + actualMob.get().getName().get() + " has been defeated");
						outputString.append("\n\n" + actualMob.get().getName().get() + " has been defeated");


					}

					/*
					 * Se il mob è vivo e ha iniziativa colpisce per primo
					 */
				} else if (actualMob.get().getInitiative() >= player.getInitiative() && actualMob.get().isAlive()) {

					/*
					 * Inizia il Mob, colpisce per primo
					 */
					System.out.println("\n" + actualMob.get().getName().get().toUpperCase() + " Attacks");
					outputString.append("\n" + actualMob.get().getName().get().toUpperCase() + "\nAttacks with " + actualMob.get().getFightStyle());

					actualMob.get().getFightStyle().useWeapon(mobCa);
					outputString.append(" dealing " + actualMob.get().getFightStyle().getLastDmgHit() +" damage");
					player.setDamage(actualMob.get().getFightStyle().getLastDmgHit());

					if (player.isAlive()) {

						/*
						 * Poi colpisce il Player
						 */

						System.out.println("\n" + player.getName().get().toUpperCase() + " Attacks");
						outputString.append("\n" + player.getName().get().toUpperCase() + "\nAttacks with " + player.getFightStyle());

						player.getFightStyle().useWeapon(mobCa);
						outputString.append(" dealing " + player.getFightStyle().getLastDmgHit()+ " damage");
						actualMob.get().setDamage(player.getFightStyle().getLastDmgHit());

						if (!actualMob.get().isAlive()) {

							System.out.println("\n" + actualMob.get().getName().get() + " has been defeated");
							outputString.append("\n\n" + actualMob.get().getName().get() + " has been defeated");
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
				System.out.println(player.getName().get() + " is restoring HP");
				outputString.append("\n\n" + player.getName().get() + " is restoring HP");
				this.restorePlayer();

			} else if (player.isAlive() && mobFighted == LAST_MOB) {

				System.out.println(player.getName().get() + " has defeated every enemy");
				outputString.append("\n\n" +player.getName().get() + " has defeated every enemy");

			} else {
				System.out.println(player.getName().get() + " is dead fighting his " + this.mobFighted + "° "
						+ actualMob.get().getClass().getSimpleName() + " who survived with this stats \n" + actualMob.get());
				outputString.append("\n\n" + player.getName().get() + " is dead fighting his " + this.mobFighted + "° "
						+ actualMob.get().getClass().getSimpleName());
			}

		}

	}

	public String getOutputString() {
		
		return this.outputString.toString();
	}

	public String getPlayerStats() {
		return player.toString();
	}

	public String getMobStats() {
		return actualMob.get().toString();
	}

	public void setMobPf() {

		this.mobPf = (int) (mobPf * INCREASE_HP);
	}

	public void setMobCa() {

		if (this.mobCa < MAX_CA) {
			this.mobCa = mobCa + INCREASE_CA;
		} else {
			this.mobCa = MAX_CA;
		}

	}

	private void nextLevel() {

		this.setMobPf();
		this.setMobCa();


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
	
	public Integer getMobFighted() {
		return this.mobFighted;
	}
	
	public Integer getLastMob() {
		Integer lastMob = LAST_MOB;
		return lastMob;
	}
	
	public Mob getActualMob() {
		if(actualMob.isEmpty()) {
			return new Mob(START_MOB, START_MOB);
		}else {
			return this.actualMob.get();
		}
		
	}

}
