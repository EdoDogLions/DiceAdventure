package playable;

import fightStyles.FightStyleInterface;

public class MobFactory implements MobFactoryInterface{

	public Mob createMob(Integer hp, Integer ac, FightStyleInterface fs) {
		
		return new Mob(hp, ac);
	}
}
