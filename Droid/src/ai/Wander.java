package ai;

import java.util.Random;

import entities.Droid;
import util.Board;
import util.Tile;

/**
 * Picks a random location on the board and calles the MoveTo Routine to get there.
 * 
 * @author datosh
 *
 */
public class Wander extends Routine {
	private static Random random = new Random();
	
	private final Board board;
	private MoveTo moveTo;
	
	public Wander(Board board) {
		super();
		this.board = board;
		int x = (int)(random.nextDouble() * board.getWidth());
		x = (x - x % Tile.TILE_WIDTH);
		int y = (int)(random.nextDouble() * board.getHeight());
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
		int x = (int)(random.nextDouble() * board.getWidth());
		x = (x - x % Tile.TILE_WIDTH);
		int y = (int)(random.nextDouble() * board.getHeight());
		y = (y - y % Tile.TILE_HEIGHT);
		this.moveTo = new MoveTo(x, y);
	}
	
	@Override
	public void act(long delta, Droid droid, Board board) {
		if(!moveTo.isRunning()) {
			return;
		}
		this.moveTo.act(delta, droid, board);
		if(this.moveTo.isSuccess()) {
			succeed();
		} else if(this.moveTo.isFailure()) {
			fail();
		}
	}
}
