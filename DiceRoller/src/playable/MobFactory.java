package playable;

import fight.FightStyleInterface;

public class MobFactory implements MobFactoryInterface{

	public Mob createMob(Integer hp, Integer ac, FightStyleInterface fs, Integer initiative) {
		
		return new Mob(hp, ac, fs, initiative);
	}
}
