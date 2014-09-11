package games.labyrinth;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import ai.MoveTo;
import ai.MoveToObject;
import ai.Repeat;
import ai.Routine;
import entities.GameObject;
import physics.collision.ColliderBox;
import physics.rigidbody.Rigidbody;
import util.Point;
import games.Game;
import gfx.animation.Animator;

public class Labyrinth extends Game {
	private static final long serialVersionUID = -9060208778538038541L;

	GameObject stairs;
	Enemy enemy;
	PlayerLab player;
	Point startPos;
	Point stairsPos;
	
	
	public Labyrinth(String name, int width) {
		super(name, width);
		startPos = new Point(0, 0);
		stairsPos = new Point(0, 0);
	}
	
	@Override
	public void init() {
		//Create the maze
		makeLabyrinth();
		
		//Create the player using the staring pos from the maze
		player = new PlayerLab(this, INPUT, startPos);
		player.setCollider(new ColliderBox(0, 0, 5, 5, player));
		player.getCollider().setColor(Color.BLUE);
		player.setRigidbody(new PlayerRigidbody(INPUT, player));
		player.getRigidbody().setSpeed(100);
		
		//Create the stairs using maze position
		stairs = new GameObject(this) {
		};
		String[] locs = {"assets/labyrinth/stairs.png"};
		long[] delays = {1_000_000};
		stairs.setAnimator(new Animator(stairs, locs, delays));
		stairs.setPosition(new Point(stairsPos.getX(), stairsPos.getY()));
		stairs.setCollider(new ColliderBox(0, 0, 16, 16, stairs));
		
		//Create the enemy, search initial path and set routine
		enemy = new Enemy(this);
		enemy.setCollider(new ColliderBox(0, 0, 6, 6, enemy));
		enemy.getCollider().setColor(Color.RED);
		enemy.setRigidbody(new Rigidbody(INPUT, enemy) {
			@Override
			public void update(long delta) {
			}
		});
		enemy.getRigidbody().setSpeed(55);
		enemy.setRoutine(new MoveToObject(player));
	}

	@Override
	public void tick(long delta) {
		player.update(delta);
		stairs.update(delta);
		enemy.update(delta);
		
		if(player.getCollider().overlaps(stairs.getCollider())) {
			switchLevel();
		}
	}

	@Override
	public void render(Graphics g) {
		Iterator<GameObject> itObstacles = obstacles.iterator();
		while(itObstacles.hasNext()) {
			itObstacles.next().draw(g);
		}
		
		stairs.draw(g);
		enemy.draw(g);
		player.draw(g);
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new Labyrinth("Labyrinth", 640).start();
	}
	
	/* TODO: MAYBE MOVE AS PROTECTED INTO GAME */
	private void addObstacle(double x1, double y1, double x2, double y2) {
		GameObject obstacle = new GameObject(this) {
		};
		obstacle.setPosition(new Point(x1, y1));
		obstacle.setCollider(new ColliderBox(0, 0, x2, y2, obstacle));
		obstacles.add(obstacle);
	}
	
