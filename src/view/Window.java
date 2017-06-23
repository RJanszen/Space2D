package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import environment.Air;
import objects.Booster;
import player.Projectile;
import player.Ship;

public class Window extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	private int windowWidth;
	private int windowHeight;
	protected double flightSpeed;
	private Ship spaceship;
	private Controls controls;
	private ArrayList<Air> air = new ArrayList<Air>();
	private ArrayList<Booster> boosters = new ArrayList<Booster>();
	private ArrayList<Booster> recycleBoosters = new ArrayList<Booster>();
	private double boosterCooldown;

    public static void main(String[] args) throws InterruptedException, IOException {
    	JFrame frame = new JFrame("Space 2D");
        Window test = new Window(700, 1000);
        frame.add(test);
        frame.setSize(700, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(test);
        
        test.makeAir(50);
        
        System.out.println("Window created: \nWidth\t" + test.windowWidth + "\nHeight\t" + test.windowHeight);
        while(true) {
        	Thread.sleep(10);
        	test.repaint();
        }
    }
    
	public Window(int width, int height) throws IOException {
		windowWidth = width;
		windowHeight = height;
		flightSpeed = 1;
		spaceship = new Ship(this, 350, 740);
		controls = new Controls(this, spaceship);
    }
	
	public int getWindowWidth() {
		return this.windowWidth;
	}
	
	public int getWindowHeight() {
		return this.windowHeight;
	}
	
	public double getFlightSpeed() {
		return this.flightSpeed;
	}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, windowWidth, windowHeight);
        g2d.setColor(Color.green);
        g2d.drawString("Controls:", 10, 30);
        g2d.drawString("Moving (w,a,s,d)", 10, 50);
        g2d.drawString("Adjust flight speed (q,e)", 10, 70);
        g2d.drawString("Shoot (spacebar)", 10, 90);
        
        updateAir(g2d);
        updateBoosters(g2d);
        updateCollisions();
        
        updateSpaceship(g2d);
        controls.updateControls();
    }
    
    private void makeAir(int density) {
    	for (int i = 0; i < density; i++) {
    		air.add(new Air(this, 1));
    	}
    }
    
    private void updateAir(Graphics g) {
    	for (Air stripe : air) {
    		stripe.update(g);
    	}
    }
    
    private void updateBoosters(Graphics g) {
    	boosters.removeAll(recycleBoosters);
    	recycleBoosters.clear();
    	
    	if (System.currentTimeMillis() > boosterCooldown) {
    		boosters.add(new Booster(this, 1));
    		boosterCooldown = System.currentTimeMillis() + 3000;
    	}
    	for (Booster booster : boosters) {
    		if (booster.recycle == true) recycleBoosters.add(booster);
    		else booster.update(g);
    	}
    }
    
    private void updateCollisions() {
    	for (Projectile proj : spaceship.getProjectiles()) {
    		proj.isCollideBooster(boosters);
    	}
    }
    
    private void updateSpaceship(Graphics g) {
    	spaceship.updateProjectiles(g);
    	int spaceshipX = (int) spaceship.getX();
    	int spaceshipY = (int) spaceship.getY();
    	g.drawImage(spaceship.getImage(), spaceshipX, spaceshipY, null);
    	if (spaceship.isForward()) {
    		g.drawImage(spaceship.getExhaust(), (spaceshipX + 50), (spaceshipY + 180), 50, 100,  null);
    		g.drawImage(spaceship.getExhaust(), (spaceshipX + 110), (spaceshipY + 180), 50, 100,  null);
    	}
    }
    
    public void changeFlightSpeed(double change, int timeMS) {
    	
    	this.flightSpeed += change;
    	for (Air stripe : air) {
    		stripe.updateSpeed(this.flightSpeed, timeMS);
    	}
    }

	public void recycleBooster(Booster booster) {
		if (boosters.contains(booster)) recycleBoosters.add(booster);
	}
    

    @Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			controls.pressKey("w");
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			controls.pressKey("a");
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			controls.pressKey("s");
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			controls.pressKey("d");
		} else if (e.getKeyCode() == KeyEvent.VK_Q) {
			controls.pressKey("q");
		} else if (e.getKeyCode() == KeyEvent.VK_E) {
			controls.pressKey("e");
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			controls.pressKey("spc");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			controls.releaseKey("w");
		else if (e.getKeyCode() == KeyEvent.VK_A)
			controls.releaseKey("a");
		else if (e.getKeyCode() == KeyEvent.VK_S)
			controls.releaseKey("s");
		else if (e.getKeyCode() == KeyEvent.VK_D)
			controls.releaseKey("d");
		else if (e.getKeyCode() == KeyEvent.VK_Q)
			controls.releaseKey("q");
		else if (e.getKeyCode() == KeyEvent.VK_E)
			controls.releaseKey("e");
		else if (e.getKeyCode() == KeyEvent.VK_SPACE)
			controls.releaseKey("spc");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
