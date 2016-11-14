package project_one.pokemons;

import project_one.types.Electric;

/**
 * Zapdos class with its own move set damage and menu.
 * 
 * @author Richard
 *
 */
public class Zapdos extends Pokemon implements Electric {

	/**
	 * Constructor
	 * @param name is the Pokemon's name
	 * @param maxHp is the Pokemon's maximum hp
	 * @param level is the Pokemon's level
	 */
	public Zapdos(String name, int maxHp, int level) {
		super(name, maxHp, level);
		super.baseExp = 50;
		calculateExpAfterWin();
	}

	/**
	 * Returns a String array of the special attack menu.
	 * @return String array of the special attack menu
	 */
	@Override
	public String[] displaySpecialMenu() {
		return TYPE_MENU;
	}
	
	/**
	 * Returns the special move damage from {@code move}.
	 * @param move the special move number
	 * @return the special move damage
	 */
	@Override
	public int specialFight(int move) {
		switch (move) {
			case 1: // Thunder Shock
				System.out.println(super.getName() + " used Thunder Shock!");
				return thunderShock();
			case 2: // Thunder Bolt
				System.out.println(super.getName() + " used Thunder Bolt!");
				return thunderBolt();
			case 3: // Thunder Punch
				System.out.println(super.getName() + " used Thunder Punch!");
				return thunderPunch();
		}
		return 0;
	}
	
	/**
	 * Returns thunder shock damage.
	 * @return thunder shock damage
	 */
	public int thunderShock() {
		return (int) ((super.getLevel() * 1.6) + 20);
	}
	
	/**
	 * Returns thunderbolt damage.
	 * @return thunderbolt damage
	 */
	public int thunderBolt() {
		return (int) ((super.getLevel() * 1.6) + 30);
	}
	
	/**
	 * Returns thunder punch damage.
	 * @return thunder punch damage
	 */
	public int thunderPunch() {
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

}
