package missle_command;

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

public class Missle {
	
	private Point start;
	private Point location;
	private Point end;
	private int speed;
	private double moveAmtX;
	private double moveAmtY;
	private int ticksElapsed;
	private int type;
	private Color color;
	private boolean active;
	private BufferedImage img;
	private AffineTransform at;

	public Missle(Point start, Point end, int speed, int type, Color color) {
		try {
			img = ImageIO.read(new File("src/missle_command/img/missle_00.png"));
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
			//Panel.play("src/missle_command/sound/att_missle_01.wav");
			//rotation = Math.PI / 2;
		} else {
			this.end = end;
			Panel.play("src/missle_command/sound/def_missle_02.wav");
			//rotation = Math.PI * 3 / 2;
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
		//System.out.println(moveAmtX);
		//System.out.println(moveAmtY);
		moveAmtX = moveAmtX * speed / magnitude;
		moveAmtY = moveAmtY * speed / magnitude;
		/*
		System.out.println(moveAmtX);
		System.out.println(moveAmtY);
		System.out.println("Magnitude: " + magnitude);
		*/
		active = true;
	}
	
	private BufferedImage createTransformed(BufferedImage image, AffineTransform at) {
        BufferedImage newImage = new BufferedImage(
            image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		//g.fillRect((int) location.getX() - 1, (int) location.getY(), 3, 3);
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
	
	public Point getLocPoint() {
		return location;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public int getType() {
		return type;
	}
}
