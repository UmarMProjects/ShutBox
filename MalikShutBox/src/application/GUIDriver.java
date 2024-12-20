package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/* 
 * A JavaFX version of the Shut The Box game for two players
 * @ Umar ayoub Malik 
 * 2024-12-17
 */

public class GUIDriver extends Application {

	Die d1 = new Die();
	Die d2 = new Die();
	private Game game = new Game(5);

	@Override
	public void start(Stage stage) throws Exception {
		VBox vbox = new VBox(10);
		Label title = new Label("Shut The Box");
		Label roundText = new Label("Round: " + game.getCurrentRound());
		Label currentPlayerText = new Label("Current Player: Player " + game.getCurrentPlayer());

		vbox.getChildren().addAll(title, roundText, currentPlayerText);
		HBox tileBox = new HBox(10);
		Button[] tileBtns = new Button[9];
		Tile[] tiles = new Tile[9];

		for (int i = 0; i < tileBtns.length; i++) {
			tileBtns[i] = new Button(String.valueOf(i + 1));
			tiles[i] = new Tile(i + 1);
			tileBtns[i].setStyle("-fx-background-color: lightgray;");

			tileBtns[i].setOnAction(e -> {
				Button btn = (Button) e.getSource();
				if (btn.getStyle().contains("lightgray")) {
					btn.setStyle("-fx-background-color: red;");
				} else if (btn.getStyle().contains("red")) {
					btn.setStyle("-fx-background-color: lightgray;");
				}
			});

			tileBox.getChildren().add(tileBtns[i]);
		}

		Label scoreLabel = new Label("Player 1 Score: ");
		Label scoreNum1 = new Label(String.valueOf(game.getPlayer1Score()));
		Label scoreLabel2 = new Label("Player 2 Score: ");
		Label scoreNum2 = new Label(String.valueOf(game.getPlayer2Score()));

		tileBox.setAlignment(Pos.CENTER);
		tileBox.getChildren().addAll(scoreLabel, scoreNum1, scoreLabel2, scoreNum2);
		vbox.getChildren().add(tileBox);

		Button btnRoll = new Button("ROLL DICE");
		Button twobtnRoll = new Button("ROLL Two DICE");
		Button lockinbtn = new Button("Lock In");
		Button donebtn = new Button("End Turn");
		Label result1 = new Label("Result: ");
		Label resultDice = new Label("0");

		HBox horbox = new HBox(20);
		horbox.setAlignment(Pos.CENTER);
		horbox.getChildren().addAll(btnRoll, twobtnRoll, lockinbtn, donebtn, result1, resultDice);

		lockinbtn.setDisable(true);

		vbox.getChildren().add(horbox);
		vbox.setAlignment(Pos.CENTER);

		btnRoll.setDisable(true);
		btnRoll.setStyle("-fx-background-color: blue;");

		twobtnRoll.setOnAction(e -> {
			int diceTotal = d1.roll() + d2.roll();
			resultDice.setText(String.valueOf(diceTotal));
			twobtnRoll.setDisable(true);
			lockinbtn.setDisable(false);
		});

		btnRoll.setOnAction(e -> {
			int diceTotal = d1.roll();
			resultDice.setText(String.valueOf(diceTotal));
			btnRoll.setDisable(true);
			twobtnRoll.setDisable(true);
			lockinbtn.setDisable(false);
		});

		donebtn.setOnAction(e -> {
			int score = 0;

			for (Button btn : tileBtns) {
				if (btn.getStyle().contains("lightgray") || btn.getStyle().contains("red")) {
					score += Integer.parseInt(btn.getText());
				}
			}

			game.addScore(score);
			game.nextTurn();

			scoreNum1.setText(String.valueOf(game.getPlayer1Score()));
			scoreNum2.setText(String.valueOf(game.getPlayer2Score()));
			roundText.setText("Round: " + game.getCurrentRound());
			currentPlayerText.setText("Current Player: Player " + game.getCurrentPlayer());
			resultDice.setText("0");
			twobtnRoll.setDisable(false);
			btnRoll.setDisable(true);
			lockinbtn.setDisable(true);
			
			btnRoll.setStyle("-fx-background-color: blue;");
			twobtnRoll.setStyle("-fx-background-color: gray;");

		
			for (Button btn : tileBtns) {
				btn.setStyle("-fx-background-color: lightgray;");
			}

			if (game.isGameOver()) {
		        if (game.getPlayer1Score() < game.getPlayer2Score()) {
		            title.setText("Game Over: Player 2 wins!");
		        } else if (game.getPlayer2Score() < game.getPlayer1Score()) {
		            title.setText("Game Over: Player 1 wins!");
		        } else {
		            title.setText("Game Over: It's a tie!");
		        }
		        btnRoll.setDisable(true);
		        twobtnRoll.setDisable(true);
		        lockinbtn.setDisable(true);
		        donebtn.setDisable(true);
			}
		});

		lockinbtn.setOnAction(e -> {
			int sum = 0;
			for (Button btn : tileBtns) {
				if (btn.getStyle().contains("red")) {
					sum += Integer.valueOf(btn.getText());
				}
			}
			if (sum == Integer.valueOf(resultDice.getText())) {
				
				for (Button btn : tileBtns) {
					if (btn.getStyle().contains("red")) {
						btn.setStyle("-fx-background-color: blue;");
						
						
						
					}
					lockinbtn.setDisable(true);
					
				}
				twobtnRoll.setDisable(false);
				twobtnRoll.setStyle("-fx-background-color: lightgray;");
				if (tileBtns[6].getStyle().contains("blue") && tileBtns[7].getStyle().contains("blue")
						&& tileBtns[8].getStyle().contains("blue")) {
					twobtnRoll.setDisable(true);
					twobtnRoll.setStyle("-fx-background-color: blue;");
					btnRoll.setDisable(false);
					btnRoll.setStyle("-fx-background-color: lightgray;");
				}
				
			}
			
		});

		Scene scene = new Scene(vbox, 600, 300);
		stage.setScene(scene);
		stage.setTitle("Shut The Box");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
