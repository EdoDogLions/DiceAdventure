package utilities;

import java.util.ArrayList;

import playable.Mob;
import playable.Player;

public class NarrationGenerator {

	private static final Integer MAX_SITUATION = 10;
	private static final Integer BEGINNING = 0;
	private Integer numberOfNarrationsMade = 0;
	private Integer narrationindex = 0;

	private String fightDescription;
	private ArrayList<String> fightPart1;
	private ArrayList<String> fightPart2;
	private Player player;

	private String onceUponATime;
	private String badEnd;

	public NarrationGenerator(Player player) {

		this.fightDescription = new String();
		this.fightPart1 = new ArrayList<String>();
		this.fightPart2 = new ArrayList<String>();
		this.player = player;

	}

	private void staticSituationGenerator() {
		onceUponATime = "Once upon a time, in a realm known as Ardania, there lived a valiant hero named "
				+ player.getName().get()
				+ ". His armor bore the scars of countless battles, and his "
				+ player.getFightStyle()
				+ " had tasted the blood of dragons and demons alike. After years of adventures and conquests, "
				+ player.getName().get() + " was eager to return to his beloved castle,"
				+ " where his people awaited his triumphant return";

		badEnd = ", but his enemy was too strong for him, tired from many fights and tried by many wounds, he falls to the ground, dead.";
	}

	/*
	 * Per rendere più credibile la narrazione la descrizione della seconda parte è
	 * lasciata all'inizio di un altro fight
	 */
	private void populateNarrationArray(Mob mob) {

		/*
		 * INDEX 0
		 */
		fightPart1.add(player.getName().get()
				+ " had just crossed the treacherous Blackridge Mountains when he encountered his first enemy, "
				+ "a fearsome band of " + mob.getName().get() + "s. With a flash of " + player.getFightStyle()
				+ ", he engaged a fight");
		fightPart2.add(
				" and killed them. Their ill-gotten gains now redistributed among the impoverished villagers they had terrorized.");
		/*
		 * INDEX 1
		 */
		fightPart1.add(
				"Continuing on his journey, he entered the Whispering Woods, where ancient trees seemed to murmur secrets of forgotten times."
						+ " Suddenly, a " + mob.getName().get()
						+ ", corrupted by dark magic, emerged from the shadows.  With a battle cry, "
						+ player.getName().get() + " clashed with the enemy");
		fightPart2.add(" ultimately breaking the enchantment that had enslaved it.");
		/*
		 * INDEX 2
		 */
		fightPart1.add("As he ventured further, he came across a cursed swamp, where was hiding a " + mob.getName().get()
				+ ". His ghostly forms beckoned him into the muck");
		fightPart2.add("but with a talisman blessed by the village druids, he banished " + mob.getName().get()
				+ "and cleansed the land.");
		/*
		 * INDEX 3
		 */
		fightPart1.add("Moving on into the eerie Mistwood Forest hid a malevolent " + mob.getName().get()
				+ " who sought to ensnare his mind with illusions. Through sheer determination and unwavering focus, "
				+ player.getName().get() + "broke free from her bewitching grasp");
		fightPart2.add("and left her bewildered in his wake.");
		/*
		 * INDEX 4
		 */
		fightPart1.add("Next came the vast Desert of Desolation, where a sandstorm conjured a " + mob.getName().get()
				+ ". His strength and cunning were put to the test");
		fightPart2.add(" as he chipped away at the enemy, until it crumbled into a mound of lifeless sand.");
		/*
		 * INDEX 5
		 */
		fightPart1.add(player.getName().get() + "  is only halfway through his journey, Once into the heart of the Everfrost Peaks, a relentless " + mob.getName().get() + "blocked his path. "
				+ "The bitter cold threatened to freeze his very soul, he enagegd the fight");
		fightPart2.add(" with fiery resolve, " + player.getName().get() + "pierced the " + mob.getName().get()
				+ "'s heart, ending its icy reign.");
		/*
		 * INDEX 6
		 */
		fightPart1.add("A few days later he passed throw the haunted Hollows of Shadows where held a legion of " + mob.getName().get() + ", "
				+ "their voices a haunting chorus. " + player.getName().get()
				+ ", undeterred, try to invoke the divine protection of the Church of the Eternal Light,");
		fightPart2.add(" sending the " + mob.getName().get()
				+ " back to the netherworld with a blinding burst of holy energy.");
		/*
		 * INDEX 7
		 */
		fightPart1.add("Beneath the rugged cliffs of the Stormbreaker Mountains, " + player.getName().get()
				+ "ventured into the treacherous Abyssal Caverns of Drenthal. Within the labyrinthine tunnels, he faced a horde of "
				+ mob.getName().get() + "their massive frames casting eerie shadows in the dimly lit passages. With "
				+ player.getFightStyle() + " as his guiding light, he battled these fearsome creatures");
		fightPart2.add(", outsmarting them by triggering a rockfall that buried the " + mob.getName().get()
				+ "under tons of rubble.");
		/*
		 * INDEX 8
		 */
		fightPart1.add("In the Moonlit Gorge, " + player.getName().get() + "confronted with a" + mob.getName().get()
				+ ".  Using swift strikes of his " + player.getFightStyle()
				+ ", he fended off their relentless attacks");
		fightPart2.add(", with courage unshaken, he banished the enemy with a blinding flash of divine light");
		/*
		 * INDEX 9
		 */
		fightPart1.add("Finally, on the outskirts of his own castle, " + player.getName().get()
				+ "confronted his most formidable adversary: The " + mob.getName().get()
				+ ", conjured by a dark sorcerer. The two clashed in a battle of skill and wits");
		fightPart2.add(", but it was " + player.getName().get()
				+ "'s unwavering dedication to his people and his unbreakable spirit that triumphed, dispelling the dark magic and restoring peace to the land.");

	}

	public String concatFightString(Integer numberOfNarrationsMade) {

		staticSituationGenerator();
		if (player.isAlive()) {

			fightDescription = fightPart1.get(numberOfNarrationsMade).concat(fightPart2.get(numberOfNarrationsMade));
			return fightDescription;

		} else {

			fightDescription = fightPart1.get(numberOfNarrationsMade).concat(badEnd);
			return fightDescription;
		}
	}

	public String generateNarration(Mob mob) {

		clearNarrationArray();
		staticSituationGenerator();
		populateNarrationArray(mob);

		if (this.narrationindex == BEGINNING) {

			fightDescription = onceUponATime;

			narrationindex++;

		} else if (narrationindex <= MAX_SITUATION) {

			fightDescription = concatFightString(numberOfNarrationsMade);
			narrationindex++;
			numberOfNarrationsMade++;

		}

		return fightDescription;

	}

	private void clearNarrationArray() {

		fightPart1.clear();
		fightPart2.clear();
	}
	
	public String getOUAT() {
		return onceUponATime;
	}

}
