package feature;

public class PartyPg {

	private final PairClassRace pair;
	private final Stats points;

	public PartyPg() {
		this.pair = new PairClassRace();
		this.points = new Stats();

	}

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
