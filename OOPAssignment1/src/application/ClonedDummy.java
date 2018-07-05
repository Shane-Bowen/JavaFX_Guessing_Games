package application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClonedDummy   {
	
	//*********************************************
	// Create a bunch of dummy objects
	//*********************************************

	public static void display(int prize) {

		String first;
		String last;

		first = "ZZZ";
		last = "ZZZ";
		
		Winners w = new Winners(first, last, prize);
		Winners.add(w);

		try {
			FileOutputStream fileOut =
					new FileOutputStream("C:/Users/Shane Bowen/workspace/OOPAssignment1/winners.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(w);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in C:/Users/Shane Bowen/workspace/OOPAssignment1/winners.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}