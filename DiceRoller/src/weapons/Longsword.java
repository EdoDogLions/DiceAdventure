package weapons;
/*
 * Beta
 */
public class Longsword extends WeaponAbstract {
	
	private static final Integer DAMAGE = 10;
	private static final Integer CRIT = 2; //Crit 3x DiceDmg
	
	
	public Longsword() {
		
		super("Longsword", DAMAGE, CRIT);
	}

}
