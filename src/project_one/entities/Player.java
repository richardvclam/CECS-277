package project_one.entities;

import java.awt.Point;
import java.io.Serializable;

import project_one.Map;
import project_one.Util;

/**
 * Player class that has methods modifying potions, pokeballs, money, and returns speech and attack patterns.
 * 
 * @author Richard Lam
 *
 */
public class Player extends Trainer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Amount of potions.
	 */
	private int potions;
	/**
	 * Amount of Pokeballs.
	 */
	private int pokeballs;
	/**
	 * Amount of money.
	 */
	private int money;
	private Point location;
	private int level;
	private Map currentMap;
	

	/**
	 * Constructor
	 * @param name is the name of the Player
	 * @param hp is the hp of the Player
	 */
	public Player(String name, int hp, Map currentMap, Point start) {
		super(name, hp);
		potions = 10;
		pokeballs = 10;
		money = 1000;
		location = start;
		this.currentMap = currentMap;
	}

	/**
	 * Reduce number of potions by one.
	 */
	public void usePotion() {
		potions--;
	}
	
	/**
	 * Return the number of potions.
	 * @return the number of potions
	 */
	public int getNumPotionsLeft() {
		return potions;
	}
	
	/**
	 * Increases the number of potions by one.
	 */
	public void buyPotion() {
		potions++;
	}
	
	/**
	 * Increases the number of potions by a specific quantity.
	 * @param quantity number of potions to increase by
	 */
	public void buyPotion(int quantity) {
		potions += quantity;
	}
	
	/**
	 * Reduce the number of Pokeballs by one.
	 */
	public void usePokeball() {
		pokeballs--;
	}
	
	/**
	 * Return the number of Pokeballs.
	 * @return the number of Pokeballs
	 */
	public int getNumPokeballsLeft() {
		return pokeballs;
	}
	
	/**
	 * Increases the number of Pokeballs by one.
	 */
	public void buyPokeball() {
		pokeballs++;
	}
	
	/**
	 * Increases the number of Pokeballs by a specific quantity.
	 * @param quantity number of Pokeballs to increase by
	 */
	public void buyPokeball(int quantity) {
		pokeballs += quantity;
	}
	
	/**
	 * Reduces the amount of money by {@code price}.
	 * @param price is the amount of money to reduce by
	 */
	public void spendMoney(int price) {
		money -= price;
	}
	
	/**
	 * Returns the amount of money the player has.
	 * @return
	 */
	public int getMoney() {
		return money;
	}
	
	/**
	 * Increases the amount of money the player has by {@code money}.
	 * @param money the amount of money to increase by
	 */
	public void gainMoney(int money) {
		this.money += money;
	}

	/**
	 * Returns the player's attack speech.
	 * @return the player's attack speech
	 */
	@Override
	public String attackSpeech() {
		return "You're going down!";
	}

	/**
	 * Returns the player's win speech.
	 * @return the player's win speech
	 */
	@Override
	public String winSpeech() {
		return "I guess today's my lucky day!";
	}

	/**
	 * Returns the player's loss speech.
	 * @return the player's loss speech
	 */
	@Override
	public String lossSpeech() {
		return "I guess it just wasn't my day...";
	}

	/**
	 * Returns the player's attack style.
	 * @return the attack style
	 */
	@Override
	public int chooseStyle() {
		String[] menu = {"Basic Attack", "Special Attack"};
		
		return Util.checkUserInput("Choose an attack type:", menu);
	}

	/**
	 * Returns the player's attack move.
	 * @param style is the attack style
	 * @return the player's attack move
	 */
	@Override
	public int chooseMove(int style) {
		switch (style) {
		case 1:
			return Util.checkUserInput("Choose an attack:", getCurrentPokemon().displayBasicMenu());
		case 2:
			return Util.checkUserInput("Choose an attack:", getCurrentPokemon().displaySpecialMenu());
		default:
			return 0;
		}
	}
	
	public Point getLocation() {
		return location;
	}
	
	public boolean setLocation(Point p) {
		location = p;
		return true;
	}
	
	public Map getCurrentMap() {
		return currentMap;
	}
	
	public void setCurrentMap(Map map) {
		currentMap = map;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void incLevel() {
		level++;
	}
	
	public char goNorth(Map m) {
		Point direction = new Point(location.x, location.y - 1);
		if (m.getCharAtLoc(direction) != '0') {
			location = direction;
			m.reveal(direction);
		}
		return m.getCharAtLoc(direction);
	}
	
	public char goSouth(Map m) {
		Point direction = new Point(location.x, location.y + 1);
		if (m.getCharAtLoc(direction) != '0') {
			location = direction;
			m.reveal(direction);
		}
		return m.getCharAtLoc(direction);
	}
	
	public char goEast(Map m) {
		Point direction = new Point(location.x + 1, location.y);
		if (m.getCharAtLoc(direction) != '0') {
			location = direction;
			m.reveal(direction);
		}
		return m.getCharAtLoc(direction);
	}
	
	public char goWest(Map m) {
		Point direction = new Point(location.x - 1, location.y);
		if (m.getCharAtLoc(direction) != '0') {
			location = direction;
			m.reveal(direction);
		}
		return m.getCharAtLoc(direction);
	}
	
}
