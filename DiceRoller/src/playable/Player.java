package playable;

import dices.Dice;
import fight.FightStyleInterface;

public class Player implements PlayableInterface {

	private static final Double RESTORE = 0.5;
	private static final Integer DEATH = 0;

	private Dice d20 = new Dice(20);

	private Integer maxHp; // Punti ferita massimi
	private Integer actualHp;
	private final Integer armorClass;
	private final FightStyleInterface fightStyle;
	private Integer initiative;

	public Player(Integer hp, Integer ac, FightStyleInterface fs) {
		this.maxHp = hp;
		this.actualHp = hp;
		this.armorClass = ac;
		this.fightStyle = fs;
		this.initiative = d20.roll();
	}

	/*
	 * Ripristino Hp attuali + x% degli Hp massimi determinato dalla variabile RESTORE
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
	public String toString() {

		return this.getClass().getName().toUpperCase() + " STATS:" + "\nHP: " + this.actualHp + "\nCA: " + this.armorClass
				+ "\nFIGHT STYLE: " + this.fightStyle + "\nINITIATIVE: " + this.initiative + "\n";
	}

}