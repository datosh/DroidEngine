package util;

public class Tile {
	private boolean walkable;
	
	public final static int TILE_WIDTH = 8;
	public final static int TILE_HEIGHT = 8;
	
	public Tile(boolean walkable) {
		this.walkable = walkable;
	}
	
	public boolean isWalkable() {
		return walkable;
	}
	
	public void toggleWalkable() {
		walkable = !walkable;
	}
	
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
}
