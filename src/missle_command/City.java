package missle_command;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class City extends Rectangle {
	
	private boolean active;

	public City(int x, int y) {
		this.setRect(x, y, 30, 20);
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
	
	public boolean isHit(Point p) {
		if (contains(p)) {
			active = false;
		}
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}
