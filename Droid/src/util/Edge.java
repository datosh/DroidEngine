package util;

public class Edge {
	public static int STANDARD_COSTS = 1;
	
	private static int ID_COUNTER = 0;
	private int id;
	private Node node;
	private int costs;
	
	/*
	 * CONSTRUCTORS
	 */
	
	public Edge() {
		node = null;
		costs = 0;
		id = ID_COUNTER;
		ID_COUNTER++;
	}
	
	public Edge(Node node, int costs) {
		this.node = node;
		this.costs = costs;
		id = ID_COUNTER;
		ID_COUNTER++;
	}
	
	/*
	 * GETTER & SETTER
	 */
	
	public int getID() {
		return id;
	}
	
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public int getCosts() {
		return costs;
	}

	public void setCosts(int costs) {
		this.costs = costs;
	}
	
	/* 
	 * OTHER METHODS
	 */
	
	public boolean areNeighbours(Node node) {
		return this.node == node;
	}
	
}
