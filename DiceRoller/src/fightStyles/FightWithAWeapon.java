package fightStyles;

import java.util.ArrayList;

import weapons.WeaponFactory;
import weapons.WeaponInterface;
/*
 * Beta
 */
public class FightWithAWeapon implements FightStyleInterface {

	private static final Integer MISS = 0;
	private static final Integer CRIT = 20;
	private static final int LEFT = 0;
	
	private final ArrayList <WeaponInterface> customWeapon;
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
		this.customWeapon = new ArrayList<>();
		this.customWeapon.add(weaponGen.createWeapon());

	}

	private void hitWithAWeapon(Integer numberOfWeapon) {

		if (isInAdvantage) {
			System.out.println(customWeapon.get(numberOfWeapon).getClass().getName() + " is rolling with Advantage");
			this.lastRollHit = customWeapon.get(numberOfWeapon).rollAdv();
		} else if (isInDisadvantage) {
			System.out.println(customWeapon.get(numberOfWeapon).getClass().getName() + " is rolling with Disadvantage");
			this.lastRollHit = customWeapon.get(numberOfWeapon).rollDsv();
		} else {
			System.out.println(customWeapon.get(numberOfWeapon).getClass().getName() + " is rolling without any modifiers");
			this.lastRollHit = customWeapon.get(numberOfWeapon).rollHit();
		}
	}

	/*
	 * Rendiamo più chiara la scrittura
	 * Player e Mob usano questo metodo per fronteggiarsi
	 */
	
	public void useWeapon(Integer armorClass) {

		Integer numberOfWeapon = LEFT;
		this.hitWithAWeapon(numberOfWeapon);

		if (this.lastRollHit == CRIT) {
			this.lastDmgHit = customWeapon.get(numberOfWeapon).rollCrit();
			System.out.println(customWeapon.get(numberOfWeapon).getClass().getName() + " Rolled a Crit and dealed : " + this.lastDmgHit + " damage");
		} else if (this.lastRollHit >= armorClass) {
			this.lastDmgHit = customWeapon.get(numberOfWeapon).rollDmg();
			System.out.println(customWeapon.get(numberOfWeapon).getClass().getName() + " Hitted the target and dealed : " + this.lastDmgHit + " damage");
		} else {
			this.lastDmgHit = MISS;
			System.out.println(customWeapon.get(numberOfWeapon).getClass().getName() + " Missed the target and dealed : " + this.lastDmgHit + " damage");
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
		return this.customWeapon.get(LEFT);
	}

	/*
	 * Qualora volessi impostare l'arma posso farlo con questo set
	 */
	public void setCustomWeapon(WeaponInterface customWeapon) {
		this.customWeapon.set(LEFT, customWeapon);
		
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
		return this.customWeapon.get(LEFT).getWeaponName();
	}

}
