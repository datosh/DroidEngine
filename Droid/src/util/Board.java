package util;

import java.util.ArrayList;
import java.util.List;

import entities.Droid;

public class Board {
	final double width;
	final double height;
	
	private List<Droid> droids = new ArrayList<Droid>();
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void addDroid(Droid droid) {
		if(isTileWalkable(droid.getX(), droid.getY())) {
			droids.add(droid);
			droid.setBoard(this);
		}
	}
	
	//TODO: CHANGE TO CHECK COLLIDER
	public boolean isTileWalkable(double x, double y) {
		for(Droid droid : droids) {
			if(droid.getX() == x && droid.getY() == y) {
				return false;
			}
		}
		
		return true;
	}
	
	public List<Droid> getDroids() {
		return droids;
	}
}
