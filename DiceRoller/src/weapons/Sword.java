package weapons;

/**
 * @author edoardodoglioni This class extends WeaponAbstract and represents the
 *         Sword
 */
public class Sword extends WeaponAbstract {

	private static final Integer DAMAGE = 6;
	private static final Integer CRIT = 2; // Crit 2x DiceDmg

	public Sword() {

		super("Sword", DAMAGE, CRIT);
	}

}
