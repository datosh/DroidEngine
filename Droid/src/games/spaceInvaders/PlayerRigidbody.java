package games.spaceInvaders;

import input.InputHandler;
import entities.GameObject;
import physics.collision.ColliderBox;
import physics.rigidbody.Rigidbody;

public class PlayerRigidbody extends Rigidbody {

	public PlayerRigidbody(InputHandler inputHandler, GameObject attachedTo) {
		super(inputHandler, attachedTo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(long delta) {
		// TODO Auto-generated method stub
		int width = (int)((ColliderBox)attachedTo.getCollider()).getWidth();
		int height = (int)((ColliderBox)attachedTo.getCollider()).getHeight();
		double offX = attachedTo.getCollider().getOffX();
		double offY = attachedTo.getCollider().getOffY();
		
		if(inputHandler._A) {
			double destX = attachedTo.getX() - speed * (double)delta / 1_000_000;
			if(destX + offX > 0) {
				attachedTo.setX(destX);
			} else {
				attachedTo.setX(0 - offX);
			}
		}
		if(inputHandler._D) {
			double destX = attachedTo.getX() + speed * (double)delta / 1_000_000;
			if(destX + offX + width < attachedTo.getGame().getWidth()) {
				attachedTo.setX(destX);
			} else {
				attachedTo.setX(attachedTo.getGame().getWidth() - width - offX);
			}
		}
//		if(inputHandler._W) {
//			double destY = attachedTo.getY() - speed * (double)delta / 1_000_000;
//			if(destY + offY > 0) {
//				attachedTo.setY(destY);	
//			} else {
//				attachedTo.setY(0 - offY);
//			}
//		}
//		if(inputHandler._S) {
//			double destY = attachedTo.getY() + speed * (double)delta / 1_000_000;
//			if(destY + offY + height < attachedTo.getGame().getHeight()) {
//				attachedTo.setY(destY);
//			} else {
//				attachedTo.setY(attachedTo.getGame().getHeight() - height - offY);
//			}
//		}
	}
	
}
