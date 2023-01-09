package mob;

import fight.FightStyle;

public interface MobFactoryInterface {

	public Mob createMob(Integer hp, Integer ac, FightStyle fs, Integer initiative);

}
