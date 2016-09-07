package project_one.pokemons;

import project_one.types.Fire;

/**
 * Ponyta class with its own move set damage and menu.
 * 
 * @author Richard
 *
 */
public class Ponyta extends Pokemon implements Fire {

	/**
	 * Constructor
	 * @param name is the Pokemon's name
	 * @param maxHp is the Pokemon's maximum hp
	 * @param level is the Pokemon's level
	 */
	public Ponyta(String name, int maxHp, int level) {
		super(name, maxHp, level);
		super.baseExp = 15;
		calculateExpAfterWin();
	}

	/**
	 * Returns the damage value of Ember
	 * @return damage value of Ember
	 */
	public int ember() {
		return (int) ((super.getLevel() * 1.6) + 20);
	}
	
	/**
	 * Returns the damage value of Fire Blast
	 * @return damage value of Fire Blast
	 */
	public int fireBlast() {
		return (int) ((super.getLevel() * 1.6) + 30);
	}
	
	/**
	 * Returns the damage value of Fire Punch
	 * @return damage value of Fire Punch
	 */
	public int firePunch() {
		return (int) ((super.getLevel() * 1.75) + 40);
	}

	/**
	 * Returns the Pokemon type
	 * @return the Pokemon type
	 */
	@Override
	public int getType() {
		return TYPE;
	}

	/**
	 * Returns the special move damage from {@code move}.
	 * @param move the special move number
	 * @return the special move damage
	 */
	@Override
	public int specialFight(int move) {
		switch (move) {
			case 1: // Ember
				System.out.println(super.getName() + " used Ember!");
				return ember();
			case 2: // Fire Blast
				System.out.println(super.getName() + " used Fire Blast!");
				return fireBlast();
			case 3: // Fire Punch
				System.out.println(super.getName() + " used Fire Punch!");
				return firePunch();
		}
		return 0;
	}

	/**
	 * Returns a String array of the special attack menu.
	 * @return String array of the special attack menu
	 */
	@Override
	public String[] displaySpecialMenu() {
		return TYPE_MENU;
	}

}
