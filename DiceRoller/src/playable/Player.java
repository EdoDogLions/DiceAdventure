package playable;
/*
 * Beta
 */
public class Player extends PlayableAbstract {

	private static final Double RESTORE = 0.2;	//20% degli HP massimi

	private final Integer maxHp; // Punti ferita massimi

	public Player(final Integer hp, final String playerName) {
		super(hp, playerName);
		this.maxHp = hp;
	}

	/*
	 * Ripristino X% degli Hp massimi determinato dalla variabile
	 * RESTORE
	 */
	public void restoreHp() {

		if ((int) super.getHealthPoints() + (RESTORE * this.maxHp) < this.maxHp) {
			super.setHealthPoints( (int) (super.getHealthPoints() + (RESTORE * this.maxHp)));
		} else {
			super.setHealthPoints(this.maxHp);
		}
	}

}