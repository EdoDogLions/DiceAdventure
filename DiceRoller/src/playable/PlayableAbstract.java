package playable;

import dices.Dice;
import fightStyles.FightStyleInterface;
import utilities.*;

public class PlayableAbstract implements PlayableInterface{

	private static final Integer DEATH = 0;
	private static final Integer BASE_CA = 10;
	private static final Integer ROLL_DICE = 20;
	private static final Integer CA_DICE = 8;
	
	private Dice d20 = new Dice(ROLL_DICE);
	private Dice d8 = new Dice(CA_DICE);
	
	private final RandomFightStyleGenerator fsGen= new RandomFightStyleGenerator();
	//private MobNameGenerator mobNameGen = new MobNameGenerator();

	private Integer healthPoints;
	private Integer armorClass;
	private FightStyleInterface fightStyle;
	private Integer initiative;
	private String name;

	/*
	 * Costruttore Mob
	 */
	public PlayableAbstract(Integer hp, Integer ac) { // Iniziativa va tirata
		this.healthPoints = hp;
		this.armorClass = ac;
		this.fightStyle = fsGen.generateRandomFightStyle();
		this.initiative = d20.roll();
		this.name = null;
	}
	
	/*
	 * Costruttore player
	 */
	public PlayableAbstract(Integer hp, String name) { // Iniziativa va tirata
		this.healthPoints = hp;
		this.armorClass = BASE_CA + d8.roll();
		this.fightStyle = fsGen.generateRandomFightStyle();
		this.initiative = d20.roll();
		this.name = name;
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
	
	public void setInitiative() {
		 this.initiative = d20.roll();
	}

	public String getName() {
		return this.name;
	}
	public boolean isAlive() {

		if (this.healthPoints > DEATH) {

			return true;

		} else {
			return false;
		}
	}

	public String toString() {

		return this.getClass().getName().toUpperCase() + " STATS:" + "\nNAME: " + this.name
				+ "\nHP: " + this.healthPoints + "\nCA: "
				+ this.armorClass + "\nFIGHT STYLE: " + this.fightStyle + "\nINITIATIVE: " + this.initiative + "\n";
	}

	public void setName(String name) {
		
		this.name = name;
		
	}
}
