package project_one;

import java.util.Scanner;

import project_one.entities.Player;
import project_one.pokemons.*;

public class Main {

	public static void main(String[] args) {
		boolean run = true;
		int response, starterNum = 0;
		Scanner in = new Scanner(System.in);
		Player player;
		
		System.out.println("What is your trainer's name?");
		player = new Player(in.nextLine(), 100);
		
		String[] starterMenu = {"Choose your starter pokemon:",
								"Charmander", "Pikachu"};
		starterNum = Util.checkUserInput(starterMenu);
		Pokemon newPokemon = null;
		switch (starterNum) {
			case 1: 
				System.out.println("You have chosen Charmander!");
				newPokemon = new Charmander("Charmander", 100, 1);
				break;
			case 2:
				System.out.println("You have chosen Pikachu!");
				newPokemon = new Pikachu("Pikachu", 100, 1);
				break;
		}
		
		player.addPokemon(newPokemon);
		player.setCurrentPokemon(newPokemon);
		namePokemon(player.getCurrentPokemon());
		
		do {
			String[] menu = {"What would you like to do?",
							"Walk Around", "Heal Pokemon at PokeCenter", "Shop", "Display Pokemon", "Quit Game"};
			response = Util.checkUserInput(menu);
			
			switch(response) {
				case 1:
					System.out.println("Let's walk!");
					encounterWildPokemon(player);
					break;
				case 2:
					
					break;
				case 3:
					break;
				case 4:
					player.listPokemons();
					break;
				case 5:
					run = false;
					break;
			}
			
			if (player.getCurrentPokemon().getHp() < 0) {
				System.out.println("Game over.");
				run = false;
			}
			
		} while (run);
		
		in.close();
	}
	
	/*
	private static void displayPokemon(Player player) {
		String[] pokemons = player.listPokemons();
		for (int i = 0; i < pokemons.length; i++) {
			System.out.println(i+1 + ") " + pokemons[i].g);
		}
	}
	*/
	
	/**
	 * Handles the fight, heal, or run menu and user inputs.
	 * @param player is your trainer
	 */
	private static void encounterWildPokemon(Player player) {
		boolean fight = true;
		int response;
		Pokemon wildPokemon = generateRandomPokemon();
		
		System.out.println("A wild " + wildPokemon.getName() + " has appeared!");
		System.out.println(player.getName() + ": " + player.getCurrentPokemon().getName() + ", I choose you!");
		
		do {
			String[] menu = {"What would you like to do?",
							"Fight", "Heal", "Throw Pokeball", "Switch Pokemon", "Run Away"};
			response = Util.checkUserInput(menu);
			
			switch (response) {
				case 1:
					fightPokemon(player.getCurrentPokemon(), wildPokemon);
					break;
				case 2:
					healPokemon(player.getCurrentPokemon());
					break;
				case 3:
					fight = catchPokemon(player, wildPokemon);
					break;
				case 4:
					System.out.println("You've chosen to switch your Pokemon!");
					break;
				case 5:
					System.out.println("You've chosen to run away.");
					fight = false;
					break;	
			}
			if (wildPokemon.getHp() > 0 && player.getCurrentPokemon().getHp() > 0 && fight) {
				wildPokemonAttacks(player.getCurrentPokemon(), wildPokemon);
			}
		} while (wildPokemon.getHp() > 0 && player.getCurrentPokemon().getHp() > 0 && fight);
	}
	
	private static boolean catchPokemon(Player player, Pokemon wildPokemon) {
		System.out.println("You threw a pokeball!");
		double rand = Math.random();
		if (rand >= .7) {
			System.out.println("You've successfully caught " + wildPokemon.getName() + "!");
			namePokemon(wildPokemon);
			player.addPokemon(wildPokemon);
			
			gainExp(player.getCurrentPokemon(), wildPokemon);
			return false;
		} else {
			System.out.println("The wild " + wildPokemon.getName() + " broke free.");
		}
		return true;
	}
	
	private static void gainExp(Pokemon pokemon, Pokemon wildPokemon) {
		int expGained = wildPokemon.getExp();
		System.out.println(pokemon.getName() + " gained " + expGained + " exp!");
		pokemon.gainExp(expGained);
		
		if (pokemon.getExp() >= pokemon.getExpToLevel()) {
			levelPokemon(pokemon);
		}
	}
	
