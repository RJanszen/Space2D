package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import environment.Air;
import view.Window;

public class Booster extends Air {
	
	protected double cooldown;
	public static ArrayList<Booster> boosters;
	public boolean recycle;

	public Booster(Window frame, int z) {
		super(frame, z);
		this.setX((int) (50 + Math.random() * 600));
		this.setY(-10);
		this.color = Color.blue;
		this.setLength(50);
	}
	
	public void update(Graphics g) {
		this.setY(getY() + speed * frame.getFlightSpeed());
		if (this.getY() - speed > frame.getWindowHeight()) {
			this.recycle = true;
		} 
		draw(g);
	}
	
	protected void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(getX(), (int) getY(), (int) getLength(), (int) getLength());
//		g.drawLine(x, (int) y, x, this.length > 0 ? (int) (y + length) : (int) y);
	}
	
	protected void explode(Graphics g) {
		g.drawLine(getX(), (int) getY(), getX(), (int) getY());
		
	}
}
