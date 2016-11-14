package missle_command;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Battery extends Rectangle {
	
	private int numMissles;
	private boolean active;
	
	public Battery(int x, int y) {
		this.setRect(x, y, 35, 35);
		active = true;
		numMissles = 10;
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
		g.drawString("" + numMissles, x + 11, y + 15);
	}
	
	public Point getLocPoint() {
		return getLocation();
	}
	
	public boolean isHit(Point p) {
		if (contains(p)) {
			active = false;
		}
		return active;
	}
	
	public int getNumMissles() {
		return numMissles;
	}
	
	public void removeMissle() {
		numMissles--;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}
