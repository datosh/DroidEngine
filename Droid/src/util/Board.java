package util;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import entities.Droid;

public class Board {
	
	private final double width, height;
	public final int numTilesH, numTilesV;
	
	private Tile tiles[][];
	private List<Droid> droids = new ArrayList<Droid>();
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		numTilesH = (int)width/Tile.TILE_WIDTH;
		numTilesV = (int)height/Tile.TILE_HEIGHT;
		tiles = new Tile[numTilesH + 1][numTilesV + 1];
		for(int i = 0; i <= numTilesH; i++) {
			for(int j = 0; j <= numTilesV; j++) {
				tiles[i][j] = new Tile(true);
			}
		}
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public Tile[][] getTiles() {
		return tiles;
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
			if(Math.abs(droid.getX() - x) < Tile.TILE_WIDTH && Math.abs(droid.getY() - y) < Tile.TILE_HEIGHT) {
				return false;
			}
		}
		if(tiles[(int)(x / Tile.TILE_WIDTH)][(int)(y / Tile.TILE_HEIGHT)].isWalkable()) {
			return false;
		}
		return true;
	}
	
	public List<Droid> getDroids() {
		return droids;
	}
	
	public void renderGrid(Graphics g) {
		g.setColor(Color.BLACK);
		for(int i = 0; i <= (width / Tile.TILE_WIDTH) + 1; i++) {
			g.drawLine(i * Tile.TILE_WIDTH, 0, i * Tile.TILE_WIDTH, (int)height + Tile.TILE_HEIGHT);
		}
		for(int i = 0; i <= ((int)height / Tile.TILE_HEIGHT) + 1; i++) {
			g.drawLine(0, i * Tile.TILE_HEIGHT, (int)width+ Tile.TILE_WIDTH, i * Tile.TILE_HEIGHT);
		}
		for(int i = 0; i <= ((int)width / Tile.TILE_WIDTH); i++) {
			for(int j = 0; j <= ((int)height / Tile.TILE_WIDTH); j++) {
				if(!tiles[i][j].isWalkable()) {
					g.fillRect(i * Tile.TILE_WIDTH, j * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
				}
			}
		}
	}
}
