package fight;

import dices.Dice;
import playable.*;

public class Combat {

	private final Player player;
	private final MobFactory mob;
	private Dice d20 = new Dice(20);
	
	//Ha senso? O è meglio creare un Mob e aumentare sequenzialmente i suoi campi?
	private Integer mobPf;
	private Integer mobCa;
	private FightStyleInterface mobFs;
	private Integer mobInitiative = d20.roll();
	
	public Combat(Player player){
		
		this.player = player;
		this.mob = new MobFactory();
		
	}
	
	public void fight() {
		
		while(player.isAlive()) {
			mob.createMob(this.mobPf, this.mobCa, this.mobFs, this.mobInitiative);
		}
	}
	
}
