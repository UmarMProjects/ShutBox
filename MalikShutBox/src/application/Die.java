package application;

import java.util.Random;

/*a class for dice to be used in the driver  
 * @ Umar malik 
 * 2024-12-17
 * 
 */

public class Die {
	private int value;
	private int numSides;
	
	Die (){
		numSides = 6;
		value = roll();
	}
	
	Die(int n){
		numSides = n;
		value = roll();
	}
	
	public int roll() {
		Random rand = new Random();
		value = rand.nextInt(1, numSides+1);
		return value;
	}
	
	public void setSides(int n) {
		numSides = n;
	}
}
