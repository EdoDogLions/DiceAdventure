package playable;

import dices.Dice;
import fightStyles.FightStyleFactory;
import fightStyles.FightStyleInterface;
import utilities.MobNameGenerator;

public class Mob implements PlayableInterface {

	private static final Integer DEATH = 0;

	private Dice d20 = new Dice(20);
	private final FightStyleFactory fsFactory= new FightStyleFactory();
	private MobNameGenerator mobNameGen = new MobNameGenerator();

	private Integer healthPoints;
	private Integer armorClass;
	private FightStyleInterface fightStyle;
	private Integer initiative;
	private String mobName;

	public Mob(Integer hp, Integer ac) { // Iniziativa va tirata
		this.healthPoints = hp;
		this.armorClass = ac;
		this.fightStyle = fsFactory.createFightStyle();
		this.initiative = d20.roll();
		this.mobName = mobNameGen.getName();
	}

	public Integer getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(Integer healthPoints) {
		this.healthPoints = healthPoints;
	}

	public void setDamage(Integer damage) {
		this.healthPoints -= damage;
	}

	public void setArmorClass(Integer armorClass) {
		this.armorClass = armorClass;
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

	public String getMobName() {
		return this.mobName;
	}
	public boolean isAlive() {

		if (this.healthPoints > DEATH) {

			return true;

		} else {
			return false;
		}
	}

	public String toString() {

		return this.getClass().getName().toUpperCase() + " STATS:" + "\nTYPE: " + this.mobName
				+ "\nHP: " + this.healthPoints + "\nCA: "
				+ this.armorClass + "\nFIGHT STYLE: " + this.fightStyle + "\nINITIATIVE: " + this.initiative + "\n";
	}

}
