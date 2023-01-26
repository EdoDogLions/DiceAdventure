package weapons;

import dices.Dice;

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

	public Integer rollDmg() {
		this.lastDmg = this.diceDmg.roll();
		return this.lastDmg;
	}

	public Integer rollHit() {
		this.lastHit = this.diceHit.roll();
		return this.lastHit;
	}

	public Integer rollCrit() {

		this.lastDmg = diceDmg.sumDices(diceDmg.rollXtimes(crit));
		return this.lastDmg;

	}

	public Integer rollAdv() {

		this.lastHit = diceHit.rollWithAdvantage();

		return lastHit;
	}

	public Integer rollDsv() {

		this.lastHit = diceHit.rollWithDisvantage();
		return this.lastHit;
	}

	public Integer getLastHit() {
		return this.lastHit;
	}

	public Integer getLastDmg() {
		return this.lastDmg;
	}

	public Integer getMaxDmg() {

		return this.diceDmg.getFaces();
	}

	public String toString() {
		return "Weapon Name: " + this.weaponName + "\nWeapon Damage: " + this.getMaxDmg();

	}

	public String getWeaponName() {
		return this.weaponName;
	}
}
