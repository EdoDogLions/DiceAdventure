package fightStyles;

import weapons.Bow;
import weapons.WeaponFactory;
import weapons.WeaponInterface;

/*
 * Beta
 */
public class FightWithTwoWeapons extends FightStyleAbstract {

	private static final Integer LEFT_HAND = 0;
	private static final Integer RIGHT_HAND = 1;
	private static final Integer NUM_WEAPONS = 2;

	/*
	 * Ho una lista di armi, all'inidice 0 la prima, all'indice 1 la seconda
	 */
	public FightWithTwoWeapons() {
		super(NUM_WEAPONS);

		this.bowException();
		this.formatName();

	}

	/*
	 * Il caso del Bow è particolare poichè non si possono avere due archi come armi
	 * in simultanea
	 */

	private void bowException() {

		WeaponInterface bowException = new Bow();
		WeaponFactory weaponGen = new WeaponFactory();

		while (customWeapon.get(LEFT_HAND).getClass() == customWeapon.get(RIGHT_HAND).getClass()
				&& customWeapon.get(LEFT_HAND).getClass() == bowException.getClass()) {

			customWeapon.set(LEFT_HAND, weaponGen.createWeapon());
		}

	}

	private void formatName() {

		if (this.customWeapon.get(LEFT_HAND).getClass() == this.customWeapon.get(RIGHT_HAND).getClass()) {

			this.fightStyleName = "Double " + customWeapon.get(LEFT_HAND).getWeaponName();

		} else {

			this.fightStyleName = customWeapon.get(LEFT_HAND).getWeaponName() + " & "
					+ customWeapon.get(RIGHT_HAND).getWeaponName();
		}
	}

	public String toString() {

		return this.fightStyleName;
	}

}
