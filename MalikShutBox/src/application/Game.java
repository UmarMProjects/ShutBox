package application;

/* 
 * Game class handles the rounds and manages two players 
 * @ Umar ayoub Malik 
 * 2024-12-17
 */

public class Game {
    private int totalRounds;
    private int currentRound;
    private int player1Score;
    private int player2Score;
    private int currentPlayer; 

    public Game(int totalRounds) {
        this.totalRounds = totalRounds;
        this.currentRound = 1;
        this.player1Score = 0;
        this.player2Score = 0;
        this.currentPlayer = 1;
    }
    
    public int getCurrentRound() {
        return currentRound;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

  
    public void nextTurn() {
        if (currentPlayer == 1) {
            currentPlayer = 2;
        } else {
            currentPlayer = 1;
            currentRound += 1;
        }
    }

    public void addScore(int score) {
        if (currentPlayer == 1) {
            player1Score += score;
        } else {
            player2Score += score;
        }
    }


    public boolean isGameOver() {
        return currentRound > totalRounds;
    }

    public void resetGame() {
        this.currentRound = 1;
        this.player1Score = 0;
        this.player2Score = 0;
        this.currentPlayer = 1;
    }
}
