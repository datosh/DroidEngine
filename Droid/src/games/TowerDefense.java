package games;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import util.Node;
import util.Point;

public class TowerDefense extends Game {
	private static final long serialVersionUID = -5886478184749806456L;

	private Image background;
	private List<Node> board;
	
	public TowerDefense(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		board = new ArrayList<Node>(50);
	}
	
	@Override
	public void init() {
		try {
			background = ImageIO.read(new File("assets/tower_defense/background.png"));
		} catch (IOException e) {
			System.err.println("Error Loading background.png");
		}
		
		board.add(new Node(0, 51, 86, 73, 0, 111));
		board.add(new Node(86, 73, 0, 111, 86, 135));
		board.add(new Node(0, 111, 86, 135, 0, 171));
		board.add(new Node(86, 135, 0, 171, 86, 207));
		board.add(new Node(0, 171, 86, 207, 0, 227));
		board.add(new Node(86, 73, 168, 79, 86, 106));
		board.add(new Node(168, 79, 86, 106, 168, 135));
		board.add(new Node(86, 106, 168, 135, 86, 171));
		board.add(new Node(168, 135, 86, 171, 168, 199));
		board.add(new Node(86, 171, 168, 199, 86, 207));
		board.add(new Node(168, 79, 256, 83, 168, 109));
		board.add(new Node(256, 83, 168, 109, 256, 137));
		board.add(new Node(168, 109, 256, 137, 168, 169));
		board.add(new Node(256, 137, 168, 169, 256, 196));
		board.add(new Node(168, 169, 256, 196, 168, 198));
		
		board.add(new Node(512 - 0, 51, 512 - 86, 73, 512 - 0, 111));
		board.add(new Node(512 - 86, 73, 512 - 0, 111, 512 - 86, 135));
		board.add(new Node(512 - 0, 111, 512 - 86, 135, 512 - 0, 171));
		board.add(new Node(512 - 86, 135, 512 - 0, 171, 512 - 86, 207));
		board.add(new Node(512 - 0, 171, 512 - 86, 207, 512 - 0, 227));
		board.add(new Node(512 - 86, 73, 512 - 168, 79, 512 - 86, 106));
		board.add(new Node(512 - 168, 79, 512 - 86, 106, 512 - 168, 135));
		board.add(new Node(512 - 86, 106, 512 - 168, 135, 512 - 86, 171));
		board.add(new Node(512 - 168, 135, 512 - 86, 171, 512 - 168, 199));
		board.add(new Node(512 - 86, 171, 512 - 168, 199, 512 - 86, 207));
		board.add(new Node(512 - 168, 79, 512 - 256, 83, 512 - 168, 109));
		board.add(new Node(512 - 256, 83, 512 - 168, 109, 512 - 256, 137));
		board.add(new Node(512 - 168, 109, 512 - 256, 137, 512 - 168, 169));
		board.add(new Node(512 - 256, 137, 512 - 168, 169, 512 - 256, 196));
		board.add(new Node(512 - 168, 169, 512 - 256, 196, 512 - 168, 198));
		
		Node.connectUni(board.get(0), board.get(1));
	}

	@Override
	public void tick(long delta) {
		// TODO Auto-generated method stub
		Point click = INPUT.getClick();
		if(click != null) {
			System.out.println(click);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(background, 0, 0, null);
		
		Iterator<Node> it = board.iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new TowerDefense("Tower Defense").start();
	}

}
