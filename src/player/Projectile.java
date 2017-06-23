package player;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import environment.Explosion;
import objects.Booster;

public class Projectile {
	private int x;
	private double y;
	private int length;
	private double speed;
	private Ship spaceship;
	private Explosion explosion;
	
	public Projectile(int x, int y, Ship spaceship) {
		this.x = x;
		this.y = y;
		this.spaceship = spaceship;
		this.speed = 10;
		this.length = 50;
	}
	
	public void update(Graphics g) {
		this.y = y - speed;
		
		if ((int) this.y + length < 0)
			spaceship.recycleProjectile(this);
		
		draw(g);
	}

	private void draw(Graphics g) {
		if (explosion != null) explosion.draw(g);
		g.setColor(Color.red);
		g.fillRect(x, (int) y, 5, length);
	}
	
	private boolean isCollideHor(int projX, Booster booster) {
		if ((projX + 5) >= booster.getX() && projX <= (booster.getX() + booster.getLength())) return true;
		else return false;
	}
	
	private boolean isCollideVer(double projY, Booster booster) {
		if (projY <= (booster.getY()+booster.getLength()/2) && projY >= (booster.getY() - booster.getLength()/2)) return true;
		else return false;
	}
	
	public void isCollideBooster(ArrayList<Booster> boosters) {
		for (Booster booster : boosters) {
			if (isCollideHor(this.x, booster) && isCollideVer(this.y, booster)) {
				spaceship.getFrame().recycleBooster(booster);
//				spaceship.getFrame().changeFlightSpeed(1, 2000);
				explosion = new Explosion(booster.getX(), booster.getY(), 100, 5,100);
			}
		}
	}
	
}
