package playable;

public class MobFactory implements MobFactoryInterface{

	
	public Mob createMob(Integer hp, Integer ac) {
		
		return new Mob(hp, ac);
	}
}
