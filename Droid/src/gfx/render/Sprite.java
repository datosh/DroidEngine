package gfx.render;

import java.awt.Graphics;
import java.awt.Image;

public class Sprite {
	private Image image;
	
	public Sprite(Image image) {
		this.image = image;
	}
	
	public void draw(int x, int y, Graphics g) {
		g.drawImage(image, x, y, null);
	}
}
