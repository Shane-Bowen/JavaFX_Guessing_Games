package application;

import javafx.stage.*;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;

public class WinningAlertBox   {

	public static void display(String title, String message, int prize) {
		Stage window = new Stage();
		VBox layout = new VBox(10);
		
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(300);
		window.setMinHeight(100);

		Label label = new Label();
		label.setText(message);

		Label label1 = new Label();
		label1.setText("Please enter your first name");

		TextField Input = new TextField();

		Label label2 = new Label();
		label2.setText("Please enter your last name");

		TextField Input1 = new TextField();
		
		layout.getChildren().addAll(label, label1, Input, label2, Input1);
		
		//****************************************************************
		// if title equals Congratulations then we can add the person to
		// the winners list
		//****************************************************************
		
		if(title.equals("Congratulations"))
		{
				
		Button enterButton = new Button("Enter");
		
		layout.getChildren().add(enterButton);

		enterButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				String first = Input.getText();
				String last = Input1.getText();
				
				Winners w = new Winners(first, last, prize);
				Winners.add(w);
		
				//*************************************************
				// Adding the contents to a serializable file
				//*************************************************
				try {
			         FileOutputStream fileOut =
			         new FileOutputStream("C:/Users/Shane Bowen/workspace/OOPAssignment1/winners.ser");
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(w);
			         out.close();
			         fileOut.close();
			         System.out.print("\nSerialized data is saved in C:/Users/Shane Bowen/workspace/OOPAssignment1/winners.ser");
			      } catch (IOException i) {
			         i.printStackTrace();
			      }

				window.close();

			}      
		});
		
		}
		
		//**********************************************************
		// if title equals remove then we display the prompt which
		// asks the user what person they want to remove
		//**********************************************************
		
		else if(title.equals("Remove"))
		{
			Button removeButton = new Button("Remove");
			
			layout.getChildren().add(removeButton);

			removeButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
					String first = Input.getText();
					String last = Input1.getText();
					
					for(int i = 0; i < Winners.noOfWinners; i++)
					{
						if(first.equalsIgnoreCase(Winners.WinnersList.get(i).firstName) && last.equalsIgnoreCase(Winners.WinnersList.get(i).lastName))
						{
							AlertBox.display("Remove", Winners.WinnersList.get(i).firstName + " has been removed from list");
							Winners.WinnersList.remove(i);
							Winners.noOfWinners --;
							Main.setDisable();
							Main.setEnable();
						}
						
						else{
							AlertBox.display("Error", "Name not in list");
						}
					}	

					window.close();
				}      
			});
			
		}

		//Display window and wait for it to be closed before returning
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}

}