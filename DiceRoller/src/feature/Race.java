package feature;

/**
 * @author edoardodoglioni This enum represents all the races who exist in the
 *         Original D&D Player's Handbook
 */
public enum Race {

	ELF(1, "Elf"), HALFLING(2, "Halfling"), HUMAN(3, "Human"), DRAGONID(4, "Dragonid"), HALFELF(5, "HalfElf"),
	HALFORC(6, "HalfOrc"), THIEFLING(7, "Thiefling"), DWARF(8, "Dwarf"), GNOME(9, "Gnome");

	private final Integer raceId;
	private final String raceName;

	Race(Integer id, String name) {

		this.raceId = id;
		this.raceName = name;
	}

	/*
	 * This methods are getters
	 */
	public Integer getRaceId() {
		return raceId;
	}

	public String getRaceName() {
		return raceName;
	}

}