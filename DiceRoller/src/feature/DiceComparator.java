package feature;

import java.util.Comparator;

public class DiceComparator implements Comparator <Integer>{

	public int compare(Integer arg0, Integer arg1) {
		
		return Integer.compare(arg0, arg1);
	}

}
