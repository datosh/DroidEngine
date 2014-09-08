package physics.collision;

import java.awt.Graphics;

import util.Point;
import entities.GameObject;

public class ColliderBox extends Collider {
	private double width, height;
	
	/*
	 * CONSTRUCTORS
	 */
	
	/**
	 * Creates a ColliderBox with the offset {0, 0} and the width and height = 0. 
	 * These values can be set later on using the setter methods. 
	 * @param attachedTo The GameObject the ColliderBox is attached to. 
	 */
	public ColliderBox(GameObject attachedTo) {
		this(0, 0, 0, 0, attachedTo);
	}
	
	/**
	 * Creates a ColliderBox and attaches is to the specified GameObject
	 * @param offset offset from the position of the attached GameObject
	 * @param width the width of the ColliderBox
	 * @param height the height of the ColliderBox
	 * @param attachedTo the gameObject the ColliderBox is attached to
 	 */
	public ColliderBox(Point offset, double width, double height, GameObject attachedTo) {
		this(offset.getX(), offset.getY(), width, height, attachedTo);
	}
	
	/**
	 * Creates a ColliderBox and attaches is to the specified GameObject
	 * @param offX x offset from the position of the attached GameObject
	 * @param offY y offset from the position of the attached GameObject
	 * @param width the width of the ColliderBox
	 * @param height the height of the ColliderBox
	 * @param attachedTo the GameObject the ColliderBox is attached to
	 */
	public ColliderBox(double offX, double offY, double width, double height, GameObject attachedTo) {
		this.offX = offX;
		this.offY = offY;
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
		//Use Axis Aligned Bounding Box Collision (AABB-collision) for Box<->Box collision
		if(c instanceof ColliderBox) {
			ColliderBox cb = (ColliderBox)c;
			double x = attachedTo.getX() + offX;
			double y = attachedTo.getY() + offY;
			double cbx = cb.attachedTo.getX() + cb.offX;
			double cby = cb.attachedTo.getY() + cb.offY;
			return !(x + this.width < cbx || cbx + cb.width < x || y + this.height < cby || cby + cb.height < y);
		}
		return false;
	}

	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect((int)(attachedTo.getX() + offX), (int)(attachedTo.getY() + offY), (int)width, (int)height);
	}

	@Override
	public void update(long delta) {

	}

	
}
