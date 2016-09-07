package project_one.types;

/**
 * Grass type interface
 * 
 * @author Richard Lam
 *
 */
public interface Grass {
	
	/**
	 * Grass type value
	 */
	static final int TYPE = 2;
	/**
	 * Grass type attacks
	 */
	static final String[] TYPE_MENU = {"Vine Whip", "Razor Leaf", "Solar Beam"};
	
	/**
	 * Returns the damage value of Vine Whip
	 * @return damage value of Vine Whip
	 */
	int vineWhip();
	
	/**
	 * Returns the damage value of Razor Leaf
	 * @return damage value of Razor Leaf
	 */
	int razorLeaf();
	
	/**
	 * Returns the damage value of Solar Beam
	 * @return damage value of Solar Beam
	 */
	int solarBeam();

}
