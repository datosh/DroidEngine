package gfx.render;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
		Image image = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight());
		image.getGraphics().drawImage(sourceImage, 0, 0, null);
		
		Sprite sprite = new Sprite(image);
		sprites.put(loc, sprite);
		
		return sprite;
	}
	
}
