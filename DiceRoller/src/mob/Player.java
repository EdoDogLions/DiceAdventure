package mob;

import dices.Dice;
import fight.FightStyle;

public class Player implements Playable {

	private static final Double HALF = 0.5;
	private static final Integer DEATH = 0;
	
	private Dice d20 = new Dice(20);


	private Integer healthPoints;
	private final Integer armorClass;
	private final FightStyle fightStyle;
	private final Integer initiative;

	public Player(Integer hp, Integer ac, FightStyle fs) {
		this.healthPoints = hp;
		this.armorClass = ac;
		this.fightStyle = fs;
		this.initiative = d20.roll();
	}

	public void restoreHp() {

		this.healthPoints = (int) (this.healthPoints + (HALF * this.healthPoints));
	}

	public boolean isAlive() {

		if (this.healthPoints > DEATH) {
			return true;
		} else {

			return false;

		}
	}

	public Integer getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(Integer healthPoints) {
		this.healthPoints = healthPoints;
	}

	public Integer getArmorClass() {
		return armorClass;
	}

	public FightStyle getFightStyle() {
		return fightStyle;
	}

	public Integer getInitiative() {
		return initiative;
	}
	
	public String toString() {
		
		return this.getClass().getName() + " has:\n" +
				"\nHP: " + this.healthPoints +
				"\nCA: " + this.armorClass +
				"\nFIGHT STYLE: " + this.fightStyle +
				"\nINITIATIVE: " + this.initiative;
	}
	
}