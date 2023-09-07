package fightStyles;

import utilities.RandomFightStyleGenerator;

/**
 * @author edoardodoglioni This class is a Factory Design Pattern who generate a
 *         FightStyle in a random way
 *
 */
public class FightStyleFactory implements FightStyleFactoryInterface {

	public FightStyleInterface createFightStyle() {
		RandomFightStyleGenerator fsGen = new RandomFightStyleGenerator();

		return fsGen.generateRandomFightStyle();
	}
}
