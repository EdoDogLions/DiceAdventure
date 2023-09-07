package feature;

/**
 * @author edoardodoglioni This class rapresents a Player for a D&D Party
 */
public class PartyPg {

	private final PairClassRace pair;
	private final Stats points;

	public PartyPg() {
		this.pair = new PairClassRace();
		this.points = new Stats();

	}

	/*
	 * This methods are getter for the fields
	 */
	public PairClassRace getClassRace() {
		return pair;
	}

	public Stats getPoints() {
		return points;
	}

	public String toString() {
		return this.pair + "\n\n" + this.points;
	}
}
