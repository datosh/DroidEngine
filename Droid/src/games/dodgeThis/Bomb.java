package games.dodgeThis;

import entities.GameObject;
import games.Game;
import gfx.render.SpriteLoader;

public class Bomb extends GameObject {
	private long duration;
	
	public Bomb(Game game, double x, double y) {
		super(game);
		this.x = x;
		this.y = y;
		this.alive = true;
		duration = 6_000_000; // 6 seconds
	}
	
	@Override
	public void update(long delta) {
		super.update(delta);
		duration -= delta;
		if(duration < 0) {
			alive = false;
		}
	}

}
