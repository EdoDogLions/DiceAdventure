package playable;

/**
 * @author edoardodoglioni This class is a Factory Design Pattern who generate a
 *         Mob in a random way
 */
public class MobFactory implements MobFactoryInterface {

	/*
	 * This method create a new Mob
	 * 
	 * @param hp is the field of the Health Points
	 * 
	 * @paran ac is the field of the Armor Class
	 * 
	 * @return a new Mob
	 */
	public Mob createMob(final Integer hp, final Integer ac) {

		return new Mob(hp, ac);
	}
}
