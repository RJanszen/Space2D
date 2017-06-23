// Particles which belong to the explosions //
package environment;

import java.awt.Color;

public class Point {
	
	// FIELDS //
	private double x;
	private double y;
	private double angle;
	private int layer;
	private Color color;
	private double[] vector = new double[2];
	
	// CONSTRUCTOR //
	public Point(double originX, double originY, double angle, int layer) {
		this.x = originX;
		this.y = originY;
		this.angle = angle;
		this.layer = layer;
		this.color = Color.getHSBColor((float) Math.random() * 255, (float) Math.random() * 255, 255);
		calculateVector();
	}

	// GETTERS AND SETTERS //
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	
	// Calculate a new vector (change in x and y as a function of time, angle, and speed (layer)) //
	private void calculateVector() {
		this.vector[0] = Math.cos(Math.toRadians(this.angle)) / (Math.random() * 0.2 * layer);
		this.vector[1] = Math.sin(Math.toRadians(this.angle)) / (Math.random() * 0.2 * layer);
	}
	
	// Update x and y to their new position //
	public void update() {
		x += vector[0];
		y += vector[1];
		y *= 1.00;
	}
	
}
