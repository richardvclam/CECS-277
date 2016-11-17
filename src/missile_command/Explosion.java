package missile_command;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Explosion class that draws the graphical representation and detects for collisions.
 * @author rvclam
 *
 */
public class Explosion extends Rectangle {

	/**
	 * Determines whether the explosion is still expanding
	 */
	private boolean expanding;
	/**
	 * Determines whether the explosion is still active
	 */
	private boolean active;
	
	/**
	 * Constructor
	 * Instantiates an Explosion object at the determined {@code Point p} location and
	 * plays a .wav sound.
	 * @param p is the Point location
	 */
	public Explosion(Point p) {
		this.setLocation(p);
		active = true;
		expanding = true;
		Panel.play("src/missile_command/sound/explosion_01.wav");
	}
	
	/**
	 * Draws the Explosion image to the panel.
	 * @param g is the graphics panel
	 */
	public void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillOval(x - (width/2), y - (height/2), width, height);
	}
	
	/**
	 * Transforms the explosion image depending on whether it is expanding or not.
	 * If the explosion is expanding, it'll transform the image into a larger circle. 
	 * It'll also transform the image into a smaller circle while it is not expanding. 
	 */
	public void move() {
		if (expanding) {
			width += 5;
			height += 5;
			if (width > 50) {
				expanding = false;
			}
		} else {
			width -= 5;
			height -= 5;
			if (width <= 0) {
				active = false;
			}
		}
	}
	
	/**
	 * Checks if the explosion is still active.
	 * @return active status of the explosion
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Checks if the explosion collides with another Rectangle object.
	 * @param r is the Rectangle to check collision with
	 */
	public boolean contains(Rectangle r) {
		return intersects(r);
	}

}
