package project_one;

import project_one.entities.Opponent;
import project_one.entities.Player;
import project_one.entities.Trainer;
import project_one.pokemons.*;

/**
*
* PokemonBattles class handles all the methods that are used during Pokemon battles.
* 
* @author Richard Lam
* 
*/
public class PokemonBattles {
	
	/**
	 * Elemental type buff/debuff bonuses
	 */
	private final static double[][] FIGHT_TABLE = { {.5, .5, 2, 1}, 
												    {2, .5, .5, 1},
												    {.5, 2, .5, 1},
												    {1, 2, .5, .5} };
	
	/**
	 * Event handler for the Player running into an angry Pokemon. Deals damage directly to the Player.
	 * @param trainer
	 */
	public static void angryPokemon(Trainer trainer) {
		int hpLoss = 10;
		System.out.println(trainer.getCurrentPokemon() + " got hungry and bit you!");
		trainer.loseHp(hpLoss);
		System.out.println("You lost " + hpLoss + " hp!");
	}
	
	/**
	 * Event handler for the Player running into an angry trainer. Deals damage directly to the Player. 
	 * @param trainer is the Trainer
	 */
	public static void angryTrainer(Trainer trainer) {
		int hpLoss = 10;
		Opponent randomPerson = OpponentMaker.makeRandomOpponent();
		
		System.out.println("You accidentally stepped on " + randomPerson.getName() + "'s " + randomPerson.getCurrentPokemon().getName() + "!");
		System.out.println(randomPerson.getName() + " punched you!");
		trainer.loseHp(hpLoss);
		System.out.println("You lost " + hpLoss + " hp!");
	}
	
	/**
	 * Returns the elemental advantage of disadvantage when using elemental attacks against another Pokemon.
	 * @param attackingPokemon is the Pokemon dealing the damage
	 * @param receivingPokemon is the Pokemon receiving the damage
	 * @return the elemental buff/debuff damage
	 */
	public static double getElementalBonus(Pokemon attackingPokemon, Pokemon receivingPokemon) {
		int attackType = attackingPokemon.getType();
		int receiveType = receivingPokemon.getType();
		
		return FIGHT_TABLE[attackType][receiveType];
	}
	
	/**
	 * Handles the fight, heal, Pokeball, switch Pokemon, run menu, user inputs, and events for the player.
	 * @param player is your Player
	 * @param opp is the Opponent
	 * @param oppPokemon is the opposing Pokemon
	 */
	public static void pokeBattle(Player player, Opponent opp, Pokemon oppPokemon) {
		boolean fight = true;
		int response;
		
		if (opp != null) {
			System.out.println(opp.getName() + " sent out " + opp.getCurrentPokemon().getName() + "!");
		}
		System.out.println(player.speak(player.getCurrentPokemon().getName() + ", I choose you!"));
		
		do {
			String[] menu = {"Fight", "Use Potion", "Throw Pokeball", "Switch Pokemon", "Run Away"};
			response = Util.checkUserInput("What should I do?", menu);
			
			switch (response) {
				case 1:
					fightPokemon(player.getCurrentPokemon(), opp != null ? opp.getCurrentPokemon() : oppPokemon);
					break;
				case 2:
					if (player.getNumPotionsLeft() > 0) {
						healPokemon(player, player.getCurrentPokemon());
					} else {
						System.out.println("You ran out of Potions!");
						continue;
					}
					break;
				case 3:
					if (player.getNumPokeballsLeft() > 0 && opp == null) {
						fight = catchPokemon(player, oppPokemon);
					} else if (player.getNumPokeballsLeft() > 0 && opp != null) { 
						System.out.println("You cannot catch someone else's Pokemon!");
						continue;
					} else {
						System.out.println("You ran out of Pokeballs!");
						continue;
					}
					break;
				case 4:
					switchPokemon(player);
					break;
				case 5:
					System.out.println("You've chosen to run away.");
					fight = false;
					break;	
			}
			
			if ((opp != null ? opp.getCurrentPokemon().getHp() : oppPokemon.getHp()) > 0 && player.getCurrentPokemon().getHp() > 0 && fight) {
				wildPokemonAttacks(player.getCurrentPokemon(),  opp != null ? opp.getCurrentPokemon() : oppPokemon);
			} else if (opp != null && canSwitchPokemon(opp) && fight) {
				switchOppPokemon(opp);
				//oppPokemon = opp.getCurrentPokemon();
				System.out.println(opp.getName() + " sent out " + opp.getCurrentPokemon().getName() + ".");
			} else if (opp != null && !canSwitchPokemon(opp) && fight){
				System.out.println("You've beaten " + opp.getName() + "!");
				System.out.println(player.speak(player.winSpeech()));
				System.out.println(opp.speak(opp.lossSpeech()));
				fight = false;
			}
			
			if (player.getCurrentPokemon().getHp() <= 0 && fight) {
				fight = switchPokemon(player);
				if (!fight) {
					System.out.println("All of your Pokemon have fainted! Going back to town now...");
					System.out.println(player.speak(player.lossSpeech()));
					if (opp != null) {
						System.out.println(opp.speak(opp.winSpeech()));
					}
				}
			}
		} while ((opp != null ? opp.getCurrentPokemon().getHp() : oppPokemon.getHp()) > 0 && player.getCurrentPokemon().getHp() > 0 && fight);
	}
	
