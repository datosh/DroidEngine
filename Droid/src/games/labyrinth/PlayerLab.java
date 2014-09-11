package games.labyrinth;


import util.Point;
import input.InputHandler;
import entities.Player;
import games.Game;

public class PlayerLab extends Player{

	public PlayerLab(Game game, InputHandler input, Point p) {
		this(game, input, p.getX(), p.getY());
	}
	
	public PlayerLab(Game game, InputHandler input, double x, double y) {
		super(game, input, x, y);
		// TODO Auto-generated constructor stub
	}
	
}
