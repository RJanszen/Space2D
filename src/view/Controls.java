package view;

import java.util.HashMap;
import java.util.Map;

import player.Projectile;
import player.Ship;

public class Controls {

	Map<String, Integer> keys = new HashMap<String, Integer>();
	private Ship spaceship;
//	private Window frame;
	private double cooldown;
	
	public Controls(Window frame, Ship spaceship) {
//		this.frame = frame;
		this.spaceship = spaceship;
		keys.put("w", 0);
		keys.put("a", 0);
		keys.put("s", 0);
		keys.put("d", 0);
		keys.put("q", 0);
		keys.put("e", 0);
		keys.put("spc", 0);
	}
	
	public void pressKey(String key) {
		keys.put(key, 1);
	}
	
	public void releaseKey(String key) {
		keys.put(key, 0);
	}
	
	public boolean getKey(String key) {
		if (keys.get(key) == 1) return true;
		else return false;
	}
	
	public void updateControls() {
		// Forward - Backward
		if (keys.get("w") == 1)
			spaceship.moveVer(200);
		if (keys.get("s") == 1)
			spaceship.moveVer(-200);
		else if (keys.get("s") == 0 && keys.get("w") == 0) spaceship.moveVer(0);
		
		// Left - Right
		if (keys.get("a") == 1)
			spaceship.moveHor(-200);
		if (keys.get("d") == 1)
			spaceship.moveHor(200);
		else if (keys.get("d") == 0 && keys.get("a") == 0) spaceship.moveHor(0);
		
		// Change flightspeed
		if (keys.get("q") == 1)
			if (spaceship.getFrame().getFlightSpeed() > 0.1)
				spaceship.getFrame().changeFlightSpeed(-0.1, 1000);
		if (keys.get("e") == 1)
			if (spaceship.getFrame().getFlightSpeed() < 5)
				spaceship.getFrame().changeFlightSpeed(0.1, 1000);
		
		// Shoot
		if (keys.get("spc") == 1) {
			if (System.currentTimeMillis() > this.cooldown) {
				spaceship.setShooting(true);
				spaceship.shoot(new Projectile(spaceship.getXloc() + 75, spaceship.getYloc() + 15, spaceship));
				spaceship.shoot(new Projectile(spaceship.getXloc() + 135, spaceship.getYloc() + 15, spaceship));
				this.cooldown = System.currentTimeMillis() + spaceship.getFireRate();
			}

		} else if (keys.get("spc") == 0) spaceship.setShooting(false);
	}
	
}
