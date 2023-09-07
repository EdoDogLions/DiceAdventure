package feature;

import dices.Dice;
import java.util.List;

/**
 * @author edoardodoglioni This class contains all the stats of a character
 *         based on the D&D Player's Handbook
 *
 */
public class Stats {

	private static final Integer POINT_DICE = 6;
	private static final Integer STANDARD = 4;

	private final Dice dPoints = new Dice(POINT_DICE);

	private final Integer strenght;
	private final Integer dexterity;
	private final Integer costitution;
	private final Integer wisdom;
	private final Integer intelligency;
	private final Integer charisma;

	public Stats() {
		this.strenght = calculateDice();
		this.dexterity = calculateDice();
		this.costitution = calculateDice();
		this.wisdom = calculateDice();
		this.intelligency = calculateDice();
		this.charisma = calculateDice();
	}

	/*
	 * This method calculate the stats of a character based on the rules of D&D
	 * rolling 4 times a 6faced dice, removing the lowest and summing the other
	 * three
	 * 
	 * @return the value of the Sum of the dices
	 */
	private Integer calculateDice() {

		List<Integer> rolledDices = dPoints.rollXtimes(STANDARD);
		rolledDices.sort(new DiceComparator());
		rolledDices.remove(rolledDices.size() - STANDARD);

		return dPoints.sumDices(rolledDices);
	}

	/*
	 * This method calculates the value of the Modifier based on D&D Player's
	 * Handbook Rules
	 * 
	 * @return the value of the Modifier
	 */
	private Integer calculateMod(Integer value) {

		return (value - 10) / 2;
	}

	public Integer getStrenght() {
		return strenght;
	}

	public Integer getDexterity() {
		return dexterity;
	}

	public Integer getCostitution() {
		return costitution;
	}

	public Integer getWisdom() {
		return wisdom;
	}

	public Integer getIntelligency() {
		return intelligency;
	}

	public Integer getCharisma() {
		return charisma;
	}

	public String toString() {
		return "STR: " + this.strenght + " [" + calculateMod(strenght) + "]" + "\nDEX: " + this.dexterity + " ["
				+ calculateMod(dexterity) + "]" + "\nCOS: " + this.costitution + " [" + calculateMod(costitution) + "]"
				+ "\nWIS: " + this.wisdom + " [" + calculateMod(wisdom) + "]" + "\nINT: " + this.intelligency + " ["
				+ calculateMod(intelligency) + "]" + "\nCHA: " + this.charisma + " [" + calculateMod(charisma) + "]";
	}

}
