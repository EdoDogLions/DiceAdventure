package weapons;

import dices.Dice;

public abstract class WeaponAbstract implements WeaponInterface {
	
	private static final int HIT = 20;
	private static final int CRIT = 2; //Crit 2x DiceDmg
	
	/*
	 * RAGIONA SU COME SVILUPPARE LA CLASSE ASTRATTA; SE SOLO COME CLASSE GENERICA PER TRACCIA DELLE ARMI
	 */
//	private Dice diceDmg;
//	private Dice diceHit;
//	private int lastDmg;
//	private int lastHit;
//	
	/*
	 * Controlla se GenericWeapon come classe astratta può esistere senza nessun metodo
	 */
//	public WeaponAbstract(Integer damage) {
//		
//		this.diceDmg = new Dice(damage);
//		this.diceHit = new Dice(HIT);
//		
//	}

//	@Override
//	public Integer rollDmg() {
//		// TODO Auto-generated method stub
//		this.lastDmg = this.diceDmg.roll();
//		return this.lastDmg;
//	}
//
//	@Override
//	public Integer rollHit() {
//		this.lastHit = this.diceHit.roll();
//		return this.lastHit;
//	}
//
//	@Override
//	public Integer rollCrit() {
//
//		this.lastDmg = diceDmg.sumDices(diceDmg.rollXtimes(CRIT));
//		return this.lastDmg;
//		
//	}
//
//	public Integer rollAdv() {
//		
//		this.lastHit = diceHit.rollWithAdvantage();
//		
//		return lastHit;
//	}
//	
//	public Integer rollDsv() {
//		
//		this.lastHit = diceHit.rollWithDisvantage();
//		return this.lastHit;
//	}
//	
//	public Integer getLastHit() {
//		return this.lastHit;
//	}
//	
//	public Integer getLastDmg() {
//		return this.lastDmg;
//	}
	
	public String toString() {
		return "Weapon Name: " + this.getClass().getName();
		
	}
}
