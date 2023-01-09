package mob;

import fight.FightStyle;

public class MobFactory implements MobFactoryInterface{

	public Mob createMob(Integer hp, Integer ac, FightStyle fs, Integer initiative) {
		
		return new Mob(hp, ac, fs, initiative);
	}
}
