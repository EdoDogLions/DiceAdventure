package utilities;

import java.util.ArrayList;

import dices.Dice;
import fightStyles.FightStyleInterface;
import fightStyles.FightWithAWeapon;
import fightStyles.FightWithTwoWeapons;

public class RandomFightStyleGenerator {

	private static final Integer FIGHT_STYLE = 2;
	private static final Integer ARRAY_START = 1;

	private final FightWithAWeapon fightWithAWeapon;
	private final FightWithTwoWeapons fightWithTwoWeapons;
	private final ArrayList<FightStyleInterface> fightStyleArray;
	private final Dice random = new Dice(FIGHT_STYLE);

	public RandomFightStyleGenerator() {

		this.fightWithAWeapon = new FightWithAWeapon();
		this.fightWithTwoWeapons = new FightWithTwoWeapons();
		this.fightStyleArray = new ArrayList<FightStyleInterface>();

	}

	public FightStyleInterface generateRandomFightStyle() {

		/*
		 * Aggiungo i due stili di combattimento all'array
		 */
		fightStyleArray.add(fightWithAWeapon);
		fightStyleArray.add(fightWithTwoWeapons);

		return this.fightStyleArray.get(random.roll() - ARRAY_START);
	}
}
