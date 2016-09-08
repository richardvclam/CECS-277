package project_one;

import java.util.Scanner;

import project_one.entities.Opponent;
import project_one.entities.Player;
import project_one.pokemons.*;

/**
 * 
 * Main Pokemon class that handles the main game functions. Asks the user for their Trainer's name and their choice
 * for a starter Pokemon. The game then loops and output the menu screen Walk, Pokecenter, Shop, Display Pokemon, or Quit.
 * 
 * @author Richard Lam
 */
public class Main {

	public static void main(String[] args) {
		boolean run = true;
		int response;
		Scanner in = new Scanner(System.in);
		
		System.out.println("What is your trainer's name?");
		final Player player = new Player(in.nextLine(), 100);
		
		Pokemon newPokemon = PokemonMaker.makeStarterPokemon();
		
		player.addPokemon(newPokemon);
		player.setCurrentPokemon(newPokemon);
		namePokemon(player.getCurrentPokemon());
		
		do {
			String[] menu = {"Walk Around", "Heal Pokemon at PokeCenter", "Shop at PokeMart", "Display Pokemon", "Quit Game"};
			response = Util.checkUserInput("What would you like to do?", menu);
			
			switch(response) {
				case 1:
					if (player.getCurrentPokemon().getHp() > 0) {
						walk(player);
					} else {
						System.out.println(player.speak("I don't think that's a good idea... I should heal my Pokemon first."));
					}
					break;
				case 2:
					pokeCenter(player);
					break;
				case 3:
					if (player.getMoney() > 0) {
						shop(player);
					} else {
						System.out.println(player.getName() + ": I don't have any money left...");
					}
					break;
				case 4:
					player.displayPokemon();
					break;
				case 5:
					run = false;
					break;
			}
		} while (run);
		
		in.close();
	}
	
	/**
	 * Takes the player on a walk and generates a random event. The events are between encountering a wild Pokemon or a trainer battle.
	 * @param player is the Player
	 */
	private static void walk(Player player) {
		System.out.println("You decided to go on a leisurely stroll.");
		final double WILD_POKE_CHANCE = .5;
		double eventNum = Math.random();
		
		if (eventNum <= WILD_POKE_CHANCE) { // 50% chance of encountering a wild pokemon
			encounterWildPokemon(player);
		} else if (eventNum < WILD_POKE_CHANCE + .3) { // 30% chance of encountering a trainer
			trainerBattle(player);
		} else if (eventNum < WILD_POKE_CHANCE + .4) {
			PokemonBattles.angryPokemon(player);
		} else {
			PokemonBattles.angryTrainer(player);
		}
	}
	
	/**
	 * Trainer battle event generates a random opponent and starts the PokeBattle.
	 * @param player is the Player
	 */
	private static void trainerBattle(Player player) {
		Opponent opp = OpponentMaker.makeRandomOpponent();
		
		System.out.println(opp.speak(opp.attackSpeech()));
		System.out.println(player.speak(player.attackSpeech()));
		
		PokemonBattles.opponentBattle(player, opp);
	}
	
	/**
	 * This is the encountering a wild Pokemon event that generates a random Pokemon and starts the PokeBattle.
	 * @param player is the Player
	 */
	private static void encounterWildPokemon(Player player) {
		Pokemon wildPokemon = PokemonMaker.makeWildPokemon();
		
		System.out.println("A wild " + wildPokemon.getName() + " has appeared!");
		
		PokemonBattles.wildPokeBattle(player, wildPokemon);
	}
	
	/**
	 * Takes the player to the PokeCenter to heal all of their Pokemons.
	 * @param player is the Player
	 */
	private static void pokeCenter(Player player) {
		System.out.println("Nurse Joy: Welcome to the PokeCenter! I'll take your Pokemon for a few seconds...");
		for (Pokemon pokemon : player.getPokemons()) {
			int hpDiff = pokemon.getMaxHp() - pokemon.getHp();
			if (hpDiff > 0) {
				pokemon.gainHp(hpDiff);
			}
		}
		System.out.println("Nurse Joy: All of your Pokemon have been restored to full health! We hope to see you again!");
	}
	
	/**
	 * Takes the player into the shop menu to purchase items.
	 * @param player is the Player
	 */
	private static void shop(Player player) {
		Scanner in = new Scanner(System.in);
		final int POKEBALL_PRICE = 100;
		final int POTION_PRICE = 50;
		String[] menu = {"Pokeballs - $" + POKEBALL_PRICE, "Potions - $" + POTION_PRICE, "Leave"}; // Menu selection choices
		String[] items = {"Pokeballs", "Potions"};
		
		do {
			int quantity;
			System.out.println(player.getName() + ": I only have $" + player.getMoney() + ". I should spend my money wisely...");
			int choice = Util.checkUserInput("Cashier: Welcome to the PokeMart! What can I get for you?", menu);
			if (choice == 3) {
				break;
			}
			do {
				quantity = Util.checkPositiveInt("How many " + items[choice-1] + " would you like?");
				if (choice == 1 && player.getMoney() >= quantity * POKEBALL_PRICE) {
					player.spendMoney(quantity * POKEBALL_PRICE);
					player.buyPokeball(quantity);
					break;
				} else if (choice == 2 && player.getMoney() >= quantity * POTION_PRICE) {
					player.spendMoney(quantity * POTION_PRICE);
					player.buyPotion(quantity);
					break;
				} else {
					System.out.println("You do not have enough money.");
				}
			} while (true);
			System.out.println("You bought " + quantity + " " + items[choice-1] + ".");
		} while (player.getMoney() > 0);
		
		System.out.println("Cashier: Thank you! Come back soon!");
	}
	
	/**
	 * Event handler that handles naming a newly acquired Pokemon.
	 * @param pokemon is the Pokemon to name
	 */
	public static void namePokemon(Pokemon pokemon) {
		Scanner in = new Scanner(System.in);
		String[] menu = {"Yes", "No"};
		int response = Util.checkUserInput("Would you like to name your Pokemon?", menu);
		
		if (response == 1) {
			System.out.println("What would you like to name your Pokemon?");
			String name = in.nextLine();
			pokemon.setName(name);
		}
	}
		
}
