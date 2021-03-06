package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.graphics.SpriteSheet;
import survivingit.scene.Scene;

/**
 * Class for the Fox animal, inherits all behaviour from Animal.
 *
 * @see Animal
 */
public class Fox extends Animal {

    private static final int MAX_HEALTH = 20;
    private static final double MOVE_SPEED = 2.0;
    private static final int DAMAGE = 1;
    private static final int ALPHA_LEVEL = 1;
    private static final double VIEW_DISTANCE = 10.0;
    private static final double RANGE = 1.0;

    private static final double COL_X = -0.2;
    private static final double COL_Y = -0.2;
    private static final double COL_WIDTH = 0.4;
    private static final double COL_HEIGHT = 0.35;

    private static final int SPRITE_WIDTH = 43;
    private static final int SPRITE_HEIGHT = 40;

    /**
     * Creates a new Fox object with the entered x and y position.
     * @param x double val of the x position of the new Fox object.
     * @param y double val of the y position of the new Fox object.
     */
    public Fox(final double x, final double y, final Scene scene) {
        super(x,
              y,
              scene,
              new CreatureSprite(SpriteSheet.FOX, 0, 0, SPRITE_WIDTH, SPRITE_HEIGHT),
              MAX_HEALTH,
              MOVE_SPEED,
              DAMAGE,
              ALPHA_LEVEL,
              VIEW_DISTANCE,
              RANGE);

        this.setCollider(new Collider(COL_X, COL_Y, COL_WIDTH, COL_HEIGHT, false, this));
    }

}