	/**
	 * Checks if the Opponent can switch to another Pokemon.
	 * @param opp is the Opponent
	 * @return true or false depending on whether or not the Opponent can switch to another Pokemon
	 */
	private static boolean canSwitchPokemon(Opponent opp) {
		for (Pokemon pokemon : opp.getPokemons()) {
			if (pokemon.getHp() > 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Switches the Opponent's current Pokemon to another Pokemon that is still alive.
	 * @param opp is the Opponent
	 */
	private static void switchOppPokemon(Opponent opp) {
		for (Pokemon pokemon : opp.getPokemons()) {
			if (pokemon.getHp() > 0) {
				opp.setCurrentPokemon(pokemon);
			}
		}
	}
	
	/**
	 * The menu that handles a Player switching their Pokemon.
	 * @param player is the Player
	 * @return a boolean value whether switching Pokemon was successful
	 */
	private static boolean switchPokemon(Player player) {
		if (player.getPokemons().size() == 1) {
			System.out.println("You don't have any other Pokemon to switch with!");
			return false;
		} else if (player.getPokemons().size() == 2) {
			for (Pokemon pokemon : player.getPokemons()) {
				if (pokemon.getHp() > 0) {
					player.setCurrentPokemon(pokemon);
					return true;
				}
			}
		} else {
			while (true) {
				int response = Util.checkUserInput("Choose a Pokemon to switch with:", player.listPokemon());
				if (player.getPokemon(response-1).getHp() > 0) {
					player.setCurrentPokemon(player.getPokemon(response-1));
					System.out.println(player.getName() + ": " + player.getCurrentPokemon().getName() + ", I choose you!");
					return true;
				} else {
					System.out.println("That Pokemon cannot battle right now.");
				}
			}
		}
		return true;
	}
	
	/**
	 * Event handler that handles catching a Pokemon.
	 * @param player is the Player
	 * @param wildPokemon is the Pokemon player is attempting to catch
	 * @return boolean value whether catching the Pokemon was successful
	 */
	private static boolean catchPokemon(Player player, Pokemon wildPokemon) {
		final double BASE_CATCH_CHANCE = .4;
		double catchChance = BASE_CATCH_CHANCE + ((1 - BASE_CATCH_CHANCE) * (wildPokemon.getHp()/wildPokemon.getMaxHp()));
		double rand = Math.random();
		player.usePokeball();
		
		System.out.println("You threw a pokeball!");
		
		if (rand <= catchChance) {
			System.out.println("You've successfully caught " + wildPokemon.getName() + "!");
			Main.namePokemon(wildPokemon);
			player.addPokemon(wildPokemon);
			
			gainExp(player.getCurrentPokemon(), wildPokemon);
			return false;
		} else {
			System.out.println("The wild " + wildPokemon.getName() + " broke free.");
		}
		System.out.println("You have " + player.getNumPokeballsLeft() + " Pokeballs left.");
		return true;
	}
	
	/**
	 * Event handler that handles a Pokemon gaining exp.
	 * @param pokemon is the Pokemon gaining the exp
	 * @param wildPokemon is the Pokemon giving the exp
	 */
	private static void gainExp(Pokemon pokemon, Pokemon wildPokemon) {
		int expGained = wildPokemon.getExpAfterWin();
		pokemon.gainExp(expGained);
		System.out.println(pokemon.getName() + " gained " + expGained + " exp!");
		
		if (pokemon.getExp() >= pokemon.getExpToLevel()) {
			levelPokemon(pokemon);
		}
	}
	
	/**
	 * Event handler that handles a Pokemon leveling up.
	 * @param pokemon is the Pokemon leveling up
	 */
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
	
	/**
	 * Handles and displays the fight menu, attacks, and damages between your Pokemon and the wild Pokemon.
	 * @param yourPokemon is your trainer's Pokemon
	 * @param wildPokemon is the wild Pokemon
	 */
	private static void fightPokemon(Pokemon yourPokemon, Pokemon wildPokemon) {
		int damage = 0;
		double elementalBonus = 1;
		String[] menu = {"Basic Attack", "Special Attack"};
		int response = Util.checkUserInput("Choose an attack type:", menu);
		switch (response) {
			case 1:
				response = Util.checkUserInput("Choose an attack:", yourPokemon.displayBasicMenu());
				damage = yourPokemon.basicFight(response);
				break;
			case 2:
				response = Util.checkUserInput("Choose an attack:", yourPokemon.displaySpecialMenu());
				damage = yourPokemon.specialFight(response);
				elementalBonus = PokemonBattles.getElementalBonus(yourPokemon, wildPokemon);
				damage = (int) ((double) damage * elementalBonus);
				break;
		}
		
		if (elementalBonus > 1) {
			System.out.println("It's super effective!");
		} else if (elementalBonus < 1) {
			System.out.println("It's not very effective...");
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
	private static void healPokemon(Player player, Pokemon pokemon) {
		if (pokemon.getHp() < pokemon.getMaxHp()) {
			final int MAX_HEAL = 100;
			int heal = 0;
			
			if (pokemon.getMaxHp() - pokemon.getHp() > MAX_HEAL) {
				heal = MAX_HEAL;
			} else {
				heal = pokemon.getMaxHp() - pokemon.getHp();
			}
			System.out.println("You healed " + pokemon.getName() + " " + heal + " HP.");
			pokemon.gainHp(heal);
			player.usePotion();
		} else {
			System.out.println(pokemon.getName() + " is already at full health.");
		}
		System.out.println("You have " + player.getNumPotionsLeft() + " Potions left.");
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
		double elementalBonus = 1;
		
		switch (randomMoveType) {
			case 0:
				damage = wildPokemon.basicFight(randomMove);
				break;
			case 1:
				damage = wildPokemon.specialFight(randomMove);
				elementalBonus = PokemonBattles.getElementalBonus(wildPokemon, yourPokemon);
				damage = (int) ((double) damage * elementalBonus);
				break;
		}
		
		if (elementalBonus > 1) {
			System.out.println("It's super effective!");
		} else if (elementalBonus < 1) {
			System.out.println("It's not very effective...");
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
