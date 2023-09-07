package weapons;

/**
 * @author edoardodoglioni This class extends WeaponAbstract and represents the
 *         Hammer
 */
public class Hammer extends WeaponAbstract {

	private static final Integer DAMAGE = 6;
	private static final Integer CRIT = 3; // Crit 3x DiceDmg

	public Hammer() {

		super("Hammer", DAMAGE, CRIT);

	}

}
