package games;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import ai.*;
import entities.Droid;
import util.*;

public class PathfindGame extends Game {
	private static final long serialVersionUID = 3036338679756098925L;

	private static final double MAX_DISTANCE = 99999;
	
	List<Node> map;
	Image background;
	
	public PathfindGame(String name) {
		super(name);
	}

	@Override
	public void init() {
		map = new ArrayList<Node>(20);
		try {
			background = ImageIO.read(new File("assets/map1.png"));
		} catch (IOException e) {
			System.err.print("Could not read map1.png!");
		}
		
		System.out.println(super.getWidth() + " " + super.getHeight());
		
		
	}

	@Override
	public void tick(long delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		g.drawImage(background, 0, 0, null);
		
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
