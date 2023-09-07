package feature;

import dices.Dice;

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

	private static Class randomClass() {
		classList = Class.values();

		return classList[dClass.roll() - BOUND];
	}

	private static Race randomRace() {
		raceList = Race.values();

		return raceList[dRace.roll() - BOUND];
	}

	public Pair<Class, Race> getPairClassRace() {

		return this.pair;
	}

	public String toString() {
		return "CLASS:\n" + this.pair.getFirst().getClassName() + "\n\nRACE:\n" + this.pair.getSecond().getRaceName();
	}
}
