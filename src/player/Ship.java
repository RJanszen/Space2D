package player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import view.Window;

public class Ship {
	
	private float x;
	private float y;
	private float horSpeed;
	private float verSpeed;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private ArrayList<Projectile> recycleProjectiles = new ArrayList<Projectile>();
	private BufferedImage graphic;
	private BufferedImage exhaust;
	private BufferedImage shooting;
	private boolean isShooting;
	private double fireRate;
	private Window frame;
	
	public Ship(Window frame, int x, int y) {
		this.frame = frame;
		this.x = x;
		this.y = y;
		this.horSpeed = 0;
		this.verSpeed = 0;
		this.fireRate = 250;
		try {
			this.graphic = ImageIO.read(new File("./img/forward.png"));
			this.exhaust = ImageIO.read(new File("./img/fire.png"));
			this.shooting = ImageIO.read(new File("./img/shooting.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Window getFrame() {
		return this.frame;
	}
	
	public float getX() {
		this.x += horSpeed / 40;
		if (this.x < 0) this.x = 0;
		else if (this.x > 700 - 227) this.x = 700 - 227;
		return this.x;
	}
	
	public int getXloc() {
		return (int) this.x;
	}
	
	public float getY() {
		this.y -= verSpeed / 40;
		if (this.y < 0) this.y = 0;
		else if (this.y > 740) this.y = 740;
		return this.y;
	}
	
	public int getYloc() {
		return (int) this.y;
	}
	
	public double getFireRate() {
		return this.fireRate;
	}
	
	public ArrayList<Projectile> getProjectiles() {
		return this.projectiles;
	}
	
	public BufferedImage getImage() {
		if (isShooting) return this.shooting;
		else return this.graphic;
	}
	
	public BufferedImage getExhaust() {
		return this.exhaust;
	}
	
	public void setShooting(boolean set) {
		this.isShooting = set;
	}
	
	public void moveHor(double speed) {
		if (horSpeed < 0) {
			if (speed > 0) {
				horSpeed = 0;
			} else if (speed == 0) horSpeed = 0;
		} else if (horSpeed > 0) {
			if (speed < 0) {
				horSpeed = 0;
			} else if (speed == 0) horSpeed = 0;
		} else {
			horSpeed = (float) speed;
		}
	}
	
	public void moveVer(double speed) {
		if (verSpeed < 0) {
			if (speed > 0) {
				verSpeed = 0;
			} else if (speed == 0) verSpeed = 0;
		} else if (verSpeed > 0) {
			if (speed < 0) {
				verSpeed = 0;
			} else if (speed == 0) verSpeed = 0;
		} else {
			verSpeed = (float) speed;
		}
	}
	
	public boolean isForward() {
		if (verSpeed > 0) return true;
		else return false;
	}
	
	public void updateProjectiles(Graphics g) {
		projectiles.removeAll(recycleProjectiles);
		recycleProjectiles.clear();
		for (Projectile proj : projectiles) {
			proj.update(g);
		}
	}
	
	public void shoot(Projectile proj) {
		this.projectiles.add(proj);
	}
	
	protected void recycleProjectile(Projectile proj) {
		this.recycleProjectiles.add(proj);
	}
}
