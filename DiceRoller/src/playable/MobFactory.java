package playable;
/*
 * Beta
 */
public class MobFactory implements MobFactoryInterface{

	
	public Mob createMob(final Integer hp, final Integer ac) {
		
		return new Mob(hp, ac);
	}
}
