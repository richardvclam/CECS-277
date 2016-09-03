package project_one.pokemons;

import project_one.entities.Entity;

public abstract class Pokemon extends Entity {
	
	private int level;
	protected int exp;
	protected int expAfterWin;
	protected int baseExp = 10;
	protected int nextLevelExp;

	public Pokemon(String name, int maxHp, int level) {
		super(name, maxHp);
		this.level = level;
		calculateExp();
	}
	
	public abstract int getType();
	
	public abstract int specialFight(int move);
	
	public abstract String[] displaySpecialMenu();
	
	public void calculateExp() {
		this.expAfterWin = baseExp * (super.getMaxHp() / 100);
	}
	
	public boolean canLevel() {
		int expToLevel = expAfterWin * level;
		return expAfterWin >= expToLevel;
	}

	public int getLevel() {
		return level;
	}
	
	public void levelUp() {
		this.level++;
	}
	
	public int getExp() {
		return exp;
	}
	
	public int gainExp(int exp) {
		return this.exp += exp;
	}
	
	public int getExpToLevel() {
		return baseExp * level;
	}
	public int getExpAfterWin() {
		return expAfterWin;
	}
	
	public String[] displayBasicMenu() {
		String[] menu = {"Choose an attack:",
						"Slam", "Tackle", "Mega Punch"};

		return menu;
	}
	
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
	
	public int slam() {
		return (int) ((level * 1.5) + 5);
	}
	
	public int tackle() {
		return (int) ((level * 1.5) + 10);
	}
	
	public int megaPunch() {
		return (int) ((level * 1.75) + 30);
	}
	
}
