package playable;

import java.util.Optional;

import dices.Dice;
import fightStyles.FightStyleInterface;
import utilities.*;

/**
 * @author edoardodoglioni This class represents everything who is playable in
 *         the game
 *
 */
public class PlayableAbstract implements PlayableInterface {

	private static final Integer DEATH = 0;
	private static final Integer BASE_CA = 10;
	private static final Integer ROLL_DICE = 20;
	private static final Integer CA_DICE = 8;

	private final Dice d20 = new Dice(ROLL_DICE);
	private final Dice d8 = new Dice(CA_DICE);

	private final RandomFightStyleGenerator fsGen = new RandomFightStyleGenerator();

	private Integer maxHp;
	private Integer healthPoints;
	private Integer armorClass;
	private FightStyleInterface fightStyle;
	private Integer initiative;
	private Optional<String> name;

	/*
	 * We have two kind of Constructor, one for the Mob, and one for the Player
	 */
	public PlayableAbstract(Integer hp, Integer ac) {
		this.maxHp = hp;
		this.healthPoints = hp;
		this.armorClass = ac;
		this.fightStyle = fsGen.generateRandomFightStyle();
		this.initiative = d20.roll();
		this.name = Optional.empty();
	}

	public PlayableAbstract(Integer hp, String name) {
		this.maxHp = hp;
		this.healthPoints = hp;
		this.armorClass = BASE_CA + d8.roll();
		this.fightStyle = fsGen.generateRandomFightStyle();
		this.initiative = d20.roll();
		this.name = Optional.ofNullable(name);
	}

	/*
	 * This methods are getter and setters of the Playable fields
	 */
	public Integer getMaxHP() {
		return maxHp;
	}

	public Integer getHealthPoints() {
		return healthPoints;
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

	public Optional<String> getName() {
		return this.name;
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

	public void setInitiative() {
		this.initiative = d20.roll();
	}

	public void setName(String name) {

		this.name = Optional.ofNullable(name);

	}

	public boolean isAlive() {

		if (this.healthPoints > DEATH) {

			return true;

		} else {
			return false;
		}
	}

	public String toString() {

		return this.getClass().getSimpleName().toUpperCase() + " STATS:" + "\nNAME: " + this.name.get() + "\nMAX HP: "
				+ this.maxHp + "\nACTUAL HP: " + this.healthPoints + "\nCA: " + this.armorClass + "\nFIGHT STYLE: "
				+ this.fightStyle + "\nINITIATIVE: " + this.initiative + "\n";
	}

}
