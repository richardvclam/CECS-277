package project_three;

import java.util.Scanner;

public class MindReaderGame {

	public static void main(String[] args) {
		boolean run = true;
		Computer opp = new Computer();
			
		while(run) {
			int response = checkUserInput();
			
			if (response == 4) {
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
}
