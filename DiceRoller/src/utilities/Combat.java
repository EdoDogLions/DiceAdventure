package utilities;

import java.util.Optional;

import dices.Dice;
import playable.Mob;
import playable.MobFactory;
import playable.Player;

/**
 * @author edoardodoglioni The Combat class controls the entire flow of the
 *         application The constants indicate the starting points of the first
 *         Mob and the coefficients of subsequent difficulties
 *
 */
public class Combat implements CombatInterface{

	private static final Integer START_CA = 10;
	private static final Integer START_LIFE = 10;
	private static final Integer LAST_MOB = 10; // Quanti Mob fronteggiare
	private static final Double INCREASE_HP = 1.2;
	private static final Integer INCREASE_CA = 1;
	private static final Integer MAX_CA = 20;
	private static final Integer START_MOB = 0;

	/*
	 * In the fields we have a Player, a MobFactory, and the starting point of the
	 * Mob constuctor
	 */

	private final Player player;
	private final MobFactory mobFactory;
	private Optional<Mob> actualMob;
	private Integer mobFighted; // Counter of fighted mobs
	private Integer mobHp; // We use this field to make our mobs stronger every other round
	private Integer mobCa;
	private StringBuilder outputString; // This is our GUI output to have track of what's happening

	public Combat(Player player) {

		this.player = player;
		this.mobFactory = new MobFactory();
		this.actualMob = Optional.empty();
		this.mobHp = START_LIFE;
		this.mobCa = START_CA;
		this.mobFighted = START_MOB;
		this.outputString = new StringBuilder();
	}

	/*
	 * This method control the entire application, it checks if there are Mobs to
	 * fight and if our player is still alive
	 */

