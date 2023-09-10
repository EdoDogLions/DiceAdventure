package weapons;

import dices.Dice;

/*
 * @author edoardodoglioni
 * This is an abstract class who implements WeaponInterface and represents everything a Weapon can do
 */

public abstract class WeaponAbstract implements WeaponInterface {

	private static final Integer HIT = 20;

	private Dice diceDmg;
	private Dice diceHit;
	private Integer crit;
	private Integer lastDmg;
	private Integer lastHit;
	private String weaponName;

	public WeaponAbstract(final String weaponName, final Integer damage, final Integer crit) {

		this.diceDmg = new Dice(damage);
		this.diceHit = new Dice(HIT);
		this.crit = crit;
		this.weaponName = weaponName;
	}

	/*
	 * This method roll a Dice who set the damage dealt by the weapon
	 *
	 * @return the field lastDmg where is tracked the damage dealt by the weapon
	 */
	@Override
	public Integer rollDmg() {
		this.lastDmg = this.diceDmg.roll();
		return this.lastDmg;
	}

	/*
	 * This method roll a Dice who set if the weapon hit his target
	 *
	 * @return the field lastHit where is tracked the last value of the dice to hit
	 */

	@Override
	public Integer rollHit() {
		this.lastHit = this.diceHit.roll();
		return this.lastHit;
	}

	/*
	 * This method roll more dices based on the value of the CRIT of the weapon
	 *
	 * @return the field lastDmg where is tracked the damage dealt by the weapon
	 */

	@Override
	public Integer rollCrit() {

		this.lastDmg = diceDmg.sumDices(diceDmg.rollXtimes(crit));
		return this.lastDmg;

	}

	/*
	 * This method roll dices with Advantage
	 *
	 * @return the field lastHit where is tracked the last value of the dice to hit
	 */

	@Override
	public Integer rollAdv() {

		this.lastHit = diceHit.rollWithAdvantage();

		return lastHit;
	}

	/*
	 * This method roll dices with Disadvantage
	 *
	 * @return the field lastHit where is tracked the last value of the dice to hit
	 */

	@Override
	public Integer rollDsv() {

		this.lastHit = diceHit.rollWithDisvantage();
		return this.lastHit;
	}

	/*
	 * This methods are getter of the fields of the class
	 */
	public Integer getLastHit() {
		return this.lastHit;
	}

	public Integer getLastDmg() {
		return this.lastDmg;
	}

	public Integer getMaxDmg() {

		return this.diceDmg.getFaces();
	}

	@Override
	public String getWeaponName() {
		return this.weaponName;
	}

	@Override
	public String toString() {
		return "Weapon Name: " + this.weaponName + "\nWeapon Damage: " + this.getMaxDmg();

	}
}
