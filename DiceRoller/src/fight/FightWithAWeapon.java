package fight;

import weapons.WeaponAbstract;

public class FightWithAWeapon implements FightStyleInterface{

	private WeaponAbstract customWeapon;
	private Integer lastRollHit;
	private Integer lastDmgHit;
	private boolean isInAdvantage = false;
	private boolean isInDisadvantage = false;

	public FightWithAWeapon(WeaponAbstract weapon) {
		this.customWeapon = weapon;

	}

	private void hitWithAWeapon() {

		if (isInAdvantage) {
			System.out.println("Rolling with Advantage");
			this.lastRollHit = customWeapon.rollAdv();
		} else if (isInDisadvantage) {
			System.out.println("Rolling with Disadvantage");
			this.lastRollHit = customWeapon.rollDsv();
		} else {
			this.lastRollHit = customWeapon.rollHit();
		}
	}

	public void useAWeapon(Integer armorClass) {

		this.hitWithAWeapon();

		if (this.lastRollHit == 20) {
			System.out.println("You critted!");
			this.lastDmgHit = customWeapon.rollCrit();
		} else if (this.lastRollHit >= armorClass) {
			System.out.println("You Hitted the enemy!");
			this.lastDmgHit = this.customWeapon.rollDmg();
		} else {
			System.out.println("You Missed the target");
		}
	}

	public void setAdvantage() {
		isInAdvantage = true;
		isInDisadvantage = false;

	}

	public void setDisadvantage() {
		isInAdvantage = false;
		isInDisadvantage = true;
	}

	public void resetAdDisvantage() {
		isInAdvantage = false;
		isInDisadvantage = false;
	}
	
	public WeaponAbstract getCustomWeapon() {
		return customWeapon;
	}

	public void setCustomWeapon(WeaponAbstract customWeapon) {
		this.customWeapon = customWeapon;
	}

	public Integer getLastRollHit() {
		return lastRollHit;
	}

	public Integer getLastDmgHit() {
		return lastDmgHit;
	}

	public boolean isInAdvantage() {
		return isInAdvantage;
	}

	public boolean isInDisadvantage() {
		return isInDisadvantage;
	}

	public String toString() {
		return  this.customWeapon.getClass().getName();
	}

}
