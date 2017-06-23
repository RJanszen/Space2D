// Let there be shooting stars, the stars move from north to south //

package environment;

import java.awt.Color;
import java.awt.Graphics;
import view.Window;

public class Air {
	
	// FIELDS //
	private int x;
	private double y;
	private double length;
	protected double speed;
	protected double originSeed;
	protected Window frame;
	protected Color color;
	protected double normalizeSpeed;
	
	// CONSTRUCTOR //
	public Air(Window frame, int z) {
		this.frame = frame;
		this.setX((int) (Math.random() * frame.getWindowWidth())); // Random horizontal position
		this.setY(Math.random() * frame.getWindowHeight()); // Random vertical position
		this.originSeed = 5 + Math.random() * 30; // Random origin length
		this.setLength(this.originSeed);
		this.speed = 4 + Math.random() * 2; // Random speed
		int cI = (int) (Math.random() * 255); // Color random greyscale
		color = new Color(cI, cI, cI);
	}
	
	// GETTERS AND SETTERS //
	public double getLength() {
		return length;
	}

	public void setLength(double d) {
		this.length = (int) d;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}	
	
	// Update speed by multiplying the original seed //
	public void updateSpeed(double speed, int timeMS) {
		this.length = speed * originSeed;
	}
	
	// Get X and Y positions for this star, fire the draw function //
	public void update(Graphics g) {
		this.setY(getY() + speed * frame.getFlightSpeed());
		if (this.getY() - speed > frame.getWindowHeight()) {
			this.setX((int) (Math.random() * frame.getWindowWidth()));
			this.setY(0);
		}
		draw(g);
	}
	
	// Draw function in the graphics originated of Window.java (or this.frame) //
	protected void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(getX(), (int) getY(), getX(), this.getLength() > 0 ? (int) (getY() + getLength()) : (int) getY());
	}
}