	private static void levelPokemon(Pokemon pokemon) {
		do {
			int expDiff = pokemon.getExp() - pokemon.getExpToLevel();
			pokemon.levelUp();
			System.out.println(pokemon.getName() + " grew to Lv. " + pokemon.getLevel());
			if (expDiff > 0) {
				pokemon.gainExp(expDiff);
			}
		} while (pokemon.getExp() >= pokemon.getExpToLevel());
	}
	
	private static void namePokemon(Pokemon pokemon) {
		Scanner in = new Scanner(System.in);
		System.out.println("What would you like to name your Pokemon?");
		String name = in.nextLine();
		pokemon.setName(name);
	}
	
	/**
	 * Randomly generates a wild Pokemon between the level 1 or 2 with a HP between (50 to 100) * level.
	 * @return a randomly generated wild Pokemon
	 */
	private static Pokemon generateRandomPokemon() {
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
	 * Handles and displays the fight menu, attacks, and damages between your Pokemon and the wild Pokemon.
	 * @param yourPokemon is your trainer's Pokemon
	 * @param wildPokemon is the wild Pokemon
	 */
	private static void fightPokemon(Pokemon yourPokemon, Pokemon wildPokemon) {
		int damage = 0;
		String[] menu = {"Choose an attack type:",
						"Basic Attack", "Special Attack"};
		int response = Util.checkUserInput(menu);
		switch (response) {
			case 1:
				response = Util.checkUserInput(yourPokemon.displayBasicMenu());
				damage = yourPokemon.basicFight(response);
				break;
			case 2:
				response = Util.checkUserInput(yourPokemon.displaySpecialMenu());
				damage = yourPokemon.specialFight(response);
				break;
		}
		
		if (damage > 0) {
			wildPokemon.loseHp(damage);
			System.out.println(yourPokemon.getName() + " did " + damage + " damage to " + wildPokemon.getName() + ".");
		}
		
		if (wildPokemon.getHp() < 0) {
			System.out.println("The wild " + wildPokemon.getName() + " has fainted!");
			gainExp(yourPokemon, wildPokemon);
		} else {
			System.out.println("The wild " + wildPokemon.getName() + " has " + wildPokemon.getHp() + "/" + wildPokemon.getMaxHp() + " HP left.");
		}
	}
	
	
	
	/**
	 * Heals your Pokemon to MAX_HEALTH.
	 * @param pokemon the Pokemon to be healed
	 */
	private static void healPokemon(Pokemon pokemon) {
		if (pokemon.getHp() < pokemon.getMaxHp()) {
			final int MAX_HEAL = 25;
			int heal = 0;
			if (pokemon.getMaxHp() - pokemon.getHp() > MAX_HEAL) {
				heal = MAX_HEAL;
			} else {
				heal = pokemon.getMaxHp() - pokemon.getHp();
			}
			System.out.println("You healed " + pokemon.getName() + " " + heal + " HP.");
			pokemon.gainHp(heal);
		} else {
			System.out.println(pokemon.getName() + " is already at full health.");
		}
	}
	
	/**
	 * Handles the wild Pokemon attack pattern and damages to your Pokemon.
	 * @param yourPokemon
	 * @param wildPokemon
	 */
	private static void wildPokemonAttacks(Pokemon yourPokemon, Pokemon wildPokemon) {
		int randomMoveType = (int) (Math.random() * 2);
		int randomMove = (int) (Math.random() * 3) + 1;
		int damage = 0;
		
		switch (randomMoveType) {
			case 0:
				damage = wildPokemon.basicFight(randomMove);
				break;
			case 1:
				damage = wildPokemon.specialFight(randomMove);
				break;
		}
		
		if (damage > 0) {
			yourPokemon.loseHp(damage);
			System.out.println("The wild " + wildPokemon.getName() + " did " + damage + " damage to " + yourPokemon.getName() + ".");
		}
		
		if (yourPokemon.getHp() < 0) {
			System.out.println(yourPokemon.getName() + " has fainted!");
		} else {
			System.out.println(yourPokemon.getName() + " has " + yourPokemon.getHp() + "/" + yourPokemon.getMaxHp() + " HP left.");
		}
	}
		
}
