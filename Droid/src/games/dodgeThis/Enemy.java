package games.dodgeThis;

import entities.GameObject;
import gfx.render.SpriteLoader;

public class Enemy extends GameObject {
	public Enemy(double x, double y) {
		this.x = x;
		this.y = y;
		this.alive = true;
		sprite = SpriteLoader.get().getSprite("assets/dodge_this/enemy.png");
	}
	
	
}
