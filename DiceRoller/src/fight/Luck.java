package fight;

public enum Luck {

	GOOD_FOR_PLAYER(1),
	GOOD_FOR_MOB(2),
	BAD_FOR_PLAYER(3), 
	BAD_FOR_MOB(4), 
	GOOD_FOR_BOTH(5), 
	NOTHING(6);
	
	private Integer diceValue;
	
	public Integer getDiceValue() {
		return diceValue;
	}
	
	private Luck(Integer value) {
		this.diceValue = value;
	}
}
