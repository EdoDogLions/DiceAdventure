package feature;

import dices.Dice;

/**
 * @author edoardodoglioni This class generate randomly a Class and a Race
 */
public class PairClassRace {

	private static final int BOUND = 1;
	private static Dice dClass = new Dice(Class.values().length);
	private static Dice dRace = new Dice(Race.values().length);

	private static Class[] classList;
	private static Race[] raceList;

	private Pair<Class, Race> pair;

	public PairClassRace() {

		this.pair = new Pair<Class, Race>(randomClass(), randomRace());
	}

	/*
	 * This method choose randomly a Class from the Class Enum
	 * 
	 * @return an instance of Enum Class
	 */
	private static Class randomClass() {
		classList = Class.values();

		return classList[dClass.roll() - BOUND];
	}

	/*
	 * This method choose randomly a Race from the Race Enum
	 * 
	 * @return an instance of Race Class
	 */
	private static Race randomRace() {
		raceList = Race.values();

		return raceList[dRace.roll() - BOUND];
	}

	/*
	 * This method return the pair of Class, Race
	 * 
	 * @return pair
	 */
	public Pair<Class, Race> getPairClassRace() {

		return this.pair;
	}

	public String toString() {
		return "CLASS:\n" + this.pair.getFirst().getClassName() + "\n\nRACE:\n" + this.pair.getSecond().getRaceName();
	}
}
