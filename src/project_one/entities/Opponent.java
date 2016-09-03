package project_one.entities;

public class Opponent extends Trainer {
	
	private int type;

	public Opponent(String name, int hp) {
		super(name, hp);
		// TODO Auto-generated constructor stub
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
	
	public int getOppType() {
		return type;
	}

}
