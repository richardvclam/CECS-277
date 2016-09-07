/**
 * Each method in the PokemonMaker class generates either a starter Pokemon, a random Pokemon, or a specific type Pokemon.
 * 
 * @author Richard Lam
 */
package project_one;

import project_one.pokemons.*;

public class PokemonMaker {
	
	/**
	 * Randomly generates a wild Pokemon between the level 1 or 2 with HP between (50 to 100) * level.
	 * @return a randomly generated wild Pokemon
	 */
	public static Pokemon makeWildPokemon() {
		int randomPokeNum = (int) (Math.random()*8); // Random generates a number to determine the Pokemon.
		int randomPokeLv = (int) (Math.random()*2) + 1; // Randomly generates a Pokemon level between 1 or 2.
		int randomPokeHp = (int) (Math.random()*51) + 50; // Randomly generates a Pokemon health between 50 to 100.
		
		switch(randomPokeNum) {
			case 0:
				return new Charmander("Charmander", randomPokeHp * randomPokeLv, randomPokeLv);
			case 1:
				return new Pikachu("Pikachu", randomPokeHp * randomPokeLv, randomPokeLv);
			case 2:
				return new Ponyta("Ponyta", randomPokeHp * randomPokeLv, randomPokeLv);
			case 3:
				return new Squirtle("Squirtle", randomPokeHp * randomPokeLv, randomPokeLv);
			case 4:
				return new Staryu("Staryu", randomPokeHp * randomPokeLv, randomPokeLv);
			case 5:
				return new Bulbasaur("Bulbasaur", randomPokeHp * randomPokeLv, randomPokeLv);
			case 6:
				return new Oddish("Oddish", randomPokeHp * randomPokeLv, randomPokeLv);
			case 7:
				return new Zapdos("Zapdos", randomPokeHp * randomPokeLv, randomPokeLv);
			default:
				return null;
		}
	}
	
	/**
	 * Randomly generates a wild Pokemon of a specific type between level 1 or 2 with HP between (50 to 100) * level.
	 * @param type is the type value of the Pokemon
	 * @return a randomly generated specific type Pokemon
	 */
	public static Pokemon makeTypePokemon(int type) {
		int randomPokeNum = (int) (Math.random()*2); // Random generates a number to determine the Pokemon.
		int randomPokeLv = (int) (Math.random()*2) + 1; // Randomly generates a Pokemon level between 1 or 2.
		int randomPokeHp = (int) (Math.random()*51) + 50; // Randomly generates a Pokemon health between 50 to 100.
		
		switch(type) {
			case 0: // Fire
				if (randomPokeNum == 0) {
					return new Charmander("Charmander", randomPokeHp * randomPokeLv, randomPokeLv);
				} else {
					return new Ponyta("Ponyta", randomPokeHp * randomPokeLv, randomPokeLv);
				}
			case 1: // Water
				if (randomPokeNum == 0) {
					return new Squirtle("Squirtle", randomPokeHp * randomPokeLv, randomPokeLv);
				} else {
					return new Staryu("Staryu", randomPokeHp * randomPokeLv, randomPokeLv);
				}
			case 2: // Grass
				if (randomPokeNum == 0) {
					return new Bulbasaur("Bulbasaur", randomPokeHp * randomPokeLv, randomPokeLv);
				} else {
					return new Oddish("Oddish", randomPokeHp * randomPokeLv, randomPokeLv);
				}
			case 3: // Electric
				if (randomPokeNum == 0) {
					return new Pikachu("Pikachu", randomPokeHp * randomPokeLv, randomPokeLv);
				} else {
					return new Zapdos("Zapdos", randomPokeHp * randomPokeLv, randomPokeLv);
				}
			default:
				return null;
		}
	}
	
	/**
	 * Returns the starter Pokemon of the player's choice. The user can choose between Charmander or Pikachu.
	 * @return a starter Pokemon
	 */
	public static Pokemon makeStarterPokemon() {
		String[] starterMenu = {"Charmander", "Pikachu"};
		int starterNum = Util.checkUserInput("Choose your starter pokemon:", starterMenu);
		switch (starterNum) {
			case 1: 
				System.out.println("You have chosen Charmander!");
				return new Charmander("Charmander", 100, 1);
			case 2:
				System.out.println("You have chosen Pikachu!");
				return new Pikachu("Pikachu", 100, 1);
			default:
				return null;
		}
	}

}
