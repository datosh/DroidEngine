package util;

public class Vector2D {
	protected double x;
	protected double y;
	
	public Vector2D() {
		x = 0;
		y = 0;
	}
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Vector2D vector) {
		this.x = vector.x;
		this.y = vector.y;
	}
	
	public String toString() {
		return "Vector2D(" + x + "," + y + ")";
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getLength() {
		return Math.sqrt(x*x + y*y);
	}
	
	//Returns a new Vector2D object which is the result of
	//this vector + vec
	public Vector2D add(Vector2D vec) {
		return new Vector2D(this.x + vec.x, this.y + vec.y);
	}
	
	public Vector2D sub(Vector2D vec) {
		return new Vector2D(this.x - vec.x, this.y - vec.y);
	}
	
	public void translate(Vector2D vec) {
		this.x = this.x + vec.x;
		this.y = this.y + vec.y;
	}
	
	public void translate(double x, double y) {
		this.x = this.x + x;
		this.y = this.y + y;
	}
	
	public Vector2D scale(double s) {
		return new Vector2D(this.x * s, this.y * s);
	}
	
	public void scaleThis(double s) {
		this.x = this.x * s;
		this.y = this.y * s;
	}
	
	//(a.x/length(a), a.y/length(a))
	public Vector2D normalize() {
		Vector2D vec = new Vector2D();
		
		double length = Math.sqrt(this.x * this.x + this.y * this.y);
		if(length != 0) {
			vec.x = this.x/length;
			vec.y = this.y/length;
		}
		
		return vec;
	}
	
	public void normalizeThis() {
		double length = getLength();
		
		if(length != 0) {
			this.x = this.x/length;
			this.y = this.y/length;
		}
	}
	
	//a.x * b.x + a.y * b.y
	public double dotProduct(Vector2D vec) {
		return this.x * vec.x + this.y * vec.y;
	}
	
	public Vector2D negateY() {
		this.y = -y;
		return this;
	}
}