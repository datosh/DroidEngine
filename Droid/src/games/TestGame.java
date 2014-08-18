package games;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ai.*;
import entities.Droid;
import util.*;

public class TestGame extends Game {
	private static final long serialVersionUID = 3036338679756098925L;

	private static final double MAX_DISTANCE = 99999;
	
	Board board;
	Droid droid;
	Routine moveTo;
	Routine wander;
	Routine repeat;
	
	List<Node> nodes;
	Point[] nodeToBuild;
	Point n1, n2, n3, nn;
	boolean initialNodeDone;
	Node selectedNode = null, otherNode = null;
	double distance = MAX_DISTANCE;
	
	public TestGame(String name) {
		super(name);
	}

	@Override
	public void init() {
		//Initialize Board and set some tiles to be not walkable
		board = new Board(WIDTH, HEIGHT);
		board.getTiles()[board.numTilesH][board.numTilesV].toggleWalkable();
		
		//Create a new Droid and add it to the board
		droid = new Droid("MyDroid", new Point(16 * 4, 20 * 4), 10, 1, 2);
		board.addDroid(droid);
		
		nodes = new ArrayList<Node>(25);
		n1 = null;
		n2 = null; 
		n3 = null; 
		nn = null;
		initialNodeDone = false;
		
		moveTo = new MoveTo(512, 288);
		wander = new Wander(board);
		repeat = new Repeat(wander);
		
		droid.setRoutine(repeat);
	}

	@Override
	public void tick(long delta) {
		// TODO Auto-generated method stub
		
		Point buffer = INPUT.getClick();

		
		if(!(buffer == null)) {
			if(!initialNodeDone) {
				if(n1 == null) {
					n1 = new Point((int)buffer.getX(), (int)buffer.getY());
					System.out.println("Clicked " + buffer);
				} else if(n2 == null) {
					n2 = new Point((int)buffer.getX(), (int)buffer.getY());
					System.out.println("Clicked " + buffer);
				} else if(n3 == null) {
					n3 = new Point((int)buffer.getX(), (int)buffer.getY());
					System.out.println("Clicked " + buffer);
					nodes.add(new Node(n1, n2, n3));
					initialNodeDone = true;
				}
			}
			
			//reset selected poly
			if(INPUT._Q) {
				selectedNode = null;
				distance = MAX_DISTANCE;
			}
			
			//delete last poly
			if(INPUT._R) {
				if(!nodes.isEmpty()) {
					nodes.remove(nodes.size() - 1);
				}
			}
			
			//select a new poly
			if(INPUT._SHIFT) {
				if(selectedNode != null) {
					selectedNode.setCentroidColor(Color.RED);
				}
				selectedNode = null;
				
				Iterator<Node> it = nodes.iterator();
				while(it.hasNext()) {
					Node currentNode = it.next();
					if(selectedNode == null) {
						selectedNode = currentNode;
						distance = selectedNode.getCentroid().distance(buffer);
						continue;
					}
					if(currentNode.getCentroid().distance(buffer) < distance) {
						selectedNode = currentNode;
						distance = selectedNode.getCentroid().distance(buffer);
					}
				}
				if(selectedNode != null) {
					selectedNode.setCentroidColor(Color.GREEN);
					System.out.println("Picked a node!");
				}
			}
			
			//MAKE A NEW POLY
			if(INPUT._CTRL) {
				System.out.println("Clicked " + buffer);
				Point[] points = selectedNode.getPoints();
	
				if(points[0].distance(buffer) < points[2].distance(buffer) && points[1].distance(buffer) < points[2].distance(buffer)) {
					nodes.add(new Node(points[0], points[1], buffer));
				} else if(points[0].distance(buffer) < points[1].distance(buffer) && points[2].distance(buffer) < points[1].distance(buffer)) {
					nodes.add(new Node(points[0], points[2], buffer));
				} else {
					nodes.add(new Node(points[1], points[2], buffer));
				}
			}
			
			if(INPUT._ALT) {
				Iterator<Node> it = nodes.iterator();
				while(it.hasNext()) {
					Node currentNode = it.next();
					if(otherNode == null) {
						otherNode = currentNode;
						distance = selectedNode.getCentroid().distance(buffer);
						continue;
					}
					if(currentNode.getCentroid().distance(buffer) < distance) {
						otherNode = currentNode;
						distance = otherNode.getCentroid().distance(buffer);
					}
				}
				if(otherNode == selectedNode) {
					System.err.println("ERROR: otherNode and selectedNode are the same!");
					
				} else if(otherNode != null) {
					otherNode.setNeighbour(selectedNode);
					selectedNode.setNeighbour(otherNode);
				}
			}
			
			if(INPUT._P) {
				Iterator<Node> it = nodes.iterator();
				while(it.hasNext()) {
					Node currentNode = it.next();
					System.out.println(currentNode);
				}
			}
			
			if(INPUT._S) {
				List<Node> path = Node.findWay(nodes.get(0), nodes.get(nodes.size()), nodes);
				if(path == null) {
					System.err.println("No path found!");
				} else {
					Iterator<Node> it = path.iterator();
					System.out.println("Path: ");
					while(it.hasNext()) {
						Node currentNode = it.next();
						System.out.print(currentNode.getID() + ", ");
					}
					System.out.println("");
				}
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		//renderGrid(g);
		
		Iterator<Node> it = nodes.iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}
	
	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new TestGame("TestGame").start();
	}
	

	private boolean sameSide(Vector2D p1, Vector2D p2, Vector2D a, Vector2D b) {
		if(b.sub(a).dotProduct(p1.sub(a).negateY()) >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean pointInTriangle(Vector2D p, Vector2D a, Vector2D b, Vector2D c) {
		if(sameSide(p, a, b, c) && sameSide(p, b, a, c) && sameSide(p, c, a, b)) {
			return true;
		} else {
			return false;
		}
	}
}
