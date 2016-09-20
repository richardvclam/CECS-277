package project_one;

import java.util.Scanner;

import project_one.entities.Player;

/**
 * Utility class for checking user inputs.
 * 
 * @author Richard Lam
 *
 */
public class Util {
	
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
	
	/**
	 * Displays a question. Checks the user's input for a positive integer repeatedly until valid.
	 * @param question is the question to ask
	 * @return the integer amount
	 */
	public static int checkPositiveInt(String question) {
		Scanner in = new Scanner(System.in);
		int response = -1;
		
		do {
			System.out.println("\n" + question);
			if (!in.hasNextInt()) {
				System.out.println("Error: Please enter a valid number.");
				in.next();
			} else {
				response = in.nextInt();
				
				if (response <= 0) {
					System.out.println("Error: Please enter a valid selection.");
				} else {
					break;
				}
			}
		} while (response <= 0);
		return response;
	}
	
	public static char checkDirection(Player player) {
		Scanner in = new Scanner(System.in);
		int response = -1;
		char c = ' ';
		String[] choices = {"North", "West", "East", "South", "Cancel"};
		
		do {
			c = ' ';
			System.out.println("\nWhere would you like to go?");
			System.out.println("Map: " + player.getCurrentMap().getName());
			player.getCurrentMap().displayMap(player.getLocation());
			System.out.println("Choose a direction:");
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
				} /*else {
					break;
				}*/
			}
			
			switch (response) {
				case 1:
					c = player.goNorth(player.getCurrentMap());
					break;
				case 2:
					c = player.goWest(player.getCurrentMap());
					break;
				case 3:
					c = player.goEast(player.getCurrentMap());
					break;
				case 4:
					c = player.goSouth(player.getCurrentMap());
					break;
			}
			
			if (c == '0') {
				response = -1;
				System.out.println("You cannot move in that direction.");
			}
		} while (response <= 0 || response > choices.length);
		
		return c;
	}

}
