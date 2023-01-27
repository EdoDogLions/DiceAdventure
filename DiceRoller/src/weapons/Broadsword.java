package weapons;
/*
 * Beta
 */
public class Broadsword extends WeaponAbstract {
	
	private static final Integer DAMAGE = 10;
	private static final Integer CRIT = 3; //Crit 3x DiceDmg
	
	
	public Broadsword() {
		
		super("Broadsword", DAMAGE, CRIT);
	}

}
