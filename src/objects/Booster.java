// Booster (aka the blue balls randomly appearing in your screen and exploding when hit with projectiles) //
package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import environment.Air;
import view.Window;

public class Booster extends Air {
	
	// FIELDS //
	protected double cooldown;
	public static ArrayList<Booster> boosters;
	public boolean recycle;

	// CONSTRUCTOR //
	public Booster(Window frame, int z) {
		super(frame, z);
		this.setX((int) (50 + Math.random() * 600));
		this.setY(-10);
		this.color = Color.blue;
		this.setLength(50);
	}
	
	// Update the booster y location as a function of previous location and speed //
	public void update(Graphics g) {
		this.setY(getY() + speed * frame.getFlightSpeed());
		if (this.getY() - speed > frame.getWindowHeight()) { // Recycle the booster when it's out of scope //
			this.recycle = true;
		} 
		draw(g);
	}
	
	// Draw in g as oval //
	protected void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(getX(), (int) getY(), (int) getLength(), (int) getLength());
//		g.drawLine(x, (int) y, x, this.length > 0 ? (int) (y + length) : (int) y);
	}
}
