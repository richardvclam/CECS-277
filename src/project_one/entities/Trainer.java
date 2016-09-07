package project_one.entities;

import java.util.ArrayList;

import project_one.pokemons.Pokemon;

/**
 * Trainer class contains addition and modification of their Pokemons.
 * 
 * @author Richard
 *
 */
public abstract class Trainer extends Entity {

	/**
	 * Trainer's current pokemon.
	 */
	private Pokemon currentPokemon;
	/**
	 * Trainer's bag of Pokemons.
	 */
	private ArrayList<Pokemon> pokemons;
	
	/**
	 * Constructor
	 * @param name is the trainer's name
	 * @param hp is the trainer's hp
	 */
	public Trainer(String name, int hp) {
		super(name, hp);
		pokemons = new ArrayList<Pokemon>();
	}
	
	/**
	 * Returns the trainer's attack speech.
	 * @return the trainer's attack speech.
	 */
	public abstract String attackSpeech();
	
	/**
	 * Returns the trainer's win speech.
	 * @return the trainer's win speech
	 */
	public abstract String winSpeech();
	
	/**
	 * Returns the trainer's loss speech.
	 * @return the trainer's loss speech
	 */
	public abstract String lossSpeech();
	
	/**
	 * Returns the trainer's attack style.
	 * @return the trainer's attack style
	 */
	public abstract int chooseStyle();
	
	/**
	 * Returns the trainer's attack move.
	 * @param style is the attack style
	 * @return the trainer's attack move
	 */
	public abstract int chooseMove(int style);
	
	/**
	 * Adds a Pokemon to the trainer's bag.
	 * @param pokemon is the Pokemon to add to the trainer's bag
	 */
	public void addPokemon(Pokemon pokemon) {
		pokemons.add(pokemon);
	}
	
	/**
	 * Returns a specific Pokemon based on their position in the trainer's bag.
	 * @param pokenum the Pokemon's position
	 * @return a specific Pokemon based on their position in the trainer's bag
	 */
	public Pokemon getPokemon(int pokenum) {
		return pokemons.get(pokenum);
	}
	
	/**
	 * Returns the trainer's entire bag of Pokemons.
	 * @return the trainer's entire bag of Pokemons
	 */
	public ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}
	
	/**
	 * Displays the trainer's bag of Pokemons.
	 */
	public void displayPokemon() {		
		for (int i = 0; i < pokemons.size(); i++) {
			String tab = " ";
			Pokemon pokemon = pokemons.get(i);

			System.out.println(i+1 + ") " + pokemon.getName() + tab + " | LV " + pokemon.getLevel() + " | HP " + pokemon.getHp() + "/" + pokemon.getMaxHp() + " | EXP " + pokemon.getExp() + "/" + pokemon.getExpToLevel());
		}
	}
	
	/**
	 * Returns an String array of the trainer's Pokemons with their level, hp, and exp.
	 * @return a String array of the trainer's Pokemon
	 */
	public String[] listPokemon() {
		String[] result = new String[pokemons.size()];
		
		for (int i = 0; i < pokemons.size(); i++) {
			Pokemon pokemon = pokemons.get(i);
			result[i] = pokemon.getName() + " | LV " + pokemon.getLevel() + " | HP " + pokemon.getHp() + "/" + pokemon.getMaxHp() + " | EXP " + pokemon.getExp() + "/" + pokemon.getExpToLevel();
		}
		
		return result;
	}
	
	/**
	 * Returns the trainer's current active Pokemon
	 * @return the trainer's current active Pokemon
	 */
	public Pokemon getCurrentPokemon() {
		return currentPokemon;
	}
	
	/**
	 * Sets the trainer's current active Pokemon to {@code currentPokemon}.
	 * @param currentPokemon the new active Pokemon to set
	 */
	public void setCurrentPokemon(Pokemon currentPokemon) {
		this.currentPokemon = currentPokemon;
	}
	
	/**
	 * Returns a String format speech with the trainer's name and a sentence.
	 * @param speech the sentence
	 * @return a String format speech
	 */
	public String speak(String speech) {
		return super.getName() + ": " + speech;
	}
	
}
