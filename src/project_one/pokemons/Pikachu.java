package project_one.pokemons;

import project_one.types.Electric;


public class Pikachu extends Pokemon implements Electric {

	public Pikachu(String name, int maxHp, int level) {
		super(name, maxHp, level);
		super.baseExp = 15;
		calculateExp();
	}
	
	@Override
	public String[] displaySpecialMenu() {
		String[] menu = new String[TYPE_MENU.length + 1];
		menu[0] = "Choose an attack:";
		System.arraycopy(TYPE_MENU, 0, menu, 1, TYPE_MENU.length);
		return menu;
	}
	
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
	
	public int thunderShock() {
		return (int) ((super.getLevel() * 1.6) + 20);
	}
	
	public int thunderBolt() {
		return (int) ((super.getLevel() * 1.6) + 30);
	}
	
	public int thunderPunch() {
		return (int) ((super.getLevel() * 1.75) + 40);
	}

	@Override
	public int getType() {
		return TYPE;
	}

}
