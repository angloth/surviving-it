package survivingit.graphics;

import survivingit.gameobjects.GameVisibleObject;
import survivingit.scene.Tile;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Renderer extends Canvas {

    private static final boolean DEBUG = true;


    public static final int UNIT_SIZE = 16; // Size of 1 game unit in pixels
    private static final int SPRITE_PADDING = 1; // Extra padding to be added to sprite size when rendering

    private int width;
    private int height;

    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    public Renderer(int width, int height) {
        super();

        this.width = width;
        this.height = height;

        this.setSize(width, height);
        this.setVisible(true);
        this.setFocusable(false);

    }

    public void prepare() {
	bufferStrategy = this.getBufferStrategy();
	graphics = bufferStrategy.getDrawGraphics();
    }

    public void clear() {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);
    }

    public void display() {
        graphics.dispose(); // Release system resources
	bufferStrategy.show();
    }

    public void drawSprite(double x, double y, Sprite sprite, double cameraWidth, double cameraHeight) {

        // Pixels per unit (ppu)
        double ppuWidth = this.width / cameraWidth;
        double ppuHeight = this.height / cameraHeight;


        // Position and size on screen
        int drawX = (int)(x * ppuWidth) - SPRITE_PADDING;
        int drawY = (int)(y * ppuHeight) - SPRITE_PADDING;
        int drawWidth = (int)(ppuWidth * sprite.getWidth() / UNIT_SIZE) + SPRITE_PADDING * 2;
        int drawHeight = (int)(ppuHeight * sprite.getHeight() / UNIT_SIZE) + SPRITE_PADDING * 2;

        graphics.drawImage(sprite.getImage(), drawX, drawY, drawX + drawWidth, drawY + drawHeight,
			   sprite.getX(), sprite.getY(), sprite.getX() + sprite.getWidth(), sprite.getY() + sprite.getHeight(),
			   null);

        if(DEBUG) {
            graphics.setColor(Color.black);
            graphics.drawRect(drawX, drawY, drawWidth, drawHeight);
	    }
    }

}
