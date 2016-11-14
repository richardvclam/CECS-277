package missle_command;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

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

	public Missle(Point start, Point end, int speed, int type, Color color) {
		this.start = start;
		this.location = new Point((int) start.getX(), (int) start.getY());
		this.end = end;
		this.color = color;
		this.type = type;
		
		moveAmtX = end.getX() - start.getX();
		moveAmtY = end.getY() -  start.getY();
		double magnitude = Math.sqrt((moveAmtX * moveAmtX) + (moveAmtY * moveAmtY));
		//System.out.println(moveAmtX);
		//System.out.println(moveAmtY);
		moveAmtX = moveAmtX * speed / magnitude;
		moveAmtY = moveAmtY * speed / magnitude;
		System.out.println(moveAmtX);
		System.out.println(moveAmtY);
		System.out.println("Magnitude: " + magnitude);
		active = true;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect((int) location.getX() - 1, (int) location.getY(), 3, 3);
		g.setColor(Color.RED);
		if (type == 0) {
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
		//location.translate((int)moveAmtX, (int)moveAmtY);
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
