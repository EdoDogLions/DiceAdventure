package playable;

import utilities.MobNameGenerator;

public class Mob extends PlayableAbstract {


	private MobNameGenerator nameGen = new MobNameGenerator();


	public Mob(Integer hp, Integer ac) { // Iniziativa va tirata
		super(hp, ac);
		super.setName(nameGen.getName());
		
	}

}
