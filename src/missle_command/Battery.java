package missle_command;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Battery extends Rectangle {
	
	private boolean active;
	
	public Battery(int x, int y) {
		this.setRect(x, y, 35, 35);
		active = true;
	}

	public void draw(Graphics g) {
		g.fillRect(x, y, width, height);
	}
	
	public Point getLocPoint() {
		return getLocation();
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}
