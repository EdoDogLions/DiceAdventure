package fight;

import weapons.WeaponFactory;
import weapons.WeaponInterface;

public class FightWithAWeapon implements FightStyleInterface {

	private WeaponInterface customWeapon;
	private Integer lastRollHit;
	private Integer lastDmgHit;
	private boolean isInAdvantage = false;
	private boolean isInDisadvantage = false;

	/*
	 * Quando si crea uno stile di combattimento, di default faccio in modo che
	 * l'arma generata sia casuale
	 */
	public FightWithAWeapon() {
		WeaponFactory weaponGen = new WeaponFactory();
		this.customWeapon = weaponGen.createWeapon();

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

	public WeaponInterface getCustomWeapon() {
		return customWeapon;
	}

	/*
	 * Qualora volessi impostare l'arma posso farlo con questo set
	 */
	public void setCustomWeapon(WeaponInterface customWeapon) {
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
		return this.customWeapon.getClass().getName();
	}

}
