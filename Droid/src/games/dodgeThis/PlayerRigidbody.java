package games.dodgeThis;

import physics.collision.ColliderBox;
import physics.rigidbody.Rigidbody;
import entities.GameObject;
import input.InputHandler;

public class PlayerRigidbody extends Rigidbody {

	public PlayerRigidbody(InputHandler inputHandler, GameObject attachedTo) {
		super(inputHandler, attachedTo);
		
	}

	@Override
	public void update(long delta) {
		int width = (int)((ColliderBox)attachedTo.getCollider()).getWidth();
		int height = (int)((ColliderBox)attachedTo.getCollider()).getHeight();
		
		if(inputHandler._A) {
			double destX = attachedTo.getX() - speed * (double)delta / 1_000_000;
			if(destX > 0) {
				attachedTo.setX(destX);
			} else {
				attachedTo.setX(0);
			}
		}
		if(inputHandler._D) {
			double destX = attachedTo.getX() + speed * (double)delta / 1_000_000;
			if(destX + width < attachedTo.getGame().getWidth()) {
				attachedTo.setX(destX);
			} else {
				attachedTo.setX(attachedTo.getGame().getWidth() - width);
			}
		}
		if(inputHandler._W) {
			double destY = attachedTo.getY() - speed * (double)delta / 1_000_000;
			if(destY > 0) {
				attachedTo.setY(destY);	
			} else {
				attachedTo.setY(0);
			}
		}
		if(inputHandler._S) {
			double destY = attachedTo.getY() + speed * (double)delta / 1_000_000;
			if(destY + height < attachedTo.getGame().getHeight()) {
				attachedTo.setY(destY);
			} else {
				attachedTo.setY(attachedTo.getGame().getHeight() - height);
			}
			
		}
	}
}
