package ai;

import util.Board;
import util.Tile;
import entities.Droid;

/**
 * Moves to the specified location in a straight line. 
 * @author datosh
 *
 */
public class MoveTo extends Routine {
	final protected double destX;
	final protected double destY;
	final protected int destXTile;
	final protected int destYTile;

	public MoveTo(double destX, double destY) {
		super();
		this.destX = destX;
		this.destY = destY;
		destXTile = (int)(destX / Tile.TILE_WIDTH);
		destYTile = (int)(destY / Tile.TILE_HEIGHT);
		
		System.out.println("MoveTo: x=" + destX + ",y=" + destY);

	}
	
	public void reset() {
		start();
	}
	
	@Override
	public void act(long delta, Droid droid, Board board) {
		if(isRunning()) {
			if(!droid.isAlive()) {
				fail();
				return;
			}
			if(!isDroidAtDestination(droid)) {
				moveDroid(delta, droid);
			}
		}
	}
	
	private void moveDroid(long delta, Droid droid) {
		if(destX != droid.getX()) {
			if(Math.abs(destX - droid.getX()) < (droid.getSpeed() * delta / 1_000_000)) {
				droid.setX(destX);
			} else if(destX > droid.getX()) {
				droid.setX(droid.getX() + (droid.getSpeed() * delta / 1_000_000));
			} else {
				droid.setX(droid.getX() - (droid.getSpeed() * delta / 1_000_000));
			}
		}
		
		if(destY != droid.getY()) {
			if(Math.abs(destY - droid.getY()) < (droid.getSpeed() * delta / 1_000_000)) {
				droid.setY(destY);
			} else if(destY > droid.getY()) {
				droid.setY(droid.getY() + (droid.getSpeed() * delta / 1_000_000));
			} else {
				droid.setY(droid.getY() - (droid.getSpeed() * delta / 1_000_000));
			}
		}
		
		if(isDroidAtDestination(droid)) {
			succeed();
		}
	}
	
	private boolean isDroidAtDestination(Droid droid) {
		return destX == droid.getX() && destY == droid.getY();
	}
	

}
