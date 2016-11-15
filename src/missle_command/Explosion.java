package missle_command;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Explosion extends Rectangle {

	private boolean expanding;
	private boolean active;
	
	public Explosion(Point p) {
		this.setLocation(p);
		active = true;
		expanding = true;
		Panel.play("src/missle_command/sound/explosion_01.wav");
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillOval(x - (width/2), y - (height/2), width, height);
	}
	
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
	
	public boolean isActive() {
		return active;
	}
	
	public boolean contains(Rectangle r) {
		return intersects(r);
	}

}
