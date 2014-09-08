package physics.collision;

import java.awt.Color;
import java.awt.Graphics;

import entities.GameObject;
import util.Point;

public abstract class Collider {
	protected Color color = Color.GREEN;
	protected double offX, offY;
	protected GameObject attachedTo;
	
	public double getOffX() {
		return offX;
	}
	
	public double getOffY() {
		return offY;
	}
	
	public Point getOffset() {
		return new Point(offX, offY);
	}
	
	public void setOffset(Point p) {
		this.offX = p.getX();
		this.offY = p.getY();
	}
	
	public void setOffset(double x, double y) {
		this.offX = x;
		this.offY = y;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public abstract boolean overlaps(Collider c);
	public abstract void draw(Graphics g);
	public abstract void update(long delta);
	
}
