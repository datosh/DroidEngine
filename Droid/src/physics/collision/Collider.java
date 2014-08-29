package physics.collision;

import java.awt.Color;
import java.awt.Graphics;

import entities.GameObject;
import util.Point;

public abstract class Collider {
	protected Color color = Color.GREEN;
	protected double offX, offY;
	protected double x, y;
	protected GameObject attachedTo;
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setPosition(Point p) {
		this.x = p.getX();
		this.y = p.getY();
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public abstract boolean overlaps(Collider c);
	public abstract void draw(Graphics g);
	public abstract void update(long delta);
	
}
