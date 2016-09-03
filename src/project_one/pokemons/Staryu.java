package project_one.pokemons;

import project_one.types.Water;

public class Staryu extends Pokemon implements Water {

	public Staryu(String name, int maxHp, int level) {
		super(name, maxHp, level);
		super.baseExp = 15;
		calculateExp();
	}

	@Override
	public int waterGun() {
		return (int) ((super.getLevel() * 1.6) + 20);
	}

	@Override
	public int bubbleBeam() {
		return (int) ((super.getLevel() * 1.6) + 30);
	}

	@Override
	public int waterfall() {
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

	@Override
	public String[] displaySpecialMenu() {
		String[] menu = new String[TYPE_MENU.length + 1];
		menu[0] = "Choose an attack:";
		System.arraycopy(TYPE_MENU, 0, menu, 1, TYPE_MENU.length);
		return menu;
	}

}
