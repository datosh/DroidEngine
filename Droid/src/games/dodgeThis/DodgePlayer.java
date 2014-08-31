package games.dodgeThis;

import input.InputHandler;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import entities.GameObject;
import entities.Player;
import games.Game;
import gfx.render.Sprite;
import gfx.render.SpriteLoader;

public class DodgePlayer extends Player {
	private Timer immunityTimer;
	private boolean immune;


	public DodgePlayer(Game game, InputHandler input, double x, double y) {
		super(game, input);
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
