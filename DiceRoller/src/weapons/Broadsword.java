package weapons;

import dices.Dice;

public class Broadsword extends GenericWeapon {
	
	private static final int DAMAGE = 10;
	private static final int CRIT = 3; //Crit 2x DiceDmg
	
	private Dice diceDmg;
	private Dice diceHit;
	private int lastDmg;
	private int lastHit;
	
	
	public Broadsword() {
		
		super(DAMAGE);
	}

	@Override
	public Integer rollDmg() {
		// TODO Auto-generated method stub
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
}
