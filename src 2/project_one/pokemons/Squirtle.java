package project_one.pokemons;

import project_one.types.Water;

/**
 * Squirtle class with its own move set damage and menu.
 * 
 * @author Richard
 *
 */
public class Squirtle extends Pokemon implements Water {

	/**
	 * Constructor
	 * @param name is the Pokemon's name
	 * @param maxHp is the Pokemon's maximum hp
	 * @param level is the Pokemon's level
	 */
	public Squirtle(String name, int maxHp, int level) {
		super(name, maxHp, level);
		super.baseExp = 15;
		calculateExpAfterWin();
	}

	/**
	 * Returns the damage value of Water Gun
	 * @return damage value of Water Gun
	 */
	@Override
	public int waterGun() {
		return (int) ((super.getLevel() * 1.6) + 20);
	}

	/**
	 * Returns the damage value of Bubble Beam
	 * @return damage value of Bubble Beam
	 */
	@Override
	public int bubbleBeam() {
		return (int) ((super.getLevel() * 1.6) + 30);
	}

	/**
	 * Returns the damage value of Water Fall
	 * @return damage value of Water Fall
	 */
	@Override
	public int waterfall() {
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
			case 1:
				System.out.println(super.getName() + " used Water Gun!");
				return waterGun();
			case 2:
				System.out.println(super.getName() + " used Bubble Beam!");
				return bubbleBeam();
			case 3:
				System.out.println(super.getName() + " used Waterfall!");
				return waterfall();
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
