package playable;

import fight.FightStyleInterface;

public interface MobFactoryInterface {

	public Mob createMob(Integer hp, Integer ac, FightStyleInterface fs, Integer initiative);

}
