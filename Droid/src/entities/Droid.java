package entities;

import java.text.DecimalFormat;

import ai.Routine;
import util.Board;
import util.Point;

public class Droid {
	private final String NAME;
	private Point position;
	private double speed;
	private double range;
	private double damage;
	private double health;
	
	private Board board;
	private Routine routine;
	
	
	/*
	 * CONSTRUCTOR
	 */
	
	public Droid(String name, Point position, double range, double damage, double health) {
		this.NAME = name;
		this.position = position;
		this.range = range;
		this.damage = damage;
		this.health = health;
		this.speed = 50;
	}

	/*
	 * GETTER
	 */

	public Point getPosition() {
		return position;
	}
	
	public double getX() {
		return position.getX();
	}
	
	public double getY() {
		return position.getY();
	}
	
	public double getSpeed() {
		return speed;
	}

	public double getRange() {
		return range;
	}
	
	public double getDamage() {
		return damage;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public double getHealth() {
		return health;
	}
	
	public String getNAME() {
		return NAME;
	}
	
	/*
	 * SETTER
	 */
	
	public void setX(double x) {
		this.position.setX(x);
	}
	
	public void setY(double y) {
		this.position.setY(y);
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}
	
	public void setHealth(double health) {
		this.health = health;
	}
	
	public void setRoutine(Routine routine) {
		this.routine = routine;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}

	/*
	 * TOSTRING
	 */
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("###.#");
		return "Name: " + NAME + "@x:" + df.format(position.getX()) + ",y:" + df.format(position.getY());
	}
	
	public void update(long delta) {
		if(routine != null) {
			if(routine.getState() == null) {
				routine.start();
			}
			//routine.act(delta, this, board);
		}
	}
}
