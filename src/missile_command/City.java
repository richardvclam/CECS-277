package missile_command;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * City class that draws the graphical representation, and detects for collisions.
 * @author rvclam
 *
 */
public class City extends Rectangle {
	
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
	 * Instantiates a City object at the determined {@code x} and {@code y} location.
	 * @param x is the x-coordinate
	 * @param y is the y-coordinate
	 */
	public City(int x, int y) {
		try {
			structure = ImageIO.read(new File("src/missile_command/img/house_11.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		setRect(x, y, structure.getWidth(), structure.getHeight());
		active = true;
	}
	
	/**
	 * Draws the City image to the panel.
	 * @param g is the graphics panel
	 */
	public void draw(Graphics g) {
		g.drawImage(structure, x, y, null);
	}
	
	/**
	 * Returns the Point location.
	 * @return the Point location
	 */
	public Point getLocPoint() {
		return getLocation();
	}
	
	/**
	 * Checks if the structure is still active.
	 * @return active status of the structure
	 */
	public boolean isActive() {
		return active;
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

}
