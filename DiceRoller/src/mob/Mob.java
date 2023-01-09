package mob;

import fightStyle.FightStyle;

public class Mob implements Playable{

	private Integer healthPoints;
	private Integer armorClass;
	private FightStyle fightStyle;
	private Integer initiative;
	
	public Mob(Integer hp, Integer ac, FightStyle fs, Integer initiative) {
		this.healthPoints = hp;
		this.armorClass = ac;
		this.fightStyle = fs;
		this.initiative = initiative;
	}
	
}
