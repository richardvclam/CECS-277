package project_one.pokemons;

import project_one.types.Grass;

public class Oddish extends Pokemon implements Grass {

	public Oddish(String name, int maxHp, int level) {
		super(name, maxHp, level);
		super.baseExp = 15;
		calculateExp();
	}

	@Override
	public int vineWhip() {
		return (int) ((super.getLevel() * 1.6) + 20);
	}

	@Override
	public int razorLeaf() {
		return (int) ((super.getLevel() * 1.6) + 30);
	}

	@Override
	public int solarBeam() {
		return (int) ((super.getLevel() * 1.75) + 40);
	}

	@Override
	public int getType() {
		return TYPE;
	}

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

	@Override
	public String[] displaySpecialMenu() {
		String[] menu = new String[TYPE_MENU.length + 1];
		menu[0] = "Choose an attack:";
		System.arraycopy(TYPE_MENU, 0, menu, 1, TYPE_MENU.length);
		return menu;
	}

}
