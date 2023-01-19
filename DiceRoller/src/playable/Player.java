package playable;

import dices.Dice;
import fightStyles.FightStyleFactory;
import fightStyles.FightStyleInterface;

public class Player implements PlayableInterface {

	private static final Double RESTORE = 0.5;
	private static final Integer DEATH = 0;
	private static final Integer BASE_CA = 10;

	private Dice d20 = new Dice(20);
	private Dice d8 = new Dice(8);

	private final FightStyleFactory fsFactory = new FightStyleFactory();

	private Integer maxHp; // Punti ferita massimi
	private Integer actualHp;
	private final Integer armorClass;
	private final FightStyleInterface fightStyle;
	private Integer initiative;
	private String playerName;

	public Player(Integer hp, String playerName) {
		this.maxHp = hp;
		this.actualHp = hp;
		this.armorClass = BASE_CA + d8.roll();
		this.fightStyle = fsFactory.createFightStyle();
		this.initiative = d20.roll();
		this.playerName = playerName;
	}

	/*
	 * Ripristino Hp attuali + x% degli Hp massimi determinato dalla variabile
	 * RESTORE
	 */
	public void restoreHp() {

		if ((int) this.actualHp + (RESTORE * this.maxHp) < this.maxHp) {
			this.actualHp = (int) (this.actualHp + (RESTORE * this.maxHp));
		} else {
			this.actualHp = this.maxHp;
		}
	}

	public boolean isAlive() {

		if (this.actualHp > DEATH) {
			return true;
		} else {

			return false;

		}
	}

	public Integer getHealthPoints() {
		return actualHp;
	}

	public void setHealthPoints(Integer healthPoints) {
		this.actualHp = healthPoints;
	}

	public void setDamage(Integer damage) {
		this.actualHp -= damage;
	}

	public Integer getArmorClass() {
		return armorClass;
	}

	public FightStyleInterface getFightStyle() {
		return fightStyle;
	}

	public Integer getInitiative() {
		return initiative;
	}

	public void setInitiative() {
		this.initiative = d20.roll();
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public String toString() {

		return this.getClass().getName().toUpperCase() + " STATS:" + "\nNAME: " + this.playerName
				+ "\nHP: " + this.actualHp + "\nCA: "
				+ this.armorClass + "\nFIGHT STYLE: " + this.fightStyle + "\nINITIATIVE: " + this.initiative + "\n";
	}

}