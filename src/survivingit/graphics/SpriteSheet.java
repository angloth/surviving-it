package survivingit.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {

    public static final SpriteSheet HERO = new SpriteSheet("hero.png", 72, 208);
    public static final SpriteSheet SNOW_TILES = new SpriteSheet("snow_tiles.png");
    public static final SpriteSheet FOXES = new SpriteSheet("foxes.png");
    public static final SpriteSheet MEME_MAN = new SpriteSheet("mememan.png");
    public static final SpriteSheet CAMP_FIRE = new SpriteSheet("campfire.png");

    private int width;
    private int height;
    private BufferedImage image;


    public SpriteSheet(String path) {
        // Reads width and height from image itself
        try {
            this.image = ImageIO.read(new File("res/spritesheets/", path));
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (IOException e)  {
            System.out.println("Error reading image " + path);
        }
    }

    public SpriteSheet(String path, int width, int height) {
        // Take width and height as parameters, to make a spritsheet that only contains part of spritesheet
        try {
            this.image = ImageIO.read(new File("res/spritesheets/", path));
            this.width = width;
            this.height = height;
        } catch (IOException e)  {
            System.out.println("Error reading image " + path);
        }
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public BufferedImage getImage() {
	return image;
    }

}
