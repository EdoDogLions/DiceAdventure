package utilities;

import java.util.ArrayList;

import dices.Dice;
import fightStyles.FightStyleInterface;
import fightStyles.FightWithAWeapon;
import fightStyles.FightWithTwoWeapons;

/**
 * @author edoardodoglioni This utility class generate a Random Fight Style
 */

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
		this.fightStyleArray = new ArrayList<>();

	}

	/*
	 * This method generate a Random Fight Style adding it to an array of FightStyle
	 * and choosing randomly which one to use
	 *
	 * @return an Object who implements a FightStyleInterface (It can be
	 * FightWithAWeapon, or FightWithTwoWeapons)
	 */
	public FightStyleInterface generateRandomFightStyle() {

		fightStyleArray.add(fightWithAWeapon);
		fightStyleArray.add(fightWithTwoWeapons);

		return this.fightStyleArray.get(random.roll() - ARRAY_START);
	}
}
