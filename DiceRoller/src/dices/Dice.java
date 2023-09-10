package dices;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author edoardodoglioni Dice class is a Random Integer generator between 1
 *         and a maximum number determinated by the field faces, who is
 *         initialized in the constructor
 *
 */
public class Dice implements DiceInterface {

	private static final Integer LOW = 1; // Valore minimo della faccia
	private final Integer faces;

	public Dice(Integer faces) {
		this.faces = faces + LOW; // including last face
	}

	/*
	 * this method generate a random Integer in a range
	 */
	@Override
	public int roll() {
		Random random = new Random();
		return random.nextInt(faces - LOW) + LOW;
	}

	/*
	 * This method rolls the dice a determinated number of times
	 *
	 * @param times is the parameter who determinate how many times the dice will be
	 * rolled
	 *
	 * @return the result of the sum
	 */

	public List<Integer> rollXtimes(Integer times) {
		List<Integer> results = new ArrayList<>();
		for (int x = 0; x < times; x++) {

			results.add(this.roll());

		}
		return results;

	}

	/*
	 * This method calculate the sum of more than a dice rolled
	 *
	 * @param a List of Integer
	 *
	 * @return the sum of the value in list
	 */
	public Integer sumDices(List<Integer> list) {

		int result = 0;

		for (Integer value : list) {
			result += value;
		}
		return result;

	}

	/*
	 * This method rolls two dices
	 *
	 * @return an Integer with the value of the greatest
	 */
	public Integer rollWithAdvantage() {
		int bigger = 0;
		List<Integer> advantageList = rollXtimes(2);
		for (Integer elements : advantageList) {
			if (elements > bigger) {
				bigger = elements;
			}
		}
		return bigger;
	}

	/*
	 * This method rolls two dices
	 *
	 * @return an Integer with the value of the least
	 */

	public Integer rollWithDisvantage() {
		int lower = faces;
		List<Integer> advantageList = rollXtimes(2);
		for (Integer elements : advantageList) {
			if (elements < lower) {
				lower = elements;
			}
		}
		return lower;
	}

	/*
	 * This method is useful to know what kind of dice are we rolling
	 *
	 * @return the number of faces of the dice
	 */
	public Integer getFaces() {

		return this.faces;
	}

}
