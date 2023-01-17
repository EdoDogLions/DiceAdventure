package fight;

import weapons.WeaponFactory;
import weapons.WeaponInterface;

public class FightWithAWeapon implements FightStyleInterface {

	private static final Integer MISS = 0;
	
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
			System.out.println(customWeapon.getClass().getName() + " is rolling with Advantage");
			this.lastRollHit = customWeapon.rollAdv();
		} else if (isInDisadvantage) {
			System.out.println(customWeapon.getClass().getName() + " is rolling with Disadvantage");
			this.lastRollHit = customWeapon.rollDsv();
		} else {
			System.out.println(customWeapon.getClass().getName() + " is rolling without any modifiers");
			this.lastRollHit = customWeapon.rollHit();
		}
	}

	/*
	 * Rendiamo più chiara la scrittura
	 * Player e Mob usano questo metodo per fronteggiarsi
	 */
	
	public void useAWeapon(Integer armorClass) {

		this.hitWithAWeapon();

		if (this.lastRollHit == 20) {
			this.lastDmgHit = customWeapon.rollCrit();
			System.out.println(customWeapon.getClass().getName() + " Rolled a Crit and dealed : " + this.lastDmgHit + " damage");
		} else if (this.lastRollHit >= armorClass) {
			this.lastDmgHit = this.customWeapon.rollDmg();
			System.out.println(customWeapon.getClass().getName() + " Hitted the target and dealed : " + this.lastDmgHit + " damage");
		} else {
			this.lastDmgHit = MISS;
			System.out.println(customWeapon.getClass().getName() + " Missed the target and dealed : " + this.lastDmgHit + " damage");
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
