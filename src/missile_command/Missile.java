package missile_command;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Missile class that draws the graphical representation, transforms the image according
 * to the angle of the missile, calculates the speed, and moves the missile position.
 * @author rvclam
 *
 */
public class Missile {
	
	/**
	 * Start location of the missile
	 */
	private Point start;
	/**
	 * Current location of the missile
	 */
	private Point location;
	/**
	 * End target location of the missile
	 */
	private Point end;
	/**
	 * Speed of the missile
	 */
	private int speed;
	/**
	 * X distance between the end and start points
	 */
	private double moveAmtX;
	/**
	 * Y distance between the end and start points
	 */
	private double moveAmtY;
	/**
	 * Number of frames that have elapsed
	 */
	private int ticksElapsed;
	/**
	 * Determines whether it is an attacker or defender missile
	 */
	private int type;
	/**
	 * Color of the missile
	 */
	private Color color;
	/**
	 * Determines whether the misile is still active
	 */
	private boolean active;
	/**
	 * Missile's image file
	 */
	private BufferedImage img;
	/**
	 * Transformation
	 */
	private AffineTransform at;

	/**
	 * Constructor
	 * Instantiates a Missile object at the determined start point and end point. It also
	 * calculates the move distances, speed, velocity of the missile. Using the move
	 * distances, it uses the cotangent function to calculate degree of the missile to
	 * transform the image accordingly.
	 * @param start
	 * @param end
	 * @param speed
	 * @param type
	 * @param color
	 */
	public Missile(Point start, Point end, int speed, int type, Color color) {
		try {
			img = ImageIO.read(new File("src/missile_command/img/missle_00.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		this.start = start;
		this.location = new Point((int) start.getX(), (int) start.getY());
		this.color = color;
		this.type = type;
		double rotation;
		if (type == 1)  {
			this.end = new Point(end.x + 15, end.y);
		} else {
			this.end = end;
			Panel.play("src/missile_command/sound/def_missle_02.wav");
		}

		moveAmtX = this.end.getX() - start.getX();
		moveAmtY = this.end.getY() -  start.getY();

		rotation = Math.atan(moveAmtY/moveAmtX);
		if (moveAmtX < 0) {
			rotation += Math.PI;
		}
		at = AffineTransform.getRotateInstance(rotation, img.getWidth()/2, img.getHeight()/2);
		img = createTransformed(img, at);
		double magnitude = Math.sqrt((moveAmtX * moveAmtX) + (moveAmtY * moveAmtY));
		moveAmtX = moveAmtX * speed / magnitude;
		moveAmtY = moveAmtY * speed / magnitude;
		/*
		System.out.println(moveAmtX);
		System.out.println(moveAmtY);
		System.out.println("Magnitude: " + magnitude);
		*/
		active = true;
	}
	
	/**
	 * Creates a transformed image based on the rotation effect applied.
	 * @param image is the image to apply the transformation to
	 * @param at is the transformation effect
	 * @return the new transformed image
	 */
	private BufferedImage createTransformed(BufferedImage image, AffineTransform at) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
	}
	
	/**
	 * Draws the Missile image, the missile travel line, and missile target cross.
	 * @param g is the graphics panel
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawImage(img, location.x - (img.getWidth()/2), location.y , null);
		
		if (type == 0) {
			g.setColor(Color.RED);
			g.drawLine(end.x - 5, end.y - 5, end.x + 5, end.y + 5);
			g.drawLine(end.x + 5, end.y - 5, end.x - 5, end.y + 5);
			g.drawLine(start.x, start.y, location.x, location.y);
		} else {
			g.setColor(color);
			g.drawLine(start.x, start.y, location.x, location.y);
		}
	}
	
	/**
	 * Updates the missile location based on the number of frame refreshes.
	 */
	public void move() {
		ticksElapsed++;
		location.setLocation(start.getX() + (moveAmtX * ticksElapsed), start.getY() + (moveAmtY * ticksElapsed));
		if (type == 0) {
			if (location.y < end.y) {
				active = false;
			}
		} else {
			if (location.y > end.y) {
				active = false;
			}
		}
	}
	
	/**
	 * Returns the Point location.
	 * @return the Point location
	 */
	public Point getLocPoint() {
		return location;
	}
	
	/**
	 * Checks if the structure is still active.
	 * @return active status of the structure
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Returns the missile type.
	 * @return the missile type
	 */
	public int getType() {
		return type;
	}
}
