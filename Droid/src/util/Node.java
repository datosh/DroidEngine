package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class Node {
	private static int ID_COUNTER = 0;
	
	//The node itself
	private int id;
	private Polygon e;
	private Point centroid; //schwerpunkt
	int[] xpoints, ypoints;
	//It's neighbours
	private Node[] neighbours;
	private int[] costs;
	//Some other values
	private Color centroidColor = Color.RED;
	private Node previous; //Needed for pathfinding
	
	/*
	 * CONSTRUCTORS
	 */
	
	public Node(Point p1, Point p2, Point p3) {
		//Inizialize the polygon
		xpoints = new int[3];
		xpoints[0] = (int)p1.getX();
		xpoints[1] = (int)p2.getX();
		xpoints[2] = (int)p3.getX();
		ypoints = new int[3];
		ypoints[0] = (int)p1.getY();
		ypoints[1] = (int)p2.getY();
		ypoints[2] = (int)p3.getY();
		e = new Polygon(xpoints, ypoints, 3);
		
		//Calculate the centroid as:
		int x1 = (int)p1.getX(), x2 = (int)p2.getX(), x3 = (int)p3.getX();
		int y1 = (int)p1.getY(), y2 = (int)p2.getY(), y3 = (int)p3.getY();
		double cx = (x1 + x2 + x3) / 3;
		double cy = (y1 + y2 + y3) / 3;
		centroid = new Point(cx, cy);
		
		//Make room for neighbours
		neighbours = new Node[3];
		costs = new int[3];
		
		//Store and increment ID
		id = ID_COUNTER;
		ID_COUNTER++;
	}
	
	
	public Node(int x1, int y1, int x2, int y2, int x3, int y3) {
		//Inizialize the polygon
		xpoints = new int[3];
		xpoints[0] = x1;
		xpoints[1] = x2;
		xpoints[2] = x3;
		ypoints = new int[3];
		ypoints[0] = y1;
		ypoints[1] = y2;
		ypoints[2] = y3;
		e = new Polygon(xpoints, ypoints, 3);
		
		//Calculate the centroid as:
		double cx = (x1 + x2 + x3) / 3;
		double cy = (y1 + y2 + y3) / 3;
		centroid = new Point(cx, cy);

		//Make room for neighbours
		neighbours = new Node[3];
		costs = new int[3];
		
		//Store and increment ID
		id = ID_COUNTER;
		ID_COUNTER++;
	}
	
	/*
	 * GETTER & SETTER
	 */
	
	public void setCosts(int[] costs) {
		this.costs = costs;
	}
	
	public void setCentroidColor(Color color) {
		this.centroidColor = color;
	}
	
	public Node[] getNeighbours() {
		return neighbours;
	}
	
	public Point getCentroid() {
		return centroid;
	}
	
	public int getID() {
		return id;
	}
	
	public void setPrevious(Node node) {
		this.previous = node;
	}
	
	public Node getPrevious() {
		return previous;
	}
	
	public Point[] getPoints() {
		Point[] result = new Point[3];
		
		result[0] = new Point(e.xpoints[0], e.ypoints[0]);
		result[1] = new Point(e.xpoints[1], e.ypoints[1]);
		result[2] = new Point(e.xpoints[2], e.ypoints[2]);
		
		return result;
	}
	
	/*
	 * OTHER METHODS
	 */
	
	/**
	 * Sets a new neighbour. Checks if nodes are already neighbours and if there is still room for
	 * more neighbours. 
	 * 
	 * @param neighbour
	 */
	public void setNeighbour(Node neighbour) {
		//Check if the nodes arent already neighbours break!
		for(int i = 0; i < 3; i++) {
			if(neighbours[0] != null) {
				if(neighbours[0] == neighbour) {
					System.err.println("ERROR: " + neighbour + " and " + this + " are already connected!");
					return;
				}
			}
		}
		
		//Place new neighbour in one free slot
		if(neighbours[0] == null) {
			neighbours[0] = neighbour;
		} else if(neighbours[1] == null) {
			neighbours[1] = neighbour;
		} else if(neighbours[2] == null) {
			neighbours[2] = neighbour;
		} else {
			//If all slots are already filled up print error message
			System.err.println("ERROR: All neightbours already filled!");
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawPolygon(e);
		
		g.setColor(centroidColor);
		g.fillOval((int)centroid.getX(), (int)centroid.getY(), 2, 2);
		
		g.setColor(Color.BLUE);
		for(int i = 0; i < 3; i++) {
			if(neighbours[i] != null) {
				g.drawLine((int)centroid.getX(), (int)centroid.getY(), (int)neighbours[i].getCentroid().getX(), (int)neighbours[i].getCentroid().getY());
			}
		}
		
		g.setColor(Color.BLACK);
		char[] chars = ("" + id).toCharArray();
		g.drawChars(chars, 0, chars.length, (int)centroid.getX()-10, (int)centroid.getY()-10);
	}
	
	@Override
	public String toString() {
		return "{ID=" + id + 
				",x1=" + xpoints[0] + ",x2=" + xpoints[1] + ",x3=" + xpoints[2] +
				",y1=" + ypoints[0] + ",y2=" + ypoints[1] + ",y3=" + ypoints[2] + "}";
	}
	
	/**
	 * Returns a path on the List of nodes from the start to the end
	 * @param start Start. 
	 * @param end Destination. 
	 * @param map List of nodes to walk on. 
	 * @return Path from the start to the end node. 
	 */
	public static List<Node> findWay(Node start, Node end, List<Node> map) {
		List<Node> reachable = new ArrayList<Node>();
		List<Node> explored = new ArrayList<Node>();
		
		//Add start as the initial node
		reachable.add(start);
		
		//While we have still something to explore
		while(!reachable.isEmpty()) {
			//Get the next node
			Node node = reachable.get(reachable.size()-1);
			
			//Is node already the destination?
			if(node.getID() == end.getID()) {
				return buildPath(end);
			}
			
			//Don't repeat this node
			reachable.remove(node);
			explored.add(node);
			
			//Add neighbours
			Node[] neighbours = node.getNeighbours();
			for(int i = 0; i < 3; i++) {
				if(neighbours[0] != null) {
					if(!explored.contains(neighbours[0])) {
						if(!reachable.contains(neighbours[0])) {
							neighbours[0].previous = node;
							reachable.add(neighbours[0]);
						}
					}
				}
			}
		}
		return null;
	}
	
	private static List<Node> buildPath(Node toNode) {
		List<Node> path = new ArrayList<Node>();
		while(toNode != null) {
			path.add(toNode);
			toNode = toNode.previous;
		}
		return path;
	}
}
