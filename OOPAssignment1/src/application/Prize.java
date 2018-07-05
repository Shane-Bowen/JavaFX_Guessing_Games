package application;

import com.cit.Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Prize extends Tab {
	
	//**********************************************************
	//	Declaring the global variables
	//**********************************************************

	int starPrize = GuessingGame.starPrize;
	ArrayList<Button> buttonList = new ArrayList<Button>();
	
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

	public Prize() {
				
		//**********************************************************
		// Creating the border, HBox and VBox layouts	
		//**********************************************************
		
		BorderPane border = new BorderPane();

		HBox hbox = new HBox(5);
		hbox.setPadding(new Insets(10));
		
		VBox vbox = new VBox(5);
		vbox.setPadding(new Insets(10));
		
		//**********************************************************
		// Adding the title to the border layout 	
		//**********************************************************

		Label Title = new Label("Welcome to the Prize Menu ");
		Title.setFont(new Font("Arial", 25));
		Title.setTextFill(Color.BLUE);
		BorderPane.setAlignment(Title, Pos.CENTER);
		border.setTop(Title);
		
		Label label = new Label("Please choose one:");
		label.setFont(new Font("Arial", 15));
		BorderPane.setAlignment(label, Pos.CENTER);
		
		//**********************************************************
		// Adding the Quit button to the border layout 	
		//**********************************************************

		Button Quit = new Button ("Quit");
		Quit.setOnAction(e -> closeProgram());
		BorderPane.setAlignment(Quit, Pos.BOTTOM_RIGHT);
		BorderPane.setMargin(Quit, new Insets(10));
		border.setBottom(Quit);
		
		//**********************************************************
		// Reading in the textfile, initializing variables for the
		// HashMap
		//**********************************************************

		File file = new File("Prize.txt");
		Tree theTree = new Tree();

		try {
			
			//**********************************************************
			// Getting the next line in the file and adding it to its 
			// corresponding hashmap variable
			//**********************************************************
			
			Scanner kb = new Scanner(file);
			String line = null;
			while(kb.hasNext()){
				line = kb.nextLine();
				String[] value = line.split(":");

				if(value[0].equals("4")) {
					theTree.addNode(4, value[1], value[2]);
				}
				else if(value[0].equals("5")) {
					theTree.addNode(5, value[1], value[2]);
				}

				else if(value[0].equals("6")) {
					theTree.addNode(6, value[1], value[2]);
				}

			}
			
			//**********************************************************
			// Depending on what star prize the user won, then it will 
			// display those prizes 
			//**********************************************************

			if(starPrize == 4){  
				for(int i = 0; i < 3; i++)
				{
					final int n = i;
					
					Button prize = new Button();
				
					prize.setText(theTree.getName(i, Tree.fourList));
					
					buttonList.add(prize);
					
					hbox.getChildren().add(prize);

					prize.setOnAction((ActionEvent) ->{
						AlertBox.display("You Won!", theTree.getPrize(n, Tree.fourList));
						
						for(int k = 0; k < 3; k++){
							buttonList.get(k).setDisable(true);
						}
					});
				}
			}

			else if(starPrize == 5){
				for(int i = 0; i < 3; i++)
				{
					final int n = i;

					Button prize = new Button();

					prize.setText(theTree.getName(i, Tree.fiveList));

					buttonList.add(prize);

					prize.setOnAction((ActionEvent) ->{
						AlertBox.display("You Won!", theTree.getPrize(n, Tree.fiveList));
						
						for(int k = 0; k < 3; k++){
							buttonList.get(k).setDisable(true);
						}
					});

					hbox.getChildren().add(prize);
				}
			}

			else if(starPrize == 6){
				for(int i = 0; i < 3; i++)
				{
					final int n = i;

					Button prize = new Button();

					prize.setText(theTree.getName(i, Tree.sixList));

					buttonList.add(prize);

					prize.setOnAction((ActionEvent) ->{
						AlertBox.display("You Won!", theTree.getPrize(n, Tree.sixList));
						
						for(int k = 0; k < 3; k++){
							buttonList.get(k).setDisable(true);
						}
					});

					hbox.getChildren().add(prize);
				}

				kb.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//**********************************************************
		// Adding the label and buttons to a VBox	
		//**********************************************************

		hbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(label, hbox);
		vbox.setAlignment(Pos.CENTER);
		border.setCenter(vbox);

		setText("Prizes");
		setContent(border);
	
	}	
}