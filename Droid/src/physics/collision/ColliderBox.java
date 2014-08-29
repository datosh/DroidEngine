package physics.collision;

import java.awt.Graphics;

import util.Point;
import entities.GameObject;

public class ColliderBox extends Collider {
	private double width, height;
	
	/*
	 * CONSTRUCTORS
	 */
	
	public ColliderBox(GameObject attachedTo) {
		x = 0;
		y = 0;
		width = 0;
		height = 0;
		this.attachedTo = attachedTo;
	}
	
	public ColliderBox(Point pos, double width, double height, GameObject attachedTo) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.width = width;
		this.height = height;
		this.attachedTo = attachedTo;
	}
	
	public ColliderBox(double x, double y, double width, double height, GameObject attachedTo) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.attachedTo = attachedTo;
	}
	
	/*
	 * GETTER & SETTER
	 */
	
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public GameObject getAttachedTo() {
		return attachedTo;
	}

	/*
	 * OTHER METHODS
	 */
	
	@Override
	public boolean overlaps(Collider c) {
		//Use Axis Aligned Bounding Box Collision (AABB-collision)
		if(c instanceof ColliderBox) {
			ColliderBox cb = (ColliderBox)c;
			return !(this.x + this.width < cb.x || cb.x + cb.width < x || this.y + this.height < cb.y || cb.y + cb.height < y);
		}
		return false;
	}

	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void update(long delta) {
		this.setPosition(attachedTo.getPosition());
	}

	
}
