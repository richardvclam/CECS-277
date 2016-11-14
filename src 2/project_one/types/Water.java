package project_one.types;

/**
 * Water type interface
 * 
 * @author Richard Lam
 *
 */
public interface Water {
	
	/**
	 * Water type value
	 */
	static final int TYPE = 1;
	/**
	 * Water type attacks
	 */
	static final String[] TYPE_MENU = {"Water Gun", "Bubble Beam", "Waterfall"};
	
	/**
	 * Returns the damage value of Water Gun
	 * @return damage value of Water Gun
	 */
	int waterGun();
	
	/**
	 * Returns the damage value of Bubble Beam
	 * @return damage value of Bubble Beam
	 */
	int bubbleBeam();
	
	/**
	 * Returns the damage value of Water Fall
	 * @return damage value of Water Fall
	 */
	int waterfall();

}
