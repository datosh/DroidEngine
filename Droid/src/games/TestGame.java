package games;

import java.awt.Color;
import java.awt.Graphics;

import ai.MoveTo;
import ai.Routine;
import ai.Wander;
import entities.Droid;
import util.Board;
import util.Point;

public class TestGame extends Game {
	private static final long serialVersionUID = 3036338679756098925L;

	Board board;
	Droid droid;
	Routine moveTo;
	Routine wander;
	
	public TestGame(String name) {
		super(name);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		board = new Board(400, 400);
		droid = new Droid("MyDroid", new Point(250, 150), 10, 1, 2);
		board.addDroid(droid);
		
		moveTo = new MoveTo(340, 260);
		wander = new Wander(board);
		
		droid.setRoutine(wander);
		System.out.println(droid);
	}

	@Override
	public void tick(long delta) {
		// TODO Auto-generated method stub
		droid.update(delta);
		
		System.out.println(droid);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		g.fillRect((int)droid.getX(), (int)droid.getY(), 5, 5);
	}
	
	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new TestGame("TestGame").start();
	}

}
