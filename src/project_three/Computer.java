package project_three;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Computer implements Serializable {

	int humanWins, cpuWins, ties;
	HashMap<Pattern, Integer> patterns;
	Pattern currentPattern;
	
	public Computer() {
		currentPattern = new Pattern("");
		patterns = new HashMap<Pattern, Integer>();
		humanWins = 0;
		cpuWins = 0;
		ties = 0;
	}
	
	public void play(int playerMove) {
		String[] choices = {"Fire", "Water", "Grass"};
		storePattern(playerMove);
		int cpuMove = makePrediction();
		
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
	
	public int makePrediction() {
		String[] choices = {"Fire", "Water", "Grass"};
		if (patterns.containsKey(currentPattern)) {
			//int[] a = combinePattern(currentPattern).getCount();
			int[] a = currentPattern.getCount();
			int humanMove = -1;
			
			int largest = -1;
			ArrayList<Integer> largestIndex = new ArrayList<Integer>();
			
			for (int i = 0; i < a.length; i++) {
				if (a[i] > largest) {
					largest = a[i];
				}
			}
			
			for (int i = 0; i < a.length; i++) {
				if (a[i] == largest) {
					largestIndex.add(i);
				}
			}
			
			if (largestIndex.size() == 1) {
				humanMove = largestIndex.get(0);
			} else if (largestIndex.size() > 1) {
				humanMove = largestIndex.get((int) (Math.random() * 2));
			}
			
			System.out.println("Computer predicted you'll play " + choices[humanMove]);
			
			switch (humanMove) {
				case 0:
					return 2;
				case 1:
					return 3;
				case 2:
					return 1;
			}

		}
		return (int)(Math.random() * 3) + 1;
	}
	
	public Pattern combinePattern(Pattern pattern) {
		if (patterns.size() > 1) {
			for (Pattern p : patterns.keySet()) {
				if (!p.equals(pattern)) {
					return new Pattern(pattern.getPattern() + p.getPattern());
				}
			}
		}
		return pattern;
	}
	
	public void displayScores() {
		System.out.println();
		System.out.println("Human Wins: " + humanWins);
		System.out.println("Computer Wins: " + cpuWins);
		System.out.println("Ties: " + ties);
		System.out.println("Computer win percentage: " + ((double)cpuWins/(humanWins+cpuWins)));
	}
	
	public void storePattern(int response) {
		int patternLength = 4;
		String[] choices = {"F", "W", "G"};
		
		String pattern = currentPattern.getPattern();
		
		if (pattern.length() < patternLength) {
			pattern += choices[response - 1];
			currentPattern = new Pattern(pattern);
		} else {
			currentPattern = new Pattern(pattern.substring(1) + choices[response - 1]);
		}
		
		if (pattern.length() == patternLength) {
			if (patterns.containsKey(currentPattern)) {
				patterns.put(currentPattern, patterns.get(currentPattern) + 1);
			} else {
				patterns.put(currentPattern, 1);
			}
		}
	}
	
	public void resetScores() {
		humanWins = 0;
		cpuWins = 0;
		ties = 0;
	}
	
}
