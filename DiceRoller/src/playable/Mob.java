package playable;

import utilities.MobNameGenerator;

public class Mob extends PlayableAbstract {


	private final MobNameGenerator nameGen = new MobNameGenerator();


	public Mob(Integer hp, Integer ac) {
		super(hp, ac);
		super.setName(nameGen.getName());
		
	}

}