	private void makeLabyrinth() {
		//Init array
		int horizontal = (int) Math.ceil(this.getWidth() / 10);
		int vertical = (int) Math.ceil(this.getHeight() / 10);
		boolean lab[][] = new boolean[horizontal+1][vertical+1];
		System.out.println("Creating a maze with " + horizontal + "x" + vertical + " tiles!");
		
		//init start pos
		Random random = new Random();
		int startX = random.nextInt(horizontal);
		int startY = random.nextInt(vertical);
		startPos = new Point(startX * 10 + 2, startY * 10 + 2);
		lab[startX][startY] = true;

		
		//Search a random way through the maze
		boolean walkHor = true;
		boolean walkForw = true;
		boolean stairsAdded = false;
		for(int i = 0; i < 280; i++) {
			//Randomly add the stairs ONCE
			if(!stairsAdded && random.nextInt(100) > 98) {
				stairsPos = new Point(wrap((startX - 3) * 10, this.getWidth()), wrap(startY * 10, this.getHeight()));
				stairsAdded = true;
				//And make some space around the stairs
				lab[wrap(startX-3, horizontal)][wrap(startY-1, vertical)] = true;
				lab[wrap(startX-3, horizontal)][wrap(startY+1, vertical)] = true;
				lab[wrap(startX-3, horizontal)][wrap(startY, vertical)] = true;
				lab[wrap(startX-2, horizontal)][wrap(startY-1, vertical)] = true;
				lab[wrap(startX-2, horizontal)][wrap(startY+1, vertical)] = true;
				lab[wrap(startX-2, horizontal)][wrap(startY, vertical)] = true;
				lab[wrap(startX-1, horizontal)][wrap(startY-1, vertical)] = true;
				lab[wrap(startX-1, horizontal)][wrap(startY+1, vertical)] = true;
				lab[wrap(startX-1, horizontal)][wrap(startY, vertical)] = true;
				lab[wrap(startX, horizontal)][wrap(startY-1, vertical)] = true;
				lab[wrap(startX, horizontal)][wrap(startY+1, vertical)] = true;
				lab[wrap(startX, horizontal)][wrap(startY-1, vertical)] = true;
			}
			
			if(random.nextInt(100) > 90) {
				walkHor = !walkHor;
			}
			if(random.nextInt(100) > 95) {
				walkForw = !walkForw;
			}
			
			if(walkHor && walkForw) {
				startX++;
			} else if(!walkHor && walkForw) {
				startY++;
			} else if(walkHor && !walkForw) {
				startX--;
			} else {
				startY--;
			}
			
			lab[wrap(startX-1, horizontal)][wrap(startY, vertical)] = true;
			//lab[wrap(startX+1, horizontal)][wrap(startY, vertical)] = true;
			lab[wrap(startX, horizontal)][wrap(startY-1, vertical)] = true;
			//lab[wrap(startX, horizontal)][wrap(startY+1, vertical)] = true;
		}
		
		if(!stairsAdded) {
			stairsPos = new Point(wrap((startX - 3) * 10, this.getWidth()), wrap(startY * 10, this.getHeight()));
			stairsAdded = true;
			//And make some space around the stairs
			lab[wrap(startX-3, horizontal)][wrap(startY-1, vertical)] = true;
			lab[wrap(startX-3, horizontal)][wrap(startY+1, vertical)] = true;
			lab[wrap(startX-3, horizontal)][wrap(startY, vertical)] = true;
			lab[wrap(startX-2, horizontal)][wrap(startY-1, vertical)] = true;
			lab[wrap(startX-2, horizontal)][wrap(startY+1, vertical)] = true;
			lab[wrap(startX-2, horizontal)][wrap(startY, vertical)] = true;
			lab[wrap(startX-1, horizontal)][wrap(startY-1, vertical)] = true;
			lab[wrap(startX-1, horizontal)][wrap(startY+1, vertical)] = true;
			lab[wrap(startX-1, horizontal)][wrap(startY, vertical)] = true;
			lab[wrap(startX, horizontal)][wrap(startY-1, vertical)] = true;
			lab[wrap(startX, horizontal)][wrap(startY+1, vertical)] = true;
			lab[wrap(startX, horizontal)][wrap(startY-1, vertical)] = true;
		}
		

		//Create the obstacles
		for(int i = 0; i < horizontal; i++) {
			for (int j = 0; j < vertical; j++) {
				if(!lab[i][j]) {
					addObstacle(i * 10, j * 10, 10, 10);
				}
			}
		}
	}
	
	private void switchLevel() {
		List<List<GameObject>> oldLevels = new ArrayList<List<GameObject>>();
		oldLevels.add(obstacles);
		obstacles.clear();
		makeLabyrinth();
		player.setPosition(startPos);
		stairs.setPosition(stairsPos);
		Point enemySpawn = new Point(player.getX() + 140, player.getY()-250);
		if(enemySpawn.getX() > this.getWidth()) {
			enemySpawn.setX(this.getWidth());
		}
		if(enemySpawn.getY() > this.getHeight()) {
			enemySpawn.setY(this.getHeight());
		}
		enemySpawn.setX(enemySpawn.getX() < 0 ? 0 : enemySpawn.getX());
		enemySpawn.setY(enemySpawn.getY() < 0 ? 0 : enemySpawn.getY());
		enemy.setPosition(enemySpawn);
	}
	
	/**
	 * (a % b + b) % b to get always the smallest pos mod
	 */
	int wrap(int x, int m) {
		return (x % m + m) % m;
	}
	
}
