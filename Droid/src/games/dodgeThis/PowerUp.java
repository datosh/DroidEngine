package games.dodgeThis;

import entities.GameObject;
import gfx.render.SpriteLoader;

public class PowerUp extends GameObject {
	private long duration;
	
	public PowerUp(double x, double y) {
		this.x = x;
		this.y = y;
		this.alive = true;
		duration = 6_000_000; // 6 seconds
		sprite = SpriteLoader.get().getSprite("assets/dodge_this/shield.png");
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
