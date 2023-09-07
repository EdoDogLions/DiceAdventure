package utilities;

import dices.Dice;
/*
 * Beta
 */
public class MobNameGenerator {
	
	private static final Integer BOUND = 1;	//Bound è 1 perchè il dado lancia da 1 a X e l'array è da 0 a X-1
	
	private final String[] adjective = {"Silent", "Putrid","Tiny","Monstruos","Wild", "Creepy", "Abandoned", "Chained", "Enraged", "Lost", "Bloodthirsty", "Undead",  };
	private final String[] type = {"Vampire", "Zombie", "Thief", "Lich", "Druid", "Shadow", "Troll", "Dwarf", "Goblin" , "Giant", "Gorgon", "Minotaur"};
	private final String concat = " ";
	private final Dice dTitle = new Dice(adjective.length - BOUND);
	private final Dice dName = new Dice(type.length - BOUND );
	private final String finalName;
	
	public MobNameGenerator() {
		
		finalName = adjective[dTitle.roll()] + concat + type[dName.roll()];	
	}
	
	public String getName() {
		return finalName;
	}

}
