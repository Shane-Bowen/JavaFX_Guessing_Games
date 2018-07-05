package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.Collections;

public class Lotto extends Tab {

	//**********************************************************
	//	Declaring the global variables
	//**********************************************************

	int noOfSuccess = 0;
	ArrayList<Integer> GuessNumbers = new ArrayList<Integer>();
	ArrayList<Button> buttonList = new ArrayList<Button>();
	int totalNumbers = 0;

	//**********************************************************
	//	Method that allows user to close down the program
	//**********************************************************

	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Title", "Are you Sure");
		if(answer)
			Platform.exit();
	}

	//**********************************************************
	// Class Constructor	
	//**********************************************************

	public Lotto () {

		//**********************************************************
		// Creating the border layout and adding the title to it	
		//**********************************************************

		BorderPane border = new BorderPane();
		setText("Lotto Cure");

		Label Title = new Label("Welcome to the National Lottery ");
		Title.setFont(new Font("Arial", 25));
		Title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(Title, Pos.CENTER);
		border.setTop(Title);

		VBox vbox = new VBox(5);
		vbox.setPadding(new Insets(10));

		//**********************************************************
		// The random number generator	
		//**********************************************************

		ArrayList<Integer> randomList = new ArrayList<Integer>();
		for (int i=1; i<50; i++) {
			randomList.add(new Integer(i));
		}

		Collections.shuffle(randomList);
		for (int i=0; i<6; i++){
			System.out.print(randomList.get(i) + " ");
		}

		//*******************************************************************
		// Creating the flowPane layout and adding the guessing buttons to it	
		//*******************************************************************

		FlowPane flowPane = new FlowPane();
		flowPane.setVgap(5);
		flowPane.setHgap(5);

		for(int i=1; i<50; i++){
			Button btnNumber = new Button();
			btnNumber.setText(String.valueOf(i));
			buttonList.add(btnNumber);
			btnNumber.setOnAction((ActionEvent)->{

				if(totalNumbers < 6){
					GuessNumbers.add(Integer.parseInt(btnNumber.getText()));
					btnNumber.setDisable(true);
					totalNumbers ++;
				}

				else if(totalNumbers >= 6){
					AlertBox.display("Maximum", "You have reached the limit!");
				}

			});
			flowPane.getChildren().add(btnNumber);
		}

		//**********************************************************
		// Adding the label and flowPane to a VBox and then 
		// adding it to the borderPane
		//**********************************************************

		Label label = new Label("Please choose six numbers: ");
		label.setFont(new Font("Arial", 15));

		flowPane.setAlignment(Pos.CENTER);
		flowPane.setPadding(new Insets(100));
		vbox.getChildren().addAll(label, flowPane);
		vbox.setAlignment(Pos.CENTER);
		border.setCenter(vbox);

		//***********************************************************
		// When the user has entered all his numbers he can submit it	
		//***********************************************************

		Button Submit = new Button("Submit");
		BorderPane.setAlignment(Submit, Pos.CENTER);

		Submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				if(totalNumbers == 6){

					for(int i = 0; i < 6; i++){
						for(int n = 0; n < 6; n++){
							if(GuessNumbers.get(i).equals(randomList.get(n))){
								noOfSuccess ++;
							}
						}
					}

					if(noOfSuccess >= 4){
						GuessingGame.starPrize = noOfSuccess;
						WinningAlertBox.display("Congratulations", "Congratulations you got " + noOfSuccess + " correct!", GuessingGame.starPrize);
						Main.setDisable();
						Main.setEnable();
					}

					else{
						AlertBox.display("Unlucky", "Unlucky you only got " + noOfSuccess + " correct!");
					}

				}

				else{
					AlertBox.display("Error", "You have to enter 6 numbers!");
				}

			}
		});

		//**********************************************************
		// Creating the new layout HBox and adding the Reset and
		// Quit button to it
		//**********************************************************

		Button Reset = new Button ("Reset");
		Reset.setOnAction(e ->{
			AlertBox.display("Reset", "You have reset the game!");
			noOfSuccess = 0;
			
			for(int n = 0; n < totalNumbers; n++)
			{
				GuessNumbers.remove(0);
			}
			
			totalNumbers = 0;

			for(int i = 0; i < 49; i++){
				buttonList.get(i).setDisable(false);
			}
		});

		Button Quit = new Button ("Quit");
		Quit.setOnAction(e -> closeProgram());

		HBox hbox = new HBox(5);
		hbox.getChildren().addAll(Submit, Reset, Quit);
		BorderPane.setMargin(hbox, new Insets(10));
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		border.setBottom(hbox);

		setContent(border);
	}
}