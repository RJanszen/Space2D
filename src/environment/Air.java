package environment;

import java.awt.Color;
import java.awt.Graphics;
import view.Window;

public class Air {
	
	private int x;
	private double y;
	private double length;
	protected double speed;
	protected double originSeed;
	protected Window frame;
	protected Color color;
	protected double normalizeSpeed;
	
	
	public Air(Window frame, int z) {
		this.frame = frame;
		this.setX((int) (Math.random() * frame.getWindowWidth()));
		this.setY(Math.random() * frame.getWindowHeight());
		this.originSeed = 5 + Math.random() * 30;
		this.setLength((int) (5 + Math.random() * 30));
		this.speed = 4 + Math.random() * 2;
		int cI = (int) (Math.random() * 255);
		color = new Color(cI, cI, cI);
	}
	
	public void updateSpeed(double speed, int timeMS) {
		this.length = speed * originSeed;
	}
	
	public void update(Graphics g) {
		this.setY(getY() + speed * frame.getFlightSpeed());
		if (this.getY() - speed > frame.getWindowHeight()) {
			this.setX((int) (Math.random() * frame.getWindowWidth()));
			this.setY(0);
		}
		draw(g);
	}
	
	protected void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(getX(), (int) getY(), getX(), this.getLength() > 0 ? (int) (getY() + getLength()) : (int) getY());
	}

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
}
