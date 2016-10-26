package project_four;

import java.util.Scanner;

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

}
