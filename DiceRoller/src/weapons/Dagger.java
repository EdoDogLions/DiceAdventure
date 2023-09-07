package weapons;

/**
 * @author edoardodoglioni This class extends WeaponAbstract and represents the
 *         Dagger
 */
public class Dagger extends WeaponAbstract {

	private static final Integer DAMAGE = 4;
	private static final Integer CRIT = 2; // Crit 2x DiceDmg

	public Dagger() {

		super("Dagger", DAMAGE, CRIT);
	}

}