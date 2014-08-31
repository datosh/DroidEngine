package games.dodgeThis;

import entities.GameObject;
import games.Game;

public class Enemy extends GameObject {
	public Enemy(Game game, double x, double y) {
		super(game);
		this.x = x;
		this.y = y;
		this.alive = true;
	}
}
