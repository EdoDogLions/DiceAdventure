package weapons;

public class Broadsword extends WeaponAbstract {
	
	private static final Integer DAMAGE = 10;
	private static final Integer CRIT = 3; //Crit 3x DiceDmg
	private static final Integer HIT = 20;
	
	
	public Broadsword() {
		
		super("Broadsword", DAMAGE, HIT, CRIT);
	}

}
