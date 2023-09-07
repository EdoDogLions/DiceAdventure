package utilities;

import dices.Dice;

/**
 * @author edoardodoglioni This is an utility class who generates some random
 *         name for our Mobs It uses the string concatenation to give our Mob a
 *         better view
 */
public class MobNameGenerator {

	private static final Integer BOUND = 1; // To keep using our Dice i have to create a Bound because our dice starts
											// from 1 but array starts from 0

	private final String[] adjective = { "Silent", "Putrid", "Tiny", "Monstruos", "Wild", "Creepy", "Abandoned",
			"Chained", "Enraged", "Lost", "Bloodthirsty", "Undead", };
	private final String[] type = { "Vampire", "Zombie", "Thief", "Lich", "Druid", "Shadow", "Troll", "Dwarf", "Goblin",
			"Giant", "Gorgon", "Minotaur" };
	private final String concat = " ";
	private final Dice dTitle = new Dice(adjective.length - BOUND);
	private final Dice dName = new Dice(type.length - BOUND);
	private final String finalName;

	public MobNameGenerator() {

		finalName = adjective[dTitle.roll()] + concat + type[dName.roll()];
	}

	public String getName() {
		return finalName;
	}

}
