package ai;

import java.util.Random;

import entities.Droid;
import util.Board;

public class Wander extends Routine {
	
	private static Random random = new Random();
	
	private final Board board;
	private MoveTo moveTo;
	
	public Wander(Board board) {
		super();
		this.board = board;
		this.moveTo = new MoveTo(random.nextDouble() * board.getWidth(), random.nextDouble() * board.getHeight());
	}

	@Override
	public void start() {
		super.start();
		this.moveTo.start();
	}
	
	@Override
	public void reset() {
		this.moveTo = new MoveTo(random.nextDouble() * board.getWidth(), random.nextDouble() * board.getHeight());
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
