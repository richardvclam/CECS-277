package project_one.entities;

import java.util.ArrayList;

import project_one.pokemons.Pokemon;

public abstract class Trainer extends Entity {

	private Pokemon currentPokemon;
	private ArrayList<Pokemon> pokemons;
	
	public Trainer(String name, int hp) {
		super(name, hp);
		pokemons = new ArrayList<Pokemon>();
	}
	
	public abstract void attackSpeech();
	
	public abstract void winSpeech();
	
	public abstract void lossSpeech();
	
	public abstract int chooseStyle();
	
	public abstract int chooseMove(int style);
	
	public void displayPokemon() {
		
	}
	
	public void addPokemon(Pokemon p) {
		pokemons.add(p);
	}
	
	public Pokemon getPokemon(int pokenum) {
		return pokemons.get(pokenum);
	}
	
	public void listPokemons() {
		String[] result = new String[pokemons.size()];
		for (int i = 0; i < pokemons.size(); i++) {
			result[i] = pokemons.get(i).getName();
		}
		
		//return result;
		
		for (int i = 0; i < pokemons.size(); i++) {
			String tab = " ";
			Pokemon pokemon = pokemons.get(i);

			System.out.println(i+1 + ") " + pokemon.getName() + tab + " | LV " + pokemon.getLevel() + " | HP " + pokemon.getHp() + "/" + pokemon.getMaxHp() + " | EXP " + pokemon.getExp() + "/" + pokemon.getExpToLevel());
		}
	}
	
	public Pokemon getCurrentPokemon() {
		return currentPokemon;
	}
	
	public void setCurrentPokemon(Pokemon currentPokemon) {
		this.currentPokemon = currentPokemon;
	}
	
}
