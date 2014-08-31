package entities;

import java.awt.Graphics;

import games.Game;
import games.World;
import gfx.animation.Animator;
import ai.Routine;
import physics.collision.Collider;
import physics.rigidbody.Rigidbody;
import util.Point;

public abstract class GameObject {
	protected boolean alive;
	protected double x, y;
	protected Game game;
	
	protected World world;
	protected Rigidbody rigidbody;
	protected Collider collider;
	
	protected Routine routine;

	protected Animator animator;
	protected STATE state;
	
	/**
	 * Every GameObject has to be linked to a game
	 * @param game The Game this object is in
	 */
	public GameObject(Game game) {
		this.game = game;
		this.state = STATE.DEFAULT;
	}
	
	/*
	 * GETTER & SETTER 
	 */
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
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

	public Point getPosition() {
		return new Point(x, y);
	}
	
	public void setPosition(Point p) {
		this.x = p.getX();
		this.y = p.getY();
	}
	
	public Game getGame() {
		return game;
	}

	//Has to be set in the contructor
//	public void setGame(Game game) {
//		this.game = game;
//	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Rigidbody getRigidbody() {
		return rigidbody;
	}

	public void setRigidbody(Rigidbody rigidbody) {
		this.rigidbody = rigidbody;
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

	public Animator getAnimator() {
		return animator;
	}

	public void setAnimator(Animator animator) {
		this.animator = animator;
	}
	
	public STATE getState() {
		return state;
	}
	
	public void setState(STATE state) {
		this.state = state;
	}
	
	/*
	 * DRAW & UPDATE
	 */

	public void draw(Graphics g) {
		if(animator != null) {
			animator.draw((int)x, (int)y, g);
		}
		
		if(collider != null && game.DEBUG) {
			collider.draw(g);
		}
	}
	
	public void update(long delta) {
		if(rigidbody != null) {
			rigidbody.update(delta);
		}
		if(routine != null) {
			if(routine.getState() == null) {
				routine.start();
			}
			routine.act(delta, this);
		}
		if(collider != null) {
			collider.update(delta);
		}
		if(animator != null) {
			animator.update(delta);
		}
	}
}
