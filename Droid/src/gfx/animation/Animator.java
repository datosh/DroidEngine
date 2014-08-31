package gfx.animation;

import entities.GameObject;
import entities.STATE;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public class Animator {
	GameObject attachedTo;
	Map<STATE, Animation> animations;
	Animation active;
	STATE lastState;
	
	public Animator(GameObject attachtedTo, String[] locs, long[] delays) {
		this.attachedTo = attachtedTo;
		animations = new HashMap<STATE, Animation>(5);
		active = null;
		
		/* A DEFAULT ANIMATION HAS ALWAYS HAVE TO BE SET */
		animations.put(STATE.DEFAULT, new Animation(locs, delays, STATE.DEFAULT));
		active = animations.get(STATE.DEFAULT);
		lastState = STATE.DEFAULT;
	}
	
	public void setAnimation(String[] locs, long[] delays, STATE state) {
		if(animations.containsKey(state)) {
			System.err.println("Already an animation for the state: " + state + " registered!");
		} else {
			animations.put(state, new Animation(locs, delays, state));
		}
	}
	
	public void deleteAnimation(STATE state) {
		if(animations.containsKey(state)) {
			animations.remove(state);
		} else {
			System.err.println("Cannot delete animation, since no animation for state: " + state + " registered!");
		}
	}
	
	public void update(long delta) {
		//Continue with the animation if the state hasnt changed
		if(lastState.equals(attachedTo.getState())) {
			active.update(delta);
		} else {
			//Get the nex state and the corresponding animation
			lastState = attachedTo.getState();
			active = animations.get(lastState);
			//If no animation is registered for that state just get the default
			if(active == null) {
				active = animations.get(STATE.DEFAULT);
			}
			//Reset the counters in the animation and then update it
			active.reset();
			active.update(delta);
		}
		
	}
	
	public void draw(int x, int y, Graphics g) {
		active.getSprite().draw(x, y, g);
	}
}
