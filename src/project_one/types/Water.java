package project_one.types;

public interface Water {
	
	static final int TYPE = 1;
	static final String[] TYPE_MENU = {"Water Gun", "Bubble Beam", "Waterfall"};
	
	int waterGun();
	
	int bubbleBeam();
	
	int waterfall();

}
