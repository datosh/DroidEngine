package games.labyrinth;

import java.util.Iterator;

import games.Game;
import input.InputHandler;
import physics.collision.ColliderBox;
import physics.rigidbody.Rigidbody;
import util.Point;
import entities.GameObject;

public class PlayerRigidbody extends Rigidbody{
	Game game;
	boolean wrapAround = true;
	
	public PlayerRigidbody(InputHandler inputHandler, GameObject attachedTo) {
		super(inputHandler, attachedTo);
		this.game = attachedTo.getGame();
	}
	
	

	@Override
	public void update(long delta) {
		int width = (int)((ColliderBox)attachedTo.getCollider()).getWidth();
		int height = (int)((ColliderBox)attachedTo.getCollider()).getHeight();

		double oldX = attachedTo.getX();
		double oldY = attachedTo.getY();
		
		/* Takes care of movement. 
		 * Note: 	resetIfCollided(oldX, oldY); has to be in every key to ensure that
		 * 			each key works and they are not blocking if one direction collides. 
		 */
		if(inputHandler._A) {
			//Get new X Coordinate and set accordingly
			double destX = attachedTo.getX() - speed * (double)delta / 1_000_000;
			if(destX > 0) {
				attachedTo.setX(destX);
			} else {
				//If wrap Around is enabled test if okay to wrap
				if(wrapAround) {
					attachedTo.setX(attachedTo.getGame().getWidth() - width);
					Iterator<GameObject> it = game.getObstacles().iterator();
					while(it.hasNext()) {
						if(it.next().getCollider().overlaps(attachedTo.getCollider())) {
							attachedTo.setX(0);
							break;
						}
					}
				} else {
					attachedTo.setX(0);
				}
			}
			resetIfCollided(oldX, oldY);
		}
		if(inputHandler._D) {
			//Get new X Coordinate and set accordingly
			double destX = attachedTo.getX() + speed * (double)delta / 1_000_000;
			if(destX + width < attachedTo.getGame().getWidth()) {
				attachedTo.setX(destX);
			} else {
				//If wrap Around is enabled test if okay to wrap
				if(wrapAround) {
					attachedTo.setX(0);
					Iterator<GameObject> it = game.getObstacles().iterator();
					while(it.hasNext()) {
						if(it.next().getCollider().overlaps(attachedTo.getCollider())) {
							attachedTo.setX(attachedTo.getGame().getWidth() - width);
							break;
						}
					}
				} else {
					attachedTo.setX(attachedTo.getGame().getWidth() - width);
				}
			}
			resetIfCollided(oldX, oldY);
		}
		if(inputHandler._W) {
			double destY = attachedTo.getY() - speed * (double)delta / 1_000_000;
			if(destY > 0) {
				attachedTo.setY(destY);	
			} else {
				//If wrap Around is enabled test if okay to wrap
				if(wrapAround) {
					attachedTo.setY(attachedTo.getGame().getHeight() - height);
					Iterator<GameObject> it = game.getObstacles().iterator();
					while(it.hasNext()) {
						if(it.next().getCollider().overlaps(attachedTo.getCollider())) {
							attachedTo.setY(0);
							break;
						}
					}
				} else {
					attachedTo.setY(0);
				}
				
			}
			resetIfCollided(oldX, oldY);
		}
		if(inputHandler._S) {
			double destY = attachedTo.getY() + speed * (double)delta / 1_000_000;
			if(destY + height < attachedTo.getGame().getHeight()) {
				attachedTo.setY(destY);
			} else {
				//If wrap Around is enabled test if okay to wrap
				if(wrapAround) {
					attachedTo.setY(0);
					Iterator<GameObject> it = game.getObstacles().iterator();
					while(it.hasNext()) {
						if(it.next().getCollider().overlaps(attachedTo.getCollider())) {
							attachedTo.setY(attachedTo.getGame().getHeight() - height);
							break;
						}
					}
				} else {
					attachedTo.setY(attachedTo.getGame().getHeight() - height);
				}
				
			}
			resetIfCollided(oldX, oldY);
		}
		
	}
	
	private void resetIfCollided(double oldX, double oldY) {
		//If the new position is colliding with something else put us back
		Iterator<GameObject> it = game.getObstacles().iterator();
		while(it.hasNext()) {
			if(it.next().getCollider().overlaps(attachedTo.getCollider())) {
				System.out.println("OVERLAPPING!");
				attachedTo.setPosition(new Point(oldX, oldY));
				return;
			}
		}
	}
}
