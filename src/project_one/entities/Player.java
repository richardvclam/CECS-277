package project_one.entities;

public class Player extends Trainer {
	
	private int potions;
	private int pokeballs;
	private int money;

	public Player(String name, int hp) {
		super(name, hp);
	}

	public void usePotion() {
		potions--;
	}
	
	public int getNumPotionsLeft() {
		return potions;
	}
	
	public void buyPotion() {
		potions++;
	}
	
	public void usePokeball() {
		pokeballs--;
	}
	
	public int getNumPokeballsLeft() {
		return pokeballs;
	}
	
	public void buyPokeball() {
		pokeballs++;
	}
	
	public void spendMoney(int price) {
		this.money -= price;
	}

	@Override
	public void attackSpeech() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void winSpeech() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lossSpeech() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int chooseStyle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int chooseMove(int style) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
