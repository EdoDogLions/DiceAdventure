package feature;

/**
 * @author edoardodoglioni This enum represents all the class who exist in the
 *         Original D&D Player's Handbook
 */
public enum Class {

	BARBARIAN(1, "Barbarian"), BARD(2, "Bard"), WARLOCK(3, "Warlock"), SORCERER(4, "Sorcerer"), MAGE(5, "Mage"),
	WARRIOR(6, "Warrior"), PALADIN(7, "Paladin"), MONK(8, "Monk"), CLERIC(9, "Cleric"), RANGER(10, "Ranger"),
	ROGUE(11, "Rogue"), DRUID(12, "Druid");

	private final Integer classId;
	private final String className;

	Class(Integer id, String className) {

		this.classId = id;
		this.className = className;
	}

	/*
	 * This methods are getters
	 */
	public Integer getRaceId() {
		return classId;
	}

	public String getClassName() {
		return className;
	}

}
