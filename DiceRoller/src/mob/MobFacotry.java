package mob;

import fightStyle.FightStyle;

public interface MobFacotry {

	public Mob createMob(Integer hp, Integer ac, FightStyle fs, Integer initiative);

}
