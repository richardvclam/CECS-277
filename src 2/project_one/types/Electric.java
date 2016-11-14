package project_one.types;

/**
 * Electric type interface
 * 
 * @author Richard Lam
 *
 */
public interface Electric {
	
	/**
	 * Electric type value
	 */
	static final int TYPE = 3;
	/**
	 * Electric type attacks
	 */
	static final String[] TYPE_MENU = {"Thunder Shock", "Thunder Bolt", "Thunder Punch"};
	
	/**
	 * Returns the damage value of Thunder Shock
	 * @return damage value of Thunder Shock
	 */
	int thunderShock();
	
	/**
	 * Returns the damage value of Thunder Bolt
	 * @return damage value of Thunder Bolt
	 */
	int thunderBolt();
	
	/**
	 * Returns the damage value of Thunder Punch
	 * @return damage value of Thunder Punch
	 */
	int thunderPunch();

}
