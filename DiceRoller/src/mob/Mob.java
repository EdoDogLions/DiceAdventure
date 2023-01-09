package mob;

import fight.FightStyle;

public class Mob implements Playable {

	private static final Integer DEATH = 0;
	
	
	private Integer healthPoints;
	private Integer armorClass;
	private FightStyle fightStyle;
	private Integer initiative;

	public Mob(Integer hp, Integer ac, FightStyle fs, Integer initiative) {	//Iniziativa va tirata
		this.healthPoints = hp;
		this.armorClass = ac;
		this.fightStyle = fs;
		this.initiative = initiative;
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

	public boolean isAlive() {

		if (this.healthPoints > DEATH) {

			return true;

		} else {
			return false;
		}
	}

}
