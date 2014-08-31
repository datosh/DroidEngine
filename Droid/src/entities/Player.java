package entities;

import input.InputHandler;

import entities.GameObject;
import games.Game;

public class Player extends GameObject {
	
	InputHandler input;
	
	public Player(Game game, InputHandler input) {
		this(game, input, 0, 0);
	}
	
	public Player(Game game, InputHandler input, double x, double y) {
		super(game);
		this.input = input;
		this.x = x;
		this.y = y;
		alive = true;
	}
	
	@Override
	public void update(long delta) {
		super.update(delta);
	}
}
