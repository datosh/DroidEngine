package ai;

import java.util.Random;

import entities.Droid;
import entities.GameObject;
import util.Board;
import util.Point;
import util.Tile;

/**
 * Picks a random location on the board and calles the MoveTo Routine to get there.
 * 
 * @author datosh
 *
 */
public class Wander extends Routine {
	private static Random random = new Random();
	
	private Point min, max;
	private MoveTo moveTo;
	
	public Wander(Point min, Point max) {
		super();
		this.min = min;
		this.max = max;
		int x = (int)(random.nextDouble() * max.getX());
		x = (x - x % Tile.TILE_WIDTH);
		int y = (int)(random.nextDouble() * max.getY());
		y = (y - y % Tile.TILE_HEIGHT);
		this.moveTo = new MoveTo(x, y);
	}

	@Override
	public void start() {
		super.start();
		this.moveTo.start();
	}
	
	@Override
	public void reset() {
		int x = (int)(random.nextDouble() * max.getX());
		x = (x - x % Tile.TILE_WIDTH);
		int y = (int)(random.nextDouble() * max.getY());
		y = (y - y % Tile.TILE_HEIGHT);
		this.moveTo = new MoveTo(x, y);
	}
	
	@Override
	public void act(long delta, GameObject gameObject) {
		if(!moveTo.isRunning()) {
			return;
		}
		this.moveTo.act(delta, gameObject);
		if(this.moveTo.isSuccess()) {
			succeed();
		} else if(this.moveTo.isFailure()) {
			fail();
		}
	}
}
