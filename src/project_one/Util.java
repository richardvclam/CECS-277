package project_one;

import java.util.Scanner;

public class Util {
	
	public static int checkUserInput(String[] question) {
		Scanner in = new Scanner(System.in);
		int response = -1;
		
		do {
			System.out.println("\n" + question[0]);
			for (int i = 1; i < question.length; i++) {
				System.out.println(i + ") " + question[i]);
			}
			if (!in.hasNextInt()) {
				System.out.println("Error: Please enter a valid number.");
				in.next();
			} else {
				response = in.nextInt();
				
				if (response < 0 || response > question.length) {
					System.out.println("Error: Please enter a valid selection.");
				} else {
					break;
				}
			}	
		} while (response < 0 || response > question.length);
		
		return response;
	}

}
