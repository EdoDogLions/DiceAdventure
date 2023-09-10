package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fightStyles.FightStyleFactory;
import fightStyles.FightStyleInterface;

public class FightStyleFactoryTest {
	
	private FightStyleFactory fs;

	@BeforeEach
	void setUp() {
		fs = new FightStyleFactory();
	}

	@Test
	void testSetUp() {

		assertTrue(fs.createFightStyle() instanceof FightStyleInterface);

	}
}
