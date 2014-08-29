package physics.rigidbody;

import entities.GameObject;
import input.InputHandler;

public abstract class Rigidbody {
	protected InputHandler inputHandler;
	protected GameObject attachedTo;
	protected int speed;
	
	public Rigidbody(InputHandler inputHandler, GameObject attachedTo) {
		this.inputHandler = inputHandler;
		this.attachedTo = attachedTo;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public abstract void update(long delta);
}
