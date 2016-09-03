package project_one.entities;

public class Entity {

	private String name;
	private int hp;
	private int maxHp;
	
	public Entity(String name, int maxHp) {
		this.name = name;
		this.maxHp = maxHp;
		hp = maxHp;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int getMaxHp() {
		return maxHp;
	}
	
	public int loseHp(int hit) {
		return hp -= hit;
	}
	
	public int gainHp(int heal) {
		return hp += heal;
	}
	
	public void incMaxHp(int maxHp) {
		this.maxHp += maxHp;
	}
	
}
