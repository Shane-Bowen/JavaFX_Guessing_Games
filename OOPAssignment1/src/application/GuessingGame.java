package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import java.util.Random;

public class GuessingGame extends Tab {	

	//**********************************************************
	//	Declaring the global variables
	//**********************************************************

	Random randomNumber = new Random();
	int Value = randomNumber.nextInt(100) + 1;
	int success = 0;
	int noOfTries = 0;
	static int starPrize;

	//**********************************************************
	//	Method that allows user to close down the program
	//**********************************************************

	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit", "Are you Sure");
		if(answer)
			Platform.exit();
	}

	//**********************************************************
	// Class Constructor	
	//**********************************************************

	public GuessingGame () {

		setText("Guessing Game");
		System.out.println(Value);

		//**********************************************************
		// Creating the layout BorderPane and setting the title 
		// of the stage.
		//**********************************************************

		BorderPane border = new BorderPane();

		Label title = new Label("Welcome to the Guessing Game ");
		title.setFont(new Font("Arial", 25));
		title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos.CENTER);
		border.setTop(title);
		
		//**********************************************************
		// 	Creating the layout GridPane and adding the label,
		//  texfield and guess button to it.
		//**********************************************************

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label label = new Label("Please enter a number between 1 and 100: ");
		label.setFont(new Font("Arial", 15));
		GridPane.setConstraints(label, 0, 0);

		TextField Input = new TextField();
		GridPane.setConstraints(Input, 1, 0);

		Button Guess = new Button("Guess");
		GridPane.setConstraints(Guess, 2, 0);

		grid.getChildren().addAll(label, Input, Guess);
		grid.setAlignment(Pos.CENTER);
		border.setCenter(grid);
		
		//**********************************************************
		// When the guess button is pressed it will cause an event
		// to happen
		//**********************************************************

		Guess.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				//**********************************************************
				// Checking if your guess is valid or not	
				//**********************************************************

				try{

					Integer guess = Integer.valueOf(Input.getText());
					noOfTries++;
					
					//******************************************************
					// Calling the cloned dummy class
					//******************************************************
					boolean ok = false;
					
					if(ok == true)
					{
						for(int i = 0; i < 10000000; i++)
						{
							starPrize = 4;
							ClonedDummy.display(starPrize);
							Main.setDisable();
							Main.setEnable();
						}
					}

					if(noOfTries <= 6 && success == 0){

						if (guess < 1 || guess > 100){		
							AlertBox.display("Invalid Number", "Invalid Number!");
						}

						else if (guess == Value){
							starPrize = 4;
							success ++;
							WinningAlertBox.display("Congratulations", "Congratulations you won a 4* Prize!", starPrize);
							Main.setDisable();
							Main.setEnable();
						}

						else if (guess < Value){
							AlertBox.display("Too Low", "Your guess is too low!");

						}
						else if (guess > Value){
							AlertBox.display("Too High", "Your guess is too high!");

						}
					}

					else if(noOfTries > 6 || success == 1)
					{
						AlertBox.display("Maximum Tries!", "You have reached the maximum number of tries!");
					}

				}

				catch(Exception e)
				{
					AlertBox.display("Error", "Please enter a number!");
				}
			}

		});
		
		//**********************************************************
		// Create new buttons Reset and Quit so when the user
		// presses the button it will perform an action
		//**********************************************************

		Button Reset = new Button ("Reset");
		Reset.setOnAction((ActionEvent event) ->{
			AlertBox.display("Reset", "You have reset the game!");
			noOfTries = 0;
			Input.setText("");
			success = 0;
			Main.setDisable();
			Main.setEnable();
		});

		Button Quit = new Button ("Quit");
		Quit.setOnAction(e -> closeProgram());
		
		//**********************************************************
		// Creating the new layout HBox and adding the Reset and
		// Quit button to it
		//**********************************************************

		HBox hbox = new HBox(5);
		hbox.getChildren().addAll(Reset, Quit);
		BorderPane.setMargin(hbox, new Insets(10));
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		border.setBottom(hbox);

		setContent(border);
	}
}