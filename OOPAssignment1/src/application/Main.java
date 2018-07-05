/*
 * Name: Shane Bowen
 * ID: R00149085
 * Class: SD2-A 
 */

package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	static Winners winners = new Winners();
	static Prize prize = new Prize();

	//*************************************************
	//	Declaring TabPane as a global variable
	//*************************************************	

	static TabPane tabPane = new TabPane();

	public static void main(String[] args) {
		launch(args);
	}

	//*****************************************************************
	//	Creating the main BorderPane and adding tabs to it.
	//****************************************************************

	public void start(Stage primaryStage) throws Exception {

		try {
			BorderPane mainPane = new BorderPane();
			Group root = new Group();
			Scene scene = new Scene(root,700,500);

			GuessingGame guessingGame = new GuessingGame();
			tabPane.getTabs().add (guessingGame);

			Lotto lotto = new Lotto();
			tabPane.getTabs().add(lotto);
		
			mainPane.setCenter(tabPane);

			mainPane.prefHeightProperty().bind(scene.heightProperty());
			mainPane.prefWidthProperty().bind(scene.widthProperty());

			root.getChildren().add(mainPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//*********************************************************************	
	//	Method to enable the prize tab and winners tab when a prize is won
	//*********************************************************************	

	public static void setEnable(){
		
		winners = new Winners();
		prize = new Prize();
				
		tabPane.getTabs().add(prize);
		tabPane.getTabs().add(winners);
	}
	
	public static void setDisable(){
		
		tabPane.getTabs().remove(prize);
		tabPane.getTabs().remove(winners);
	}
}	