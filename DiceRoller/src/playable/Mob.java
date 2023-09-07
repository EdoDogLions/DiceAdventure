package playable;

import utilities.MobNameGenerator;

/**
 * @author edoardodoglioni This class represents a Mob, an enemy who we want to
 *         defeat during our exploration This class extends PlayableAbstract and
 *         uses a MobNameGenerator to give our enemy a name
 */
public class Mob extends PlayableAbstract {

	private final MobNameGenerator nameGen = new MobNameGenerator();

	public Mob(Integer hp, Integer ac) {
		super(hp, ac);
		super.setName(nameGen.getName());

	}

}
