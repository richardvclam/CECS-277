package project_one;

import project_one.entities.Opponent;
import project_one.pokemons.Pokemon;

/**
 * This class generates a random opponent for the player to battle against.
 * 
 * @author Richard Lam
 */
public class OpponentMaker {
	
	/**
	 * Maximum number of Pokemons a Team Rocket opponent can generate.
	 */
	private static final int MAX_POKE_TEAM_ROCKET = 2;
	/**
	 * Maximum number of Pokemons a normal opponent can generate.
	 */
	private static final int MAX_POKE_OPP = 2;
	/**
	 * Chance of a Team Rocket opponent being generated.
	 */
	private static final double TEAM_ROCKET_CHANCE = .30;
	/**
	 * Array of possible opponent names.
	 */
	private static final String[] OPPONENT_NAMES = {"Gary", "Blake", "Joy", "Misty"};
	
	/**
	 * Generates a random opponent with a set of Pokemons. The opponent can be either a rival trainer or a malicious Team Rocket.
	 * @return a random opponent
	 */
	public static Opponent makeRandomOpponent() {
		double randomOpp = Math.random(); // Generates a random opponent
		Opponent opponent;
		
		if (randomOpp >= TEAM_ROCKET_CHANCE) { 
			int randomName = (int) (Math.random() * OPPONENT_NAMES.length); // Generates a random opponent name
			
			opponent = new Opponent(OPPONENT_NAMES[randomName], 100);
		} else {
			opponent = new Opponent("Team Rocket", 100);
		}
		
		// Adds a random amount of Pokemons to the opponent
		for (int i = 0; i < (randomOpp >= TEAM_ROCKET_CHANCE ? MAX_POKE_OPP : MAX_POKE_TEAM_ROCKET); i++) { 
			addRandomPokemon(opponent);
		}
		
		return opponent;
	}
	
	/**
	 * Adds and sets a random Pokemon for the opponent.
	 * @param opponent the opponent to add a random Pokemon to
	 */
	private static void addRandomPokemon(Opponent opponent) {
		Pokemon pokemon = PokemonMaker.makeWildPokemon();
		
		opponent.addPokemon(pokemon);
		opponent.setCurrentPokemon(pokemon);
	}

}
