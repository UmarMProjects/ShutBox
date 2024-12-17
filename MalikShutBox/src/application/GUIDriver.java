package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIDriver extends Application {

	   Die d1 = new Die(); // Ensure Die class is implemented
	   int count2 = 0;
	 
	    @Override
	    public void start(Stage stage) throws Exception {
	        VBox vbox = new VBox(10);

	       
	        Label title = new Label("Shut The Box");
	        vbox.getChildren().add(title);

	        HBox tileBox = new HBox(10);

	        Button[] tileBtns = new Button[9];
	        Tile[] tiles = new Tile[9];

	        for (int i = 0; i < tileBtns.length; i++) {
	            tileBtns[i] = new Button(String.valueOf(i + 1));
	            tiles[i] = new Tile(i + 1);
	            tileBtns[i].setStyle("-fx-background-color: lightgray;");

	            
	            tileBtns[i].setOnAction(e -> {
	                Button clickedButton = (Button) e.getSource();
	                String currentStyle = clickedButton.getStyle();
	                if (currentStyle.contains("lightgray")) {
	                    clickedButton.setStyle("-fx-background-color: red;");
	                } else if (currentStyle.contains("red")) {
	                    clickedButton.setStyle("-fx-background-color: lightgray;");
	                }
	            });

	            tileBox.getChildren().add(tileBtns[i]);
	        }

	        tileBox.setAlignment(Pos.CENTER);
	        vbox.getChildren().add(tileBox);

	        Button btnRoll = new Button("ROLL DICE");
	        Button twobtnRoll = new Button("ROLL Two DICE");
	        Button lockinbtn = new Button("Lock In");
	        Button donebtn = new Button("Done");

	        Label result1 = new Label("Result: ");
	        Label resultDice = new Label("0");

	        HBox rollBox = new HBox(20); 
	        rollBox.setAlignment(Pos.CENTER);
	        rollBox.getChildren().addAll(btnRoll, twobtnRoll, lockinbtn, donebtn, result1, resultDice);

	        vbox.getChildren().add(rollBox);
	        vbox.setAlignment(Pos.CENTER);
	        
	        btnRoll.setDisable(true);
         	btnRoll.setStyle("-fx-background-color: blue;"); 

	        btnRoll.setOnAction(e -> {
	            resultDice.setText(String.valueOf(d1.roll()));
	            btnRoll.setDisable(true);
	        });

	        twobtnRoll.setOnAction(e -> {
	        	
	        		int diceTotal = d1.roll() + d1.roll();
	        		resultDice.setText(String.valueOf(diceTotal));
	        		twobtnRoll.setDisable(true);
	        });
	        
	        donebtn.setOnAction(e -> {
	        	int score = 0; 
		        
	        	 for (int i = 0; i < tileBtns.length; i++) {
	        		 if (tileBtns[i].getStyle().contains("lightgray")) {
	        			 score += Integer.valueOf(tileBtns[i].getText());
	        		 }
	        		  
	        	 }
	        	 
	        	 resultDice.setText(String.valueOf(score)); 
	        	 result1.setText("Score"); 
	        	 twobtnRoll.setDisable(true);
	        	 btnRoll.setDisable(true);
	        	 lockinbtn.setDisable(true);
	        	 twobtnRoll.setStyle("-fx-background-color: blue;");
	        	 btnRoll.setStyle("-fx-background-color: blue;"); 
	        	 lockinbtn.setStyle("-fx-background-color: blue;"); 
	        	 
	        	 
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
	        	 }
	        
	         
             if (tileBtns[6].getStyle().contains("blue") && tileBtns[7].getStyle().contains("blue") && tileBtns[8].getStyle().contains("blue")) {
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