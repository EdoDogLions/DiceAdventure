import fightStyle.FightStyle;

public class Player implements Playable{

	private Integer healthPoints;
	private Integer armorClass;
	private FightStyle fightStyle;
	private Integer initiative;
	
	public Player(Integer hp, Integer ac, FightStyle fs, Integer initiative) {
		this.healthPoints = hp;
		this.armorClass = ac;
		this.fightStyle = fs;
		this.initiative = initiative;
	}
}