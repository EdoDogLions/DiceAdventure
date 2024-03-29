package weapons;

/**
 * @author edoardodoglioni This class extends WeaponAbstract and represents the
 *         Bow
 */

public class Bow extends WeaponAbstract {

	private static final Integer DAMAGE = 8;
	private static final Integer CRIT = 3; // Crit 2x DiceDmg

	public Bow() {

		super("Bow", DAMAGE, CRIT);

	}

}
