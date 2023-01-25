package weapons;

public class Bow extends WeaponAbstract {

	private static final Integer DAMAGE = 8;
	private static final Integer CRIT = 3; // Crit 2x DiceDmg
	private static final Integer HIT = 20;


	public Bow() {

		super("Bow", DAMAGE, HIT, CRIT);

	}

}
