package fightStyles;

import utilities.RandomFightStyleGenerator;

public class FightStyleFactory implements FightStyleFactoryInterface {

	@Override
	public FightStyleInterface createFightStyle() {
		RandomFightStyleGenerator fsGen = new RandomFightStyleGenerator();
		
		return fsGen.generateRandomFightStyle();
	}
}
