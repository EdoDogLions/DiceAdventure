package weapons;

/**
 * @author edoardodoglioni This class extends WeaponAbstract and represents the
 *         Longsword
 */
public class Longsword extends WeaponAbstract {

	private static final Integer DAMAGE = 10;
	private static final Integer CRIT = 2; // Crit 2x DiceDmg

	public Longsword() {

		super("Longsword", DAMAGE, CRIT);
	}

}