	@Override
	public void fight() {

		this.outputString = new StringBuilder();

		if (this.mobFighted < LAST_MOB && player.isAlive()) {

			this.outputString = new StringBuilder();

			/*
			 * I create a new Mob
			 */

			actualMob = Optional.ofNullable(mobFactory.createMob(this.mobHp, this.mobCa));
			System.out.println("\n*******INCONTRO NUMERO: " + mobFighted + "*******\n");

			/*
			 * I set now the situation of the fight, if someone is in advantage or
			 * disadvantage
			 */
			this.randomAdvDSV(actualMob.get());

			System.out.println(player);
			System.out.println(actualMob.get());

			while (actualMob.get().isAlive() && player.isAlive()) {

				/*
				 * The turn order is based on initiative
				 */

				if (player.getInitiative() > actualMob.get().getInitiative()) {

					/*
					 * The player start first, so he attacks
					 */

					System.out.println("\n" + player.getName().get().toUpperCase() + " Attacks");
					outputString.append(
							"\n\n" + player.getName().get().toUpperCase() + "\nAttacks with " + player.getFightStyle());

					player.getFightStyle().useWeapon(mobCa);
					outputString.append(" dealing " + player.getFightStyle().getLastDmgHit() + " damage");
					actualMob.get().setDamage(player.getFightStyle().getLastDmgHit());

					/*
					 * Now is Mob's turn, if he's alive
					 */
					if (actualMob.get().isAlive()) {

						System.out.println("\n" + actualMob.get().getName().get().toUpperCase() + " Attacks");
						outputString.append("\n\n" + actualMob.get().getName().get().toUpperCase() + "\nAttacks with "
								+ actualMob.get().getFightStyle());

						actualMob.get().getFightStyle().useWeapon(mobCa);
						outputString.append(" dealing " + actualMob.get().getFightStyle().getLastDmgHit() + " damage");
						player.setDamage(actualMob.get().getFightStyle().getLastDmgHit());

					} else {

						System.out.println("\n" + actualMob.get().getName().get() + " has been defeated");
						outputString.append("\n\n" + actualMob.get().getName().get() + " has been defeated");

					}

					/*
					 * If the Mob is alive and have more initiative, he's the first who attacks
					 */
				} else if (actualMob.get().getInitiative() >= player.getInitiative() && actualMob.get().isAlive()) {

					/*
					 * The mob start first, so he attacks
					 */
					System.out.println("\n" + actualMob.get().getName().get().toUpperCase() + " Attacks");
					outputString.append("\n\n" + actualMob.get().getName().get().toUpperCase() + "\nAttacks with "
							+ actualMob.get().getFightStyle());

					actualMob.get().getFightStyle().useWeapon(mobCa);
					outputString.append(" dealing " + actualMob.get().getFightStyle().getLastDmgHit() + " damage");
					player.setDamage(actualMob.get().getFightStyle().getLastDmgHit());

					if (player.isAlive()) {

						/*
						 * Now is player turn, if he's still alive
						 */

						System.out.println("\n" + player.getName().get().toUpperCase() + " Attacks");
						outputString.append("\n\n" + player.getName().get().toUpperCase() + "\nAttacks with "
								+ player.getFightStyle());

						player.getFightStyle().useWeapon(mobCa);
						outputString.append(" dealing " + player.getFightStyle().getLastDmgHit() + " damage");
						actualMob.get().setDamage(player.getFightStyle().getLastDmgHit());

						/*
						 * If the mob is dead, the round is done
						 */
						if (!actualMob.get().isAlive()) {

							System.out.println("\n" + actualMob.get().getName().get() + " has been defeated");
							outputString.append("\n\n" + actualMob.get().getName().get() + " has been defeated");
						}
					}
				}
			}

			/*
			 * Here we move to the next level
			 */
			this.mobFighted++;
			this.nextLevel();

			System.out.println("\n" + player);

			/*
			 * The round is finished, we check the situation of player and mobs, if there
			 * are more mob to fight the player restore some HP
			 */

			if (player.isAlive() && mobFighted < LAST_MOB) {
				System.out.println(player.getName().get() + " is restoring HP");
				outputString.append("\n\n" + player.getName().get() + " is restoring HP");
				this.restorePlayer();

				/*
				 * If there are no more mob to fight and the player is still alive, the game is
				 * won
				 */

			} else if (player.isAlive() && mobFighted == LAST_MOB) {

				System.out.println(player.getName().get() + " has defeated every enemy");
				outputString.append("\n\n" + player.getName().get() + " has defeated every enemy");

				/*
				 * If there are mobs still alive and the player is dead, the game is lost
				 */
			} else {
				System.out.println(player.getName().get() + " is dead fighting his " + this.mobFighted + "° "
						+ actualMob.get().getClass().getSimpleName() + " who survived with this stats \n"
						+ actualMob.get());
				outputString.append("\n\n" + player.getName().get() + " is dead fighting his " + this.mobFighted + "° "
						+ actualMob.get().getClass().getSimpleName());
			}

		}

	}

	/*
	 * This methods are getter
	 */
	public String getOutputString() {

		return this.outputString.toString();
	}

	public String getPlayerStats() {
		return player.toString();
	}

	public String getMobStats() {
		return actualMob.get().toString();
	}

	public Integer getMobFighted() {
		return this.mobFighted;
	}

	public Integer getLastMob() {
		Integer lastMob = LAST_MOB;
		return lastMob;
	}

	public Mob getActualMob() {
		if (actualMob.isEmpty()) {
			return new Mob(START_MOB, START_MOB);
		} else {
			return this.actualMob.get();
		}

	}

	/*
	 * This method are setters for Mob Health Points and Class Armor
	 */
	public void setMobPf() {

		this.mobHp = (int) (mobHp * INCREASE_HP);
	}

	public void setMobCa() {

		if (this.mobCa < MAX_CA) {
			this.mobCa = mobCa + INCREASE_CA;
		} else {
			this.mobCa = MAX_CA;
		}

	}

	/*
	 * This methods prepares the next Mob to fight
	 */
	private void nextLevel() {

		this.setMobPf();
		this.setMobCa();

	}

	/*
	 * This methods restore Health Points of the player and roll his Initiative
	 * again
	 */
	private void restorePlayer() {

		this.player.setInitiative();
		this.player.restoreHp();
	}

	/*
	 * This method calculates the fight advantage/disadvantage
	 *
	 * @param mob is the actual mob we want to calculate the advantage/disadvantage
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

}
