package missile_command;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Battery class that draws the graphical representation, stores the number of missiles, 
 * removes a missile each time the player shoots, and detects for collisions.
 * @author rvclam
 *
 */
public class Battery extends Rectangle {
	
	/**
	 * Number of missiles left
	 */
	private int numMissiles;
	/**
	 * Determines whether the structure is still active
	 */
	private boolean active;
	/**
	 * Structure's image file
	 */
	private BufferedImage structure;
	
	/**
	 * Constructor
	 * Instantiates a Battery object at the determined {@code x} and {@code y} location.
	 * @param x is the x-coordinate
	 * @param y is the y-coordinate
	 */
	public Battery(int x, int y) {
		try {
			structure = ImageIO.read(new File("src/missile_command/img/battery_1.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		setRect(x, y, structure.getWidth(), structure.getHeight());
		active = true;
		numMissiles = 10;
	}

	/**
	 * Draws the Battery image and number of missiles to the panel.
	 * @param g is the graphics panel
	 */
	public void draw(Graphics g) {
		g.drawImage(structure, x, y, null);
		g.setColor(Color.WHITE);
		g.drawString("" + numMissiles, x + 11, y + 15);
	}
	
	/**
	 * Returns the Point location.
	 * @return the Point location
	 */
	public Point getLocPoint() {
		return getLocation();
	}
	
	/**
	 * Checks if the structure has collided with another point. Sets
	 * the structure active status to false if collided.
	 * @param p is the Point location
	 * @return collision status
	 */
	public boolean isHit(Point p) {
		if (contains(p)) {
			active = false;
		}
		return active;
	}
	
	/**
	 * Returns the number of missiles left.
	 * @return the number of missiles left.
	 */
	public int getNumMissles() {
		return numMissiles;
	}
	
	/**
	 * Removes one missile.
	 */
	public void removeMissle() {
		numMissiles--;
	}
	
	/**
	 * Checks if the structure is still active.
	 * @return active status of the structure
	 */
	public boolean isActive() {
		return active;
	}

}
