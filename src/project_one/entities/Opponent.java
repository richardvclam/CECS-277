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

	/**
	 * Constructor
	 * @param name is the name of the opponent
	 * @param hp is the hp of the opponent
	 */
	public Opponent(String name, int hp) {
		super(name, hp);
	}

	/**
	 * Returns the opponent's attack speech.
	 */
	@Override
	public String attackSpeech() {
		return "You picked the wrong day to mess with me!";
	}

	/**
	 * Returns the opponent's win speech.
	 */
	@Override
	public String winSpeech() {
		return "Haha! I knew you were a weakling.";
	}

	/**
	 * Returns the opponent's loss speech.
	 */
	@Override
	public String lossSpeech() {
		return "Oh no! You'll regret this!";
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
