package project_one.types;

public interface Electric {
	
	static final int TYPE = 3;
	static final String[] TYPE_MENU = {"Thunder Shock", "Thunder Bolt", "Thunder Punch"};
	
	int thunderShock();
	
	int thunderBolt();
	
	int thunderPunch();

}
