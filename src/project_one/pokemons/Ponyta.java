package project_one.pokemons;

import project_one.types.Fire;

public class Ponyta extends Pokemon implements Fire {

	public Ponyta(String name, int maxHp, int level) {
		super(name, maxHp, level);
		super.baseExp = 15;
		calculateExp();
	}

	public int ember() {
		return (int) ((super.getLevel() * 1.6) + 20);
	}
	
	public int fireBlast() {
		return (int) ((super.getLevel() * 1.6) + 30);
	}
	
	public int firePunch() {
		return (int) ((super.getLevel() * 1.75) + 40);
	}

	@Override
	public int getType() {
		return TYPE;
	}

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

	@Override
	public String[] displaySpecialMenu() {
		String[] menu = new String[TYPE_MENU.length + 1];
		menu[0] = "Choose an attack:";
		System.arraycopy(TYPE_MENU, 0, menu, 1, TYPE_MENU.length);
		return menu;
	}

}
