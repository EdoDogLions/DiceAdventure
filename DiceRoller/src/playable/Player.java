package playable;
/*
 * Beta
 */
public class Player extends PlayableAbstract {

	private static final Double RESTORE = 0.1;	//10% degli HP massimi

	private final Integer maxHp; // Punti ferita massimi

	public Player(final Integer hp, final String playerName) {
		super(hp, playerName);
		this.maxHp = super.getMaxHP();
	}

	/*
	 * Ripristino X% degli Hp massimi determinato dalla variabile
	 * RESTORE
	 */
	public void restoreHp() {

		if ((int) super.getHealthPoints() + (RESTORE * this.maxHp) < super.getMaxHP()) {
			super.setHealthPoints( (int) (super.getHealthPoints() + (RESTORE * this.maxHp)));
		} else {
			super.setHealthPoints(super.getMaxHP());
		}
	}

}