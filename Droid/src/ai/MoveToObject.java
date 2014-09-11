package ai;

import entities.GameObject;

public class MoveToObject extends Routine{
	protected double destX;
	protected double destY;
	final protected GameObject destination;
	
	public MoveToObject(GameObject object) {
		super();
		this.destX = object.getX();
		this.destY = object.getY();
		this.destination = object;
		
		System.out.println("Following: " + object);

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
				destX = destination.getX();
				destY = destination.getY();
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
		return destination.getCollider().overlaps(gameobject.getCollider());
	}
	

}
