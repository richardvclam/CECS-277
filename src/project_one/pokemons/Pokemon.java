package project_one.pokemons;

import project_one.entities.Entity;

/**
 * Pokemon class that modifies a Pokemon's type, exp, level, and their basic attacks.
 * 
 * @author Richard
 *
 */
public abstract class Pokemon extends Entity {
	
	/**
	 * Pokemon's level and exp.
	 */
	private int level, exp;
	/**
	 * Pokemon's exp to give after another Pokemon defeats it.
	 */
	protected int expAfterWin;
	/**
	 * Pokemon's base experience points. It is used in calculating the {@code expAfterWin}.
	 */
	protected int baseExp = 10;

	/**
	 * Constructor
	 * @param name is the name of the Pokemon
	 * @param maxHp is the max hp of the Pokemon
	 * @param level is the level of the Pokemon
	 */
	public Pokemon(String name, int maxHp, int level) {
		super(name, maxHp);
		this.level = level;
		calculateExpAfterWin();
	}
	
	/**
	 * Returns the Pokemon's type.
	 * @return the Pokemon's type
	 */
	public abstract int getType();
	
	/**
	 * Returns the damage dealt using a special move.
	 * @param move is the move number
	 * @return the damage dealt
	 */
	public abstract int specialFight(int move);
	
	/**
	 * Returns a String array that shows the special move's menu selection.
	 * @return a String array with the special move's menu 
	 */
	public abstract String[] displaySpecialMenu();
	
	/**
	 * Calculates the exp another Pokemon gains after defeating this Pokemon.
	 */
	public void calculateExpAfterWin() {
		this.expAfterWin = (int) (baseExp * ((double)super.getMaxHp() / 100));
	}

	/**
	 * Returns the Pokemon's level.
	 * @return
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Increases the Pokemon's level by one. Also increases the Pokemon's max hp by {@code hpPerLevel}.
	 */
	public void levelUp() {
		final int hpPerLevel = 100;
		level++;
		super.incMaxHp(hpPerLevel);
		exp = 0;
	}
	
	/**
	 * Returns the Pokemon's exp.
	 * @return
	 */
	public int getExp() {
		return exp;
	}
	
	/**
	 * Increaes the Pokemon's exp by {@code exp}.
	 * @param exp is the amount of exp to increase by
	 * @return the resulting exp gain
	 */
	public int gainExp(int exp) {
		return this.exp += exp;
	}
	
	/**
	 * Returns the amount of exp required to level up.
	 * @return the amount of exp required to level up
	 */
	public int getExpToLevel() {
		return baseExp * level;
	}
	
	/**
	 * Returns the amoung of exp gained after winning.
	 * @return
	 */
	public int getExpAfterWin() {
		return expAfterWin;
	}
	
	/**
	 * Returns a String array of basic attack menu.
	 * @return String array of basic attack menu
	 */
	public String[] displayBasicMenu() {
		String[] menu = {"Slam", "Tackle", "Mega Punch"};

		return menu;
	}
	
	/**
	 * Returns the basic move damage from {@code move}.
	 * @param move the basic move number
	 * @return the basic move damage
	 */
	public int basicFight(int move) {
		switch (move) {
			case 1: // Slam
				System.out.println(super.getName() + " used Slam!");
				return slam();
			case 2: // Tackle
				System.out.println(super.getName() + " used Tackle!");
				return tackle();
			case 3: // Mega Punch
				System.out.println(super.getName() + " used Mega Punch!");
				return megaPunch();
		}
		return 0;
	}
	
	/**
	 * Returns slam's damage.
	 * @return slam damage
	 */
	public int slam() {
		return (int) ((level * 1.5) + 5);
	}
	
	/**
	 * Returns tackle's damage.
	 * @return tackle damage
	 */
	public int tackle() {
		return (int) ((level * 1.5) + 10);
	}
	
	/**
	 * Returns mega punch's damage.
	 * @return mega punch damage
	 */
	public int megaPunch() {
		return (int) ((level * 1.75) + 30);
	}
	
	/**
	 * Returns the damage
	 * @param style
	 * @param move
	 * @return
	 */
	public int fight(int style, int move) {
		if (style == 1) {
			return basicFight(move);
		} else {
			return specialFight(move);
		}
	}
	
}
