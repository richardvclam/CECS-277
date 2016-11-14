package project_one.types;

/**
 * Fire type interface
 * 
 * @author Richard Lam
 *
 */
public interface Fire {
	
	/**
	 * Fire type value
	 */
	static final int TYPE = 0;
	/**
	 * Fire type attacks
	 */
	static final String[] TYPE_MENU = {"Ember", "Fire Blast", "Fire Punch"};
	
	/**
	 * Returns the damage value of Ember
	 * @return damage value of Ember
	 */
	int ember();
	
	/**
	 * Returns the damage value of Fire Blast
	 * @return damage value of Fire Blast
	 */
	int fireBlast();
	
	/**
	 * Returns the damage value of Fire Punch
	 * @return damage value of Fire Punch
	 */
	int firePunch();

}
