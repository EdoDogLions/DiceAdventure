package utilities;

import java.util.ArrayList;

import dices.Dice;
import fightStyles.FightStyleInterface;
import fightStyles.FightWithAWeapon;
import fightStyles.FightWithTwoWeapons;

public class RandomFightStyleGenerator {

	private static final Integer FIGHT_STYLE = 2;
	private static final Integer ARRAY_START = 1;

	private FightWithAWeapon fightWithAWeapon;
	private FightWithTwoWeapons fightWithTwoWeapons;
	private ArrayList<FightStyleInterface> fightStyleArray;
	private Dice random = new Dice(FIGHT_STYLE);

	public RandomFightStyleGenerator() {

		this.fightWithAWeapon = new FightWithAWeapon();
		this.fightWithTwoWeapons = new FightWithTwoWeapons();
		this.fightStyleArray = new ArrayList<FightStyleInterface>();

	}

	public FightStyleInterface generateRandomFightStyle() {

		/*
		 * Aggiungo le 4 armi all'array
		 */
		fightStyleArray.add(fightWithAWeapon);
		fightStyleArray.add(fightWithTwoWeapons);

		return this.fightStyleArray.get(random.roll() - ARRAY_START);
	}
}
