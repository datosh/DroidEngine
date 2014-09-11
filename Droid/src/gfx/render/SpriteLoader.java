package gfx.render;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Simple sprite loader. Uses a HashMap to cache already loaded images. 
 * Utilizes GraphicsConfiguration to get an accelerated image for the current
 * screen device. 
 * 
 * SOURCE: http://www.cokeandcode.com/info/showsrc/showsrc.php?src=../spaceinvaders/org/newdawn/spaceinvaders/SpriteStore.java
 * 
 * @author datosh
 *
 */


public class SpriteLoader {
	private static SpriteLoader loader = new SpriteLoader();
	
	private HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
	
	/**
	 * Uses singleton to return the one SpriteLoader
	 * @return single instance of this class
	 */
	public static SpriteLoader get() {
		return loader;
	}
	
	/**
	 * Returns the sprite if already loaded, otherwise loads it from the disk/resources. 
	 * @param loc Location of the sprite
	 * @return the requested sprite
	 */
	public Sprite getSprite(String loc) {
		return getSprite(loc, -1, -1);
	}
	
	/**
	 * Returns a scaled instance of the sprite if already loaded, otherwise loads it from the disk/resources.
	 * @param loc Location of the sprite
	 * @param width Width of the Sprite after scaling
	 * @param height Height of the Sprite after scaling
	 * @return requested sprite
	 */
	public Sprite getSprite(String loc, int width, int height) { 
		//If sprite is already caches simply return
		if(sprites.get(loc) != null) {
			return sprites.get(loc);
		}
		
		BufferedImage sourceImage = null;
		
		try {
			//URL url = this.getClass().getClassLoader().getResource(loc);
			File file = new File(loc);
			
			sourceImage = ImageIO.read(file);
			
		} catch (IOException e) {
			System.err.println("Not able to load " + loc + "!");
			return null;
		}
		
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		Image image = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), Transparency.TRANSLUCENT);
		//-1 is used when the constructor without width and height information is used. So use the standard dimensions of the image
		if(width == -1 && height == -1) {
			image.getGraphics().drawImage(sourceImage, 0, 0, null);
		} else if(width <= 0 || height <= 0) { //Negative numbers and zero arent valid dimensions
			System.err.println("Wrong height and width parameter when loading:" + loc + " for width: " + width + " and heigt: " + height);
			return null;
		} else { //Finally load the scaled image instead
			image.getGraphics().drawImage(sourceImage, 0, 0, width, height, 0, 0, image.getWidth(null), image.getHeight(null), null);
		}
		Sprite sprite = new Sprite(image);
		sprites.put(loc, sprite);
		
		return sprite;
	}
	
}
