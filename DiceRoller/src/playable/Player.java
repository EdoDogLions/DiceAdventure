package playable;


public class Player extends PlayableAbstract {

	private static final Double RESTORE = 0.5;
	private static final Integer DEATH = 0;

	private Integer maxHp; // Punti ferita massimi

	public Player(Integer hp, String playerName) {
		super(hp, playerName);
		this.maxHp = hp;
	}

	/*
	 * Ripristino Hp attuali + x% degli Hp massimi determinato dalla variabile
	 * RESTORE
	 */
	public void restoreHp() {

		if ((int) super.getHealthPoints() + (RESTORE * this.maxHp) < this.maxHp) {
			super.setHealthPoints( (int) (super.getHealthPoints() + (RESTORE * this.maxHp)));
		} else {
			super.setHealthPoints(this.maxHp);
		}
	}

	public boolean isAlive() {

		if (super.getHealthPoints() > DEATH) {
			return true;
		} else {

			return false;

		}
	}

}