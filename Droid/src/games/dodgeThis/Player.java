package games.dodgeThis;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import entities.GameObject;
import gfx.render.SpriteLoader;

public class Player extends GameObject {
	private Timer immunityTimer;
	private boolean immune;

	public Player() {
		this(0, 0);
	}
	
	public Player(double x, double y) {
		this.x = x;
		this.y = y;
		alive = true;
		immune = false;

		ActionListener immunityRemover = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				immune = false;
				collider.setColor(Color.GREEN);
			}
		};
		immunityTimer = new Timer(500, immunityRemover);
		immunityTimer.setRepeats(false);
		
		sprite = SpriteLoader.get().getSprite("assets/dodge_this/player.png");
	}
	
	public boolean isImmune() {
		return immune;
	}

	public void setImmune(boolean immune) {
		if(immune) {
			this.collider.setColor(Color.BLACK);
			this.immune = true;
			if(immunityTimer.isRunning()) {
				immunityTimer.stop();
			}
		} else {
			if(!immunityTimer.isRunning()) {
				immunityTimer.start();
			}
		}
	}

	@Override
	public void update(long delta) {
		super.update(delta);
		
	}
}
