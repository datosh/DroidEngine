package util;

public class Point {
	private double x, y;
	
	/* 
	 * CONSTRUCTORS
	 */
	
	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(Point point) {
		this.x = point.getX();
		this.y = point.getY();
	}
	
	/* 
	 * GETTER
	 */
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	/* 
	 * SETTER
	 */
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setPosition(Point p) {
		this.x = p.getX();
		this.y = p.getY();
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/* 
	 * OTHER METHODS
	 */
	
	public double distance(Point p) {
		return Math.sqrt((this.x - p.getX()) * (this.x - p.getX()) + (this.y - p.getY()) * (this.y - p.getY())); 
	}
}
