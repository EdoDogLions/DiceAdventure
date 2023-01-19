package weapons;

import dices.Dice;

public class Bow extends WeaponAbstract {

	private static final Integer DAMAGE = 8;
	private static final Integer CRIT = 3; // Crit 2x DiceDmg
	private static final Integer HIT = 20;

	private Dice diceDmg;
	private Dice diceHit;
	private Integer lastDmg;
	private Integer lastHit;
	private String weaponName;

	public Bow() {

		this.diceDmg = new Dice(DAMAGE);
		this.diceHit = new Dice(HIT);
		this.weaponName = "Bow";

	}

	@Override
	public Integer rollDmg() {
		this.lastDmg = this.diceDmg.roll();
		return this.lastDmg;
	}

	@Override
	public Integer rollHit() {
		this.lastHit = this.diceHit.roll();
		return this.lastHit;
	}

	@Override
	public Integer rollCrit() {

		this.lastDmg = diceDmg.sumDices(diceDmg.rollXtimes(CRIT));
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

		return DAMAGE;
	}

	public String toString() {
		return "Weapon Name: " + this.weaponName + "\nWeapon Damage: " + this.getMaxDmg();

	}

	public String getWeaponName() {

		return this.weaponName;
	}
}
