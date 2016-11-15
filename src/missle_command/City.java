package missle_command;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class City extends Rectangle {
	
	private boolean active;
	private BufferedImage structure;

	public City(int x, int y) {
		try {
			structure = ImageIO.read(new File("src/missle_command/img/house_11.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		//this.setRect(x, y, 30, 20);
		setRect(x, y, structure.getWidth(), structure.getHeight());
		active = true;
	}
	
	public void draw(Graphics g) {
		//g.fillRect(x, y, width, height);
		g.drawImage(structure, x, y, null);
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
