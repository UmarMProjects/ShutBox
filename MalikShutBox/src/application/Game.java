package application;

/*game class handles the rounds 
 * @ Umar malik 
 * 2024-12-17
 */

public class Game {
	private int GameRounds;
	private int round;
	private int Score;

	public Game(int totalRounds) {
		this.GameRounds = totalRounds;
		this.round = 1;
		this.Score = 0;

	}

	public int getScore() {
		return Score;
	}
	
	public int addScore(int n) {
		return Score += n;
	}
	
	public void nextRound() {
	    round += 1;
	}



	public int getround() {
		return round;
	}

	public boolean isGameOver() {
		if (round > GameRounds) {
			return true;
		} else {
			return false;
		}
	}

}
