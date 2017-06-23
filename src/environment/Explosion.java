// Let there be explosions when the projectiles collide with a booster (blue ball) //
package environment;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Explosion {
	
	// FIELDS //
	private Color color;
	private ArrayList<Point> points = new ArrayList<Point>();

	// CONSTRUCTOR //
	public Explosion (double originX, double originY, double span, int layers, int particles) {
		this.color = Color.white;
		double angle = 0;
		for (int i = 0; i < layers; i++) { // For each layer and particle, create a new location AND angle of the vector (the speed depends on the layer) //
			for (int j = 0; j < particles; j++) {
				points.add(new Point(originX, originY, angle, i));
				angle += 360/particles;
			}
		}
	}
	
	// Draw each exploding point which belongs to this explosion //
	public void draw(Graphics g) {
		g.setColor(this.color);
		for (Point point : points) {
			g.setColor(point.getColor()); // Get random color, because rainbows are awesome //
			point.update();
			int x = (int) point.getX();
			int y = (int) point.getY();
			g.fillOval(x, y, (int) (Math.random() * 2 + 3), (int) (Math.random() * 2 + 3)); // Draw the particles as small circles //
		}
	}
}