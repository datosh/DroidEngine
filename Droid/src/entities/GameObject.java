package entities;

import java.awt.Graphics;

import games.Game;
import gfx.animation.Animator;
import gfx.render.Sprite;
import ai.Routine;
import physics.collision.Collider;
import physics.rigidbody.Rigidbody;
import util.Point;

public abstract class GameObject {
	protected enum STATE {
		WALKING, RUNNING, FALLING, STANDING
	}
	
	protected boolean alive;
	protected double x, y;
	//protected World world; ??
	protected Rigidbody rigidbody;
	protected Collider collider;
	protected Routine routine;
	protected Sprite sprite;

	protected Animator animator;
	protected Game game;
	
	/*
	 * GETTER & SETTER 
	 */
	
	public boolean isAlive() {
		return alive;
	}
	public Point getPosition() {
		return new Point(x, y);
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public Collider getCollider() {
		return collider;
	}
	public void setCollider(Collider collider) {
		this.collider = collider;
	}
	public Routine getRoutine() {
		return routine;
	}
	public void setRoutine(Routine routine) {
		this.routine = routine;
	}
	public Sprite getSprite() {
		return sprite;
	}
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	public Rigidbody getRigidbody() {
		return rigidbody;
	}
	public void setRigidbody(Rigidbody rigidbody) {
		this.rigidbody = rigidbody;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	public void draw(Graphics g) {
		if(sprite != null) {
			sprite.draw((int)x, (int)y, g);
		}
		
		if(collider != null) {
			collider.draw(g);
		}
	}
	
	public void update(long delta) {
		if(collider != null) {
			collider.update(delta);
		}
		if(rigidbody != null) {
			rigidbody.update(delta);
		}
		if(routine != null) {
			if(routine.getState() == null) {
				routine.start();
			}
			routine.act(delta, this);
		}
	}
}
