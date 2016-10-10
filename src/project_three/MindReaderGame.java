package project_three;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class MindReaderGame {
	
	/**
	 * Global save file.
	 */
	private static File saveFile = new File("fwg.dat");

	public static void main(String[] args) {
		boolean run = true;
		Computer opp = new Computer();
		int response;
		
		if (saveFile.exists()) {
			String[] menu = {"Beginner", "Veteran"};
			response = checkUserInput("Choose a difficulty:", menu);
		
			if (response == 2) {
				try {
					ObjectInputStream load = new ObjectInputStream(new FileInputStream(saveFile));
					opp = (Computer) load.readObject();
					opp.resetScores();
					load.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} 
			
		while(run) {
			response = checkUserInput();
			
			if (response == 4) {
				int quitResponse = checkYesNo("Do you want to save the game?");
				
				if (quitResponse == 1) {
					try {
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFile));
						out.writeObject(opp);
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				break;
			}
			opp.play(response);
			opp.displayScores();
		}
	}
	
	/**
	 * Displays a question with answer choices. Checks the user's input repeatedly until valid.
	 * @param question is the question to ask
	 * @param choices is the array of answer choices
	 * @return the selection choice
	 */
	public static int checkUserInput() {
		Scanner in = new Scanner(System.in);
		String question = "Pick a type:";
		String[] choices = {"Fire", "Water", "Grass", "Quit"};
		int response = -1;
		
		do {
			System.out.println("\n" + question);
			for (int i = 0; i < choices.length; i++) {
				System.out.println(i+1 + ") " + choices[i]);
			}
			if (!in.hasNextInt()) {
				System.out.println("Error: Please enter a valid number.");
				in.next();
			} else {
				response = in.nextInt();
				
				if (response <= 0 || response > choices.length) {
					System.out.println("Error: Please enter a valid selection.");
				} else {
					break;
				}
			}	
		} while (response <= 0 || response > choices.length);
		
		return response;
	}
	
	
	public static int checkYesNo(String question) {
		Scanner in = new Scanner(System.in);
		int response = -1;
		String[] yesNo = {"Yes", "No"};
		
		do {
			System.out.println("\n" + question);
			for (int i = 0; i < yesNo.length; i++) {
				System.out.println(i+1 + ") " + yesNo[i]);
			}
			if (!in.hasNextInt()) {
				System.out.println("Error: Please enter a valid number.");
				in.next();
			} else {
				response = in.nextInt();
				
				if (response <= 0 || response > yesNo.length) {
					System.out.println("Error: Please enter a valid selection.");
				} else {
					break;
				}
			}	
		} while (response <= 0 || response > yesNo.length);
		
		return response;
	}
	
	/**
	 * Displays a question with answer choices. Checks the user's input repeatedly until valid.
	 * @param question is the question to ask
	 * @param choices is the array of answer choices
	 * @return the selection choice
	 */
	public static int checkUserInput(String question, String[] choices) {
		Scanner in = new Scanner(System.in);
		int response = -1;
		
		do {
			System.out.println("\n" + question);
			for (int i = 0; i < choices.length; i++) {
				System.out.println(i+1 + ") " + choices[i]);
			}
			if (!in.hasNextInt()) {
				System.out.println("Error: Please enter a valid number.");
				in.next();
			} else {
				response = in.nextInt();
				
				if (response <= 0 || response > choices.length) {
					System.out.println("Error: Please enter a valid selection.");
				} else {
					break;
				}
			}	
		} while (response <= 0 || response > choices.length);
		
		return response;
	}
}
