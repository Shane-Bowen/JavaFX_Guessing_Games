package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.application.Platform;
import javafx.event.ActionEvent;
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

public class Winners extends Tab implements java.io.Serializable {

	//**********************************************************
	//	Declaring the global variables
	//**********************************************************

	private static final long serialVersionUID = 1L;
	public String firstName;
	public String lastName;
	public int prize;
	
	//**********************************************************
	// We makte these variable transient as we don't want to 
	// implement these as serializable
	//**********************************************************

	transient static int noOfWinners = 0;
	transient static ArrayList<Winners> WinnersList = new ArrayList<Winners>();
	
	//*************************************************************
	//	Get the values of the winners and add then to an arraylist
	//*************************************************************

	public Winners(String first, String last, int prize)
	{		
		this.firstName = first;
		this.lastName = last;
		this.prize = prize;
	}

	public static void add(Winners w) 
	{
		WinnersList.add(w);
		noOfWinners ++;
	}

	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Title", "Are you Sure");
		if(answer)
			Platform.exit();
	}

	public String toString(){ 
		return firstName + " " + lastName + " " + prize + "\n";
	}
	
	//**********************************************************
	//	This orders the winners list by name
	//**********************************************************

	public transient Comparator<Winners> NameComparator = new Comparator<Winners>() {

		@Override
		public int compare(Winners w, Winners w1) {
			int lastCmp = w.lastName.toUpperCase().compareTo(w1.lastName.toUpperCase());
			return (lastCmp != 0 ? lastCmp : w.firstName.toUpperCase().compareTo(w1.firstName.toUpperCase()));	        

		}};
		
		//**********************************************************
		//	This orders the winners list by prize
		//**********************************************************

		public transient Comparator<Winners> PrizeComparator = new Comparator<Winners>() {

			public int compare(Winners w, Winners w1) {

				int prizeno1 = w.prize;
				int prizeno2 = w1.prize;

				//For ascending order
				return prizeno1-prizeno2;

			}};

			public Winners() {
				
				//**********************************************************
				// Adding all the contents to a border layout 	
				//**********************************************************
				
				setText("Winners");

				BorderPane bp = new BorderPane();
				VBox vb = new VBox(5);
				HBox hb = new HBox(5);

				Label title = new Label("Welcome to the Winners Menu ");
				title.setFont(new Font("Arial", 25));
				title.setTextFill(Color.BLUE);
				BorderPane.setAlignment(title, Pos.CENTER);
				bp.setTop(title);

				Label lbl = new Label("\nList of Winners ordered by name");
				lbl.setFont(new Font("Arial", 15));
				vb.getChildren().add(lbl);
				
				//**********************************************************
				// Displays the winners list sorted by name
				//**********************************************************

				Collections.sort(WinnersList, NameComparator);

				String names = WinnersList.toString()
						.replace(",", "")
						.replace("[", "")
						.replace("]", "");

				Label label = new Label(names);
				vb.getChildren().add(label);

				Label lbl1 = new Label("\nList of Winners ordered by prize");
				lbl1.setFont(new Font("Arial", 15));
				vb.getChildren().add(lbl1);
				
				//**********************************************************
				// Displays the winners list by prize
				//**********************************************************

				Collections.sort(WinnersList, PrizeComparator);
				
				String names1 = WinnersList.toString()
						.replace(",", "")
						.replace("[", "")
						.replace("]", "");
				Label label1 = new Label(names1);
				vb.getChildren().add(label1);
				BorderPane.setMargin(vb, new Insets(10));
				bp.setLeft(vb);

				Button Remove = new Button ("Remove");
				Remove.setOnAction((ActionEvent event) ->{

					WinningAlertBox.display("Remove", "Enter details of person you want to remove!", 1);
				});

				Button Quit = new Button ("Quit");
				Quit.setOnAction(e -> closeProgram());
				hb.getChildren().addAll(Remove, Quit);
				BorderPane.setMargin(hb, new Insets(10));
				hb.setAlignment(Pos.BOTTOM_RIGHT);
				bp.setBottom(hb);

				setContent(bp);
			}
			
			//**********************************************************
			// Desirialize the contents in the file and display them
			//**********************************************************

			public static void main(String [] args) {
				Winners w = null;

				try {
					FileInputStream fileIn = new FileInputStream("C:/Users/Shane Bowen/workspace/OOPAssignment1/winners.ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					w = (Winners) in.readObject();
					in.close();
					fileIn.close();
					
					System.out.println("Deserialized Winners...");

					System.out.println("First Name: " + w.firstName);
					System.out.println("Last Name: " + w.lastName);
					System.out.println("Prize: " + w.prize);
					
				} catch (IOException i) {
					i.printStackTrace();
					return;
				} catch (ClassNotFoundException c) {
					System.out.println("Winners class not found");
					c.printStackTrace();
					return;
				}


			}

}