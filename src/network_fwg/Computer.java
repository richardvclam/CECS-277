package network_fwg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Computer class that holds game values such as patterns, current pattern, and number of wins.
 * The computer class also has methods for predicting and storing patterns.
 * @author Richard Lam
 */
public class Computer implements Serializable {
	
	/**
	 * Number of times the human has won.
	 */
	int humanWins;
	/**
	 * Number of times the computer has won.
	 */
	int cpuWins;
	/**
	 * Number of times the human and computer tied.
	 */
	int ties;
	/**
	 * HashMap of the different patterns with a value indicating the number of times the pattern has occurred.
	 */
	HashMap<Pattern, Integer> patterns;
	/**
	 * The most recent pattern from the human.
	 */
	Pattern currentPattern;
	
	/**
	 * Constructor
	 */
	public Computer() {
		currentPattern = new Pattern("");
		patterns = new HashMap<Pattern, Integer>();
		humanWins = 0;
		cpuWins = 0;
		ties = 0;
	}
	
	/**
	 * Handles the event logic for the choices played by the human and computer. This method
	 * stores the current pattern, makes a prediction, and outputs the consequence of the play.
	 * @param playerMove the choice played by the human
	 * @param cpuMove the choice predicted by the computer
	 */
	public static void play(int playerMove, int cpuMove) {
		String[] choices = {"Fire", "Water", "Grass"};
		
		System.out.println("You played " + choices[playerMove - 1] + ".");
		System.out.println("The computer played " + choices[cpuMove - 1] + ".");
		
		if ((playerMove == 1 && cpuMove == 1) || (playerMove == 2 && cpuMove == 2) || (playerMove == 3 && cpuMove == 3)) {
			//ties++;
			System.out.println("You tied with the computer.");
		} else if ((playerMove == 1 && cpuMove == 2) || (playerMove == 2 && cpuMove == 3) || (playerMove == 3 && cpuMove == 1)) {
			//cpuWins++;
			System.out.println("You lost to the computer.");
		} else if ((playerMove == 1 && cpuMove == 3) || (playerMove == 2 && cpuMove == 1) || (playerMove == 3 && cpuMove == 2)) {
			//humanWins++;
			System.out.println("You beat the computer!");
		}
	}
	
	/**
	 * Makes a prediction based on the current pattern played by the human. If the computer
	 * cannot make a feasible prediction, it will randomly play any of the three choices.
	 * @return an integer value for Fire, Water, or Grass
	 */
	public int makePrediction() {
		String[] choices = {"Fire", "Water", "Grass"};
		if (currentPattern.getPattern().length() == 4) {
			int[] count = new int[3];
			Pattern F = new Pattern(currentPattern.getPattern().substring(1) + "F");
			Pattern W = new Pattern(currentPattern.getPattern().substring(1) + "W");
			Pattern G = new Pattern(currentPattern.getPattern().substring(1) + "G");
			
			if (patterns.get(F) != null) {
				count[0] = patterns.get(F);
			}
			if (patterns.get(W) != null) {
				count[1] = patterns.get(W);
			}
			if (patterns.get(G) != null) {
				count[2] = patterns.get(G);
			}
			
			int largest = -1;
			int humanMove = -1;
			ArrayList<Integer> largestIndex = new ArrayList<Integer>();
			
			for (int i : count) {
				if (i > largest) {
					largest = i;
				}
			}
			
			for (int i = 0; i < count.length; i++) {
				if (count[i] == largest) {
					largestIndex.add(i);
				}
			}
			
			if (largestIndex.size() == 1) {
				humanMove = largestIndex.get(0);
			} else if (largestIndex.size() > 1) {
				humanMove = largestIndex.get((int) (Math.random() * 2));
			}
			
			if (humanMove != -1) {
				//System.out.println("Computer predicted you'll play " + choices[humanMove]);
				
				switch (humanMove) {
					case 0:
						return 2;
					case 1:
						return 3;
					case 2:
						return 1;
				}
			} else {
				return (int)(Math.random() * 3) + 1;
			}
		}
		
		return (int)(Math.random() * 3) + 1;
	}
	
	/**
	 * Combines other patterns with the current pattern.
	 * @param pattern the current pattern
	 * @return a combination of the current pattern and another pattern
	 */
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
	
	/**
	 * Prints the current score between the human, computer, ties, and computer win percentage.
	 */
	public void displayScores() {
		System.out.println();
		System.out.println("Human Wins: " + humanWins);
		System.out.println("Computer Wins: " + cpuWins);
		System.out.println("Ties: " + ties);
		System.out.println("Computer win percentage: " + ((double)cpuWins/(humanWins+cpuWins)));
	}
	
	/**
	 * Stores the current pattern into the HashMap patterns.
	 * @param response the player choice
	 */
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
	
	/**
	 * Resets the scores back to zero.
	 */
	public void resetScores() {
		humanWins = 0;
		cpuWins = 0;
		ties = 0;
	}
	
}
