package fightStyles;

/**
 * @author edoardodoglioni This class extends the FightStyleAbstract and
 *         represents the fight with single weapon
 */

public class FightWithAWeapon extends FightStyleAbstract {

	private static final Integer RIGHT_HAND = 0;
	private static final Integer NUM_WEAPONS = 1;

	public FightWithAWeapon() {
		super(NUM_WEAPONS);

	}

	@Override
	public String toString() {
		return this.customWeapon.get(RIGHT_HAND).getWeaponName();
	}

}
