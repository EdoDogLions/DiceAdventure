package playable;

/**
 * @author edoardodoglioni This is the class for the Player
 */
public class Player extends PlayableAbstract {

	private static final Double RESTORE = 0.1; // 10% of MaxHp

	private final Integer maxHp;

	public Player(final Integer hp, final String playerName) {
		super(hp, playerName);
		this.maxHp = super.getMaxHP();
	}

	/*
	 * This method restore a percentage of maximum Hp of the Player
	 */

	public void restoreHp() {

		if ((int) super.getHealthPoints() + (RESTORE * this.maxHp) < super.getMaxHP()) {
			super.setHealthPoints((int) (super.getHealthPoints() + (RESTORE * this.maxHp)));
		} else {
			super.setHealthPoints(super.getMaxHP());
		}
	}

}