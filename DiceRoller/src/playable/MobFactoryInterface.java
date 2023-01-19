package playable;

import fightStyles.FightStyleInterface;

public interface MobFactoryInterface {

	public Mob createMob(Integer hp, Integer ac, FightStyleInterface fs);

}
