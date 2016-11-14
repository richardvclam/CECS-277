package project_one.pokemons;

import project_one.types.Grass;

/**
 * Bulbasaur class with its own move set damage and menu.
 * 
 * @author Richard
 *
 */
public class Bulbasaur extends Pokemon implements Grass {

	/**
	 * Constructor
	 * @param name is the Pokemon's name
	 * @param maxHp is the Pokemon's maximum hp
	 * @param level is the Pokemon's level
	 */
	public Bulbasaur(String name, int maxHp, int level) {
		super(name, maxHp, level);
		super.baseExp = 15;
		calculateExpAfterWin();
	}

	/**
	 * Returns vine whip damage.
	 * @return vine whip damage
	 */
	@Override
	public int vineWhip() {
		return (int) ((super.getLevel() * 1.6) + 20);
	}

	/**
	 * Returns razor leaf damage.
	 * @return razor leaf damage
	 */
	@Override
	public int razorLeaf() {
		return (int) ((super.getLevel() * 1.6) + 30);
	}

	/**
	 * Returns solar beam damage.
	 * @return solar beam damage
	 */
	@Override
	public int solarBeam() {
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
				System.out.println(super.getName() + " used Vine Whip!");
				return vineWhip();
			case 2:
				System.out.println(super.getName() + " used Razor Leaf!");
				return razorLeaf();
			case 3:
				System.out.println(super.getName() + " used Solar Beam!");
				return solarBeam();
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
