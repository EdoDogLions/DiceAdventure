package fightStyles;

import java.util.ArrayList;

import weapons.WeaponFactory;
import weapons.WeaponInterface;

public abstract class FightStyleAbstract implements FightStyleInterface {

	private static final Integer MISS = 0;
	private static final Integer CRIT = 20;
	private static final Integer INITIAL_DAMAGE = 0;

	protected final ArrayList<WeaponInterface> customWeapon;
	private Integer numOfWeapon;
	private Integer lastRollHit;
	private Integer lastDmgHit;
	private boolean isInAdvantage = false;
	private boolean isInDisadvantage = false;
	protected String fightStyleName;

	public FightStyleAbstract(Integer numOfWeapon) {

		this.numOfWeapon = numOfWeapon;
		WeaponFactory weaponGen = new WeaponFactory();
		this.customWeapon = new ArrayList<>();

		for (Integer i = 0; i < numOfWeapon; i++) {
			this.customWeapon.add(weaponGen.createWeapon());
		}

	}

	private void hitWithAWeapon(Integer numberOfWeapon) {

		if (isInAdvantage) {
			System.out.println(customWeapon.get(numberOfWeapon).getClass().getName() + " is rolling with Advantage");
			this.lastRollHit = customWeapon.get(numberOfWeapon).rollAdv();
		} else if (isInDisadvantage) {
			System.out.println(customWeapon.get(numberOfWeapon).getClass().getName() + " is rolling with Disadvantage");
			this.lastRollHit = customWeapon.get(numberOfWeapon).rollDsv();
		} else {
			System.out.println(
					customWeapon.get(numberOfWeapon).getClass().getName() + " is rolling without any modifiers");
			this.lastRollHit = customWeapon.get(numberOfWeapon).rollHit();
		}
	}

	@Override
	public void useWeapon(Integer armorClass) {

		Integer totalDamage = INITIAL_DAMAGE;
		this.lastDmgHit = INITIAL_DAMAGE;

		for (Integer actualWeapon = 0; actualWeapon < numOfWeapon; actualWeapon++) {

			this.hitWithAWeapon(actualWeapon);

			if (this.lastRollHit == CRIT) {
				totalDamage = customWeapon.get(actualWeapon).rollCrit();
				System.out.println(customWeapon.get(actualWeapon).getClass().getName() + " Rolled a Crit and dealed : "
						+ totalDamage + " damage");
			} else if (this.lastRollHit >= armorClass) {
				totalDamage = customWeapon.get(actualWeapon).rollDmg();
				System.out.println(customWeapon.get(actualWeapon).getClass().getName()
						+ " Hitted the target and dealed : " + totalDamage + " damage");
			} else {
				totalDamage = MISS;
				System.out.println(customWeapon.get(actualWeapon).getClass().getName()
						+ " Missed the target and dealed : " + totalDamage + " damage");
			}

			this.lastDmgHit += totalDamage;
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

	/*
	 * Qualora volessi impostare l'arma posso farlo con questo set
	 */

	public void setCustomWeapon(WeaponInterface customWeapon) {

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
		return this.fightStyleName;
	}

}
