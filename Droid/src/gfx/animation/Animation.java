package gfx.animation;

import entities.STATE;
import gfx.render.Sprite;
import gfx.render.SpriteLoader;

public class Animation {
	String[] locs;
	long[] delays;
	int length;
	STATE state;
	long timePassed;
	long timeTotal;
	Sprite currentSprite;
	
	public Animation(String[] locs, long[] delays, STATE state) {
		if(locs.length != delays.length) {
			System.err.println("Location and Delay-Arrays have different length!");
		}
		this.locs = locs;
		this.delays = delays;
		this.state = state;
		timePassed = 0;
		
		/* Calculate the total duration of the animation */
		timeTotal = 0;
		for(long i : delays) {
			timeTotal += i;
		}
	}
	
	public void reset() {
		timePassed = 0;
	}
	
	/**
	 * Updates the currentSprite accordingly to the time that has passed
	 * @param delta time passed since last call
	 */
	public void update(long delta) {
		timePassed += delta;
		
		if(timePassed >= timeTotal) {
			timePassed -= timeTotal;
		}
		
		long delaySums = 0;
		for(int i = 0; i < delays.length; i++) {
			delaySums += delays[i];
			if(timePassed < delaySums) {
				currentSprite = SpriteLoader.get().getSprite(locs[i]);
				return;
			}
		}
	}
	
	public Sprite getSprite() {
		return currentSprite;
	}
}
