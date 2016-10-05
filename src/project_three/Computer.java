package project_three;

import java.util.HashMap;

public class Computer {

	int humanWins, cpuWins, ties;
	HashMap<Pattern, Integer> patterns;
	
	public Computer() {
		patterns = new HashMap<Pattern, Integer>();
		humanWins = 0;
		cpuWins = 0;
		ties = 0;
	}
	
	public void play(int playerMove) {
		String[] choices = {"Fire", "Water", "Grass"};
		int cpuMove = makeMove();
		
		System.out.println("You played " + choices[playerMove - 1] + ".");
		System.out.println("The computer played " + choices[cpuMove - 1] + ".");
		
		if ((playerMove == 1 && cpuMove == 1) || (playerMove == 2 && cpuMove == 2) || (playerMove == 3 && cpuMove == 3)) {
			ties++;
			System.out.println("You tied with the computer.");
		} else if ((playerMove == 1 && cpuMove == 2) || (playerMove == 2 && cpuMove == 3) || (playerMove == 3 && cpuMove == 1)) {
			cpuWins++;
			System.out.println("You lost to the computer.");
		} else if ((playerMove == 1 && cpuMove == 3) || (playerMove == 2 && cpuMove == 1) || (playerMove == 3 && cpuMove == 2)) {
			humanWins++;
			System.out.println("You beat the computer!");
		}
	}
	
	public int makeMove() {
		return (int)(Math.random() * 3) + 1;
	}
	
	public void displayScores() {
		System.out.println();
		System.out.println("Human Wins: " + humanWins);
		System.out.println("Computer Wins: " + cpuWins);
		System.out.println("Ties: " + ties);
	}
	
}
