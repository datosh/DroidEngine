package util;

public class Line2D {
	double a, b, c;
	
	/*
	 * CONSTRUCTORS
	 */
	
	public Line2D() {
		a = 0; 
		b = 0;
		c = 0;
	}
	
	public Line2D(Vector2D j, Vector2D k) {
		a = k.getY() - j.getY();
		b = j.getX() - k.getX();
		c = a * j.getX() + b * k.getX();
	}

	/*
	 * GETTER & SETTER
	 */
	
	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}
	
	/*
	 * OTHER METHODS
	 */
	
	/**
	 * Calculates the point where the two lines intersect.
	 * @param j one line
	 * @param k other line
	 * @return Point where the lines intersect, or null if they are parallel. 
	 */
	public static Vector2D intersection(Line2D j, Line2D k) {
		double det = j.getA() * k.getB() - k.getA() * j.getB();
		
		if(det == 0) {
			return null;
		} else {
			double x = (k.getB() * j.getC() - j.getB() * k.getC()) / det;
			double y = (j.getA() * k.getC() - k.getA() * j.getC()) / det;
			return new Vector2D(x, y);
		}
	}
}
