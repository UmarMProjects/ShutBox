package application;

/*for the tiles of the shut the box game 
 * @ Umar malik 
 * 2024-12-17
 */


public class Tile {
	private int value;
	private boolean isUp;
	
	Tile(int n){
		value = n;
		isUp = true;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isUp() {
		return isUp;
	}
	
	public void putDown() {
		isUp = false;
	}
	
	@Override
	public String toString() {
		String response = "";
		if (isUp) {
			response = "" + value + "(U) ";
		}
		else {
			response = "" + value + "(D) ";			
		}
		return response;
	}
}
