package project_one.entities;

/**
 * Opponent class that contains speech, attack patterns, and opponent type.
 * 
 * @author Richard Lam
 *
 */
public class Opponent extends Trainer {
	
	/**
	 * Opponent type
	 */
	private int type;
<<<<<<< HEAD
	/**
	 * Opponent's attack speech, win speech, and loss speech.
	 */
=======
>>>>>>> origin/master
	private String attackSpeech, winSpeech, lossSpeech;

	/**
	 * Constructor
	 * @param name is the name of the opponent
	 * @param hp is the hp of the opponent
	 */
	public Opponent(String name, int hp, String attackSpeech, String lossSpeech, String winSpeech) {
		super(name, hp);
		this.attackSpeech = attackSpeech;
		this.winSpeech = winSpeech;
		this.lossSpeech = lossSpeech;
	}

	/**
	 * Returns the opponent's attack speech.
	 */
	@Override
	public String attackSpeech() {
		return attackSpeech;
	}

	/**
	 * Returns the opponent's win speech.
	 */
	@Override
	public String winSpeech() {
		return winSpeech;
	}

	/**
	 * Returns the opponent's loss speech.
	 */
	@Override
	public String lossSpeech() {
		return lossSpeech;
	}

	/**
	 * Returns the opponent's attack style.
	 */
	@Override
	public int chooseStyle() {
		return(int) (Math.random() * 2);
	}

	/**
	 * Returns the opponent's attack move.
	 * @param style is the attack style
	 * @return the opponent's attack move.
	 */
	@Override
	public int chooseMove(int style) {
		return (int) (Math.random() * 3) + 1;
	}
	
	/**
	 * Returns the opponent's type.
	 * @return
	 */
	public int getOppType() {
		return type;
	}

}
