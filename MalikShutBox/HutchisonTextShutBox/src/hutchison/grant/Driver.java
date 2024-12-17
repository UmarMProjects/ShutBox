package hutchison.grant;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		Die d1 = new Die();
		Die d2 = new Die();
		
		Tile[] tiles = new Tile[9];
		
		// populate the array with tiles
		for (int i=0; i<tiles.length; i++) {
			tiles[i] = new Tile((i+1));
		}
		printTiles(tiles);
		
		int diceSum = d1.roll() + d2.roll();
		System.out.println("Roll is: " + diceSum);
		System.out.println("Which tiles? Separate with spaces.");
		Scanner in = new Scanner(System.in);
		
		String data = in.nextLine();
		String[] values = data.split(" "); // split on spaces
		int inputSum =0;
		
		for (String value: values) {
			int n = Integer.valueOf(value);
			
		}
		System.out.println(inputSum);
		if (inputSum == diceSum) {
			System.out.println("GOOD");
			//
		}
		else {
			System.out.println("BAD");
		}
		
		

	}
	
	private static void printTiles(Tile[] tiles) {
		for (Tile t: tiles) {
			System.out.print(t);
		}
		System.out.println();
	}

}
