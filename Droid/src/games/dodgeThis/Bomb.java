package games.dodgeThis;

import entities.GameObject;

public class Bomb extends GameObject {
	private long duration;
	
	public Bomb(double x, double y) {
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