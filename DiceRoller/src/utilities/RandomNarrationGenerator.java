package utilities;

import java.util.ArrayList;

import dices.Dice;
import playable.Player;

public class RandomNarrationGenerator {

	private static final Integer MAX_SITUATION = 9;
	private static final Integer BEGINNING = 0;
	private Integer numberOfNarrationsMade = 0;

	private String fightDescription;
	private final ArrayList<String> fightPossibilities;
	private final Dice random = new Dice(MAX_SITUATION);
	private Player player;

	public RandomNarrationGenerator(Player player) {

		this.fightDescription = new String();
		this.fightPossibilities = new ArrayList<String>();
		this.player = player;

	}

	private ArrayList<String> populateNarrationArray() {

		fightPossibilities.add("Il crepuscolo si avvicina sulla valle di Pendelen, all'oriz" + player.getName().get());
		fightPossibilities.add("La foresta è dura1");
		fightPossibilities.add("La foresta è dura2");
		fightPossibilities.add("La foresta è dura3");
		fightPossibilities.add("La foresta è dura4");
		fightPossibilities.add("La foresta è dura5");
		fightPossibilities.add("La foresta è dura6");
		fightPossibilities.add("La foresta è dura7");
		fightPossibilities.add("La foresta è dura8");
		fightPossibilities.add("La foresta è dura9");

		return fightPossibilities;

	}

	public String generateRandomNarration() {

		populateNarrationArray();

		if (this.numberOfNarrationsMade == BEGINNING) {

			fightDescription = fightPossibilities.get(BEGINNING);
			numberOfNarrationsMade++;

		} else if (this.numberOfNarrationsMade < MAX_SITUATION){

			fightDescription = fightPossibilities.get(random.roll());

		}else {
			
			fightDescription = fightPossibilities.get(MAX_SITUATION);
		}

		return fightDescription;
	}

}
