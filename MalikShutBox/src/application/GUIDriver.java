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

/*A javafx vertion of the shut the box game 
 * @ Umar malik awd
 * 2024-12-17
 */

public class GUIDriver extends Application {

	Die d1 = new Die();
	int count2 = 0;
	private Game game = new Game(5);

	@Override
	public void start(Stage stage) throws Exception {
		VBox vbox = new VBox(10);
		Label title = new Label("Shut The Box");
		Label roundText = new Label("Round: Number: " + game.getround());

		vbox.getChildren().addAll(title, roundText);
		HBox tileBox = new HBox(10);
		Button[] tileBtns = new Button[9];
		Tile[] tiles = new Tile[9];

		for (int i = 0; i < tileBtns.length; i++) {
			tileBtns[i] = new Button(String.valueOf(i + 1));
			tiles[i] = new Tile(i + 1);
			tileBtns[i].setStyle("-fx-background-color: lightgray;");

			tileBtns[i].setOnAction(e -> {
				if (((Node) e.getSource()).getStyle().contains("lightgray")) {
					((Node) e.getSource()).setStyle("-fx-background-color: red;");
				} else if (((Node) e.getSource()).getStyle().contains("red")) {
					((Node) e.getSource()).setStyle("-fx-background-color: lightgray;");
				}
			});

			tileBox.getChildren().add(tileBtns[i]);
		}
		Label scoreLabel = new Label("Score: ");
		Label scoreNum = new Label("0");

		tileBox.setAlignment(Pos.CENTER);
		tileBox.getChildren().addAll(scoreLabel, scoreNum);
		vbox.getChildren().add(tileBox);

		Button btnRoll = new Button("ROLL DICE");
		Button twobtnRoll = new Button("ROLL Two DICE");
		Button lockinbtn = new Button("Lock In");
		Button donebtn = new Button("End Round");
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

		btnRoll.setOnAction(e -> {
			resultDice.setText(String.valueOf(d1.roll()));
			btnRoll.setDisable(true);
			lockinbtn.setDisable(false);
		});

		twobtnRoll.setOnAction(e -> {
			int diceTotal = d1.roll() + d1.roll();
			resultDice.setText(String.valueOf(diceTotal));
			twobtnRoll.setDisable(true);
			lockinbtn.setDisable(false);
		});

		donebtn.setOnAction(e -> {
			int score = 0;

			for (int i = 0; i < tileBtns.length; i++) {
				if (tileBtns[i].getStyle().contains("lightgray") || tileBtns[i].getStyle().contains("red")) {
					score += Integer.valueOf(tileBtns[i].getText());

				}
			}

			game.addScore(score);
			game.nextRound();
			scoreNum.setText(String.valueOf(game.getScore()));
			twobtnRoll.setDisable(true);
			btnRoll.setDisable(true);
			lockinbtn.setDisable(true);
			twobtnRoll.setStyle("-fx-background-color: blue;");
			btnRoll.setStyle("-fx-background-color: blue;");
			lockinbtn.setStyle("-fx-background-color: blue;");

			if (game.isGameOver()) {
				title.setText("Game Over end Score: " + game.getScore());
				btnRoll.setDisable(true);
				twobtnRoll.setDisable(true);
				lockinbtn.setDisable(true);
				donebtn.setDisable(true);

			} else {
				roundText.setText("Round: Number: " + game.getround());
				resultDice.setText("0");
				btnRoll.setDisable(true);
				btnRoll.setStyle("-fx-background-color: lightgray;");
				twobtnRoll.setDisable(false);
				twobtnRoll.setStyle("-fx-background-color: lightgray;");
				lockinbtn.setDisable(true);
				lockinbtn.setStyle("-fx-background-color: lightgray;");
				for (int i = 0; i < tileBtns.length; i++) {
					tileBtns[i].setStyle("-fx-background-color: lightgray;");

				}
			}
		});

		lockinbtn.setOnAction(e -> {
			int sum = 0;

			for (int i = 0; i < tileBtns.length; i++) {
				if (tileBtns[i].getStyle().contains("red")) {
					sum += Integer.valueOf(tileBtns[i].getText());
				}
			}

			if (sum == Integer.valueOf(resultDice.getText())) {
				for (int i = 0; i < tileBtns.length; i++) {
					if (tileBtns[i].getStyle().contains("red")) {
						tileBtns[i].setStyle("-fx-background-color: blue;");
					}
				}
				twobtnRoll.setDisable(false);
				btnRoll.setDisable(true);
				lockinbtn.setDisable(true);
			}

			if (tileBtns[6].getStyle().contains("blue") && tileBtns[7].getStyle().contains("blue")
					&& tileBtns[8].getStyle().contains("blue")) {
				twobtnRoll.setDisable(true);
				twobtnRoll.setStyle("-fx-background-color: blue;");
				btnRoll.setDisable(false);
				btnRoll.setStyle("-fx-background-color: lightgray;");
			}
		});

		Scene scene = new Scene(vbox, 500, 200);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}