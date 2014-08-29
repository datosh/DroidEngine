package ai;

import entities.GameObject;

/**
 * Moves to the specified location in a straight line. 
 * @author datosh
 *
 */
public class MoveTo extends Routine {
	final protected double destX;
	final protected double destY;

	public MoveTo(double destX, double destY) {
		super();
		this.destX = destX;
		this.destY = destY;
		
		System.out.println("MoveTo: x=" + destX + ",y=" + destY);

	}
	
	public void reset() {
		start();
	}
	
	@Override
	public void act(long delta, GameObject gameObject) {
		if(isRunning()) {
			if(!gameObject.isAlive()) {
				fail();
				return;
			}
			if(!isDroidAtDestination(gameObject)) {
				moveDroid(delta, gameObject);
			}
		}
	}
	
	private void moveDroid(long delta, GameObject gameObject) {
		int speed = gameObject.getRigidbody().getSpeed();
		if(destX != gameObject.getX()) {
			if(Math.abs(destX - gameObject.getX()) < (speed * (double)delta / 1_000_000)) {
				gameObject.setX(destX);
			} else if(destX > gameObject.getX()) {
				gameObject.setX(gameObject.getX() + (speed * (double)delta / 1_000_000));
			} else {
				gameObject.setX(gameObject.getX() - (speed * (double)delta / 1_000_000));
			}
		}
		
		if(destY != gameObject.getY()) {
			if(Math.abs(destY - gameObject.getY()) < (speed * (double)delta / 1_000_000)) {
				gameObject.setY(destY);
			} else if(destY > gameObject.getY()) {
				gameObject.setY(gameObject.getY() + (speed * (double)delta / 1_000_000));
			} else {
				gameObject.setY(gameObject.getY() - (speed * (double)delta / 1_000_000));
			}
		}
		
		if(isDroidAtDestination(gameObject)) {
			succeed();
		}
	}
	
	private boolean isDroidAtDestination(GameObject gameobject) {
		return destX == gameobject.getX() && destY == gameobject.getY();
	}
	

}
