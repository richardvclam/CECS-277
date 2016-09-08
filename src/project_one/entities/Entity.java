package project_one.entities;

/**
 * Entity class is a basic entity object with a name and hp.
 * 
 * @author Richard Lam
 *
 */
public abstract class Entity {

	/**
	 * Name of the entity.
	 */
	private String name;
	/**
	 * Current health points of the entity.
	 */
	private int hp;
	/**
	 * Maximum health points of the entity.
	 */
	private int maxHp;
	
	/**
	 * Constructor
	 * @param name is the name of the entity
	 * @param maxHp is the maximum hp of the entity
	 */
	public Entity(String name, int maxHp) {
		this.name = name;
		this.maxHp = maxHp;
		hp = maxHp;
	}
	
	/**
	 * Returns the entity's name.
	 * @return the entity's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the entity's name.
	 * @param name is the name to set for the entity
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the entity's hp.
	 * @return the entity's hp
	 */
	public int getHp() {
		return hp;
	}
	
	/**
	 * Returns the entity's max hp.
	 * @return the entity's max hp
	 */
	public int getMaxHp() {
		return maxHp;
	}
	
	/**
	 * Reduces the entity's hp by {@code hit}. Returns the result.
	 * @param hit amount of damage
	 * @return the entity's hp after the hit
	 */
	public int loseHp(int hit) {
		return hp -= hit;
	}
	
	/**
	 * Increases the entity's hp by {@code heal}. Returns the result.
	 * @param heal amount of hp
	 * @return the entity's hp after the heal
	 */
	public int gainHp(int heal) {
		return hp += heal;
	}
	
	/**
	 * Increases the entity's hp by {@code maxHp}.
	 * @param maxHp amount of hp
	 */
	public void incMaxHp(int maxHp) {
		this.maxHp += maxHp;
	}
	
}
