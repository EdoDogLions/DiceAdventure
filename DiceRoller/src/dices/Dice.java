package dices;

import java.util.*;

public class Dice implements DiceInterface {
	
	/*
	 * Beta
	 * Simulare il lancio di un qualsiasi tipo di Dado generico
	 * 
	 */
	
	private static final Integer LOW = 1;	//Valore minimo della faccia
	private final Integer faces;
	
	/*
	 * COSTRUTTORE
	 */
	public Dice(Integer faces) {
		this.faces = faces + LOW; //including last face
	}
	
	/*
	 * metodo roll()
	 */
	@Override
	public int roll() {
		Random random = new Random();
		return random.nextInt(faces - LOW) + LOW;
	}
	
	/*
	 * metodo rollXtimes()
	 */
	
	public List<Integer> rollXtimes(Integer times) {
		List<Integer> results = new ArrayList<Integer>();
		for (int x= 0; x < times; x++) {
			
			results.add(this.roll());
			
		}
		return results;
		
	}
	/*
	 * metodo sumDices()
	 */
	public Integer sumDices(List<Integer> list) {
		
		int result = 0;
		
		for (Integer value : list) {
			result += value;
		}
		return result;
		
	}
	/*
	 * metodo rollWitAdvantage()
	 */
	public Integer rollWithAdvantage() {
		int bigger = 0;
		 List<Integer> advantageList = rollXtimes(2);
		 for (Integer elements : advantageList) {
			 if (elements > bigger) {
				 bigger = elements;
			 }
		 }
		 return bigger;
	}
	
	/*
	 * metodo rollWitAdvantage()
	 */
	
	public Integer rollWithDisvantage() {
		int lower = faces;
		 List<Integer> advantageList = rollXtimes(2);
		 for (Integer elements : advantageList) {
			 if (elements < lower) {
				 lower = elements;
			 }
		 }
		 return lower;
	}

	public Integer getFaces() {
		
		return this.faces;
	}
}
