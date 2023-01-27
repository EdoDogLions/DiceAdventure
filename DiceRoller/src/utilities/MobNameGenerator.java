package utilities;

import dices.Dice;
/*
 * Beta
 */
public class MobNameGenerator {
	
	private static final Integer BOUND = 1;	//Bound è 1 perchè il dado lancia da 1 - X e l'array è da 0 a X-1
	
	private final String[] title = {"Lord", "King","Servant","Prince","Queen", "Princess", "Chief" };
	private final String[] name = {"Vampires", "Zombies", "Thieves", "Lichs", "Dark Druids", "Shadows"};
	private final String concat = " of ";
	private final Dice dTitle = new Dice(title.length - BOUND);
	private final Dice dName = new Dice(name.length - BOUND );
	private final String finalName;
	
	public MobNameGenerator() {
		
		finalName = title[dTitle.roll()] + concat + name[dName.roll()];	
	}
	
	public String getName() {
		return finalName;
	}

}
