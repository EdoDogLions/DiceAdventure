package fightStyles;

import utilities.RandomFightStyleGenerator;

/*
 * Beta
 */
public class FightStyleFactory implements FightStyleFactoryInterface {

	public FightStyleInterface createFightStyle() {
		RandomFightStyleGenerator fsGen = new RandomFightStyleGenerator();

		return fsGen.generateRandomFightStyle();
	}
}
