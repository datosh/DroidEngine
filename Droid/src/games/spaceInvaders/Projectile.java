package games.spaceInvaders;

import entities.GameObject;
import games.Game;

public class Projectile extends GameObject {

	public Projectile(Game game) {
		super(game);
		
	}
	
	@Override
	public void update(long delta) {
		super.update(delta);
		
		double destY = (y - 125 * (double)delta / 1_000_000);
		if(destY < 0) {
			this.alive = false;
		} else {
			System.out.println("Moving from y:" + y + " to destX:" + destY + "!");
			y = destY;
		}
	}
}
