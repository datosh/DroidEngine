package games;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ai.*;
import entities.Droid;
import util.*;

public class PathfindGame extends Game {
	private static final long serialVersionUID = 3036338679756098925L;

	private static final double MAX_DISTANCE = 99999;
	
	List<Node> map;
	
	public PathfindGame(String name) {
		super(name);
	}

	@Override
	public void init() {
		map = new ArrayList<Node>(20);
		
		
	}

	@Override
	public void tick(long delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		Iterator<Node> it = map.iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}
	
	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new PathfindGame("TestGame").start();
	}
}
