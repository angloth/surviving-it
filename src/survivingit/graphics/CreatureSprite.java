package survivingit.graphics;

import survivingit.gameobjects.Direction;

// TODO: Allow for idle animations (?)

public class CreatureSprite {

    private AnimatedSprite left;
    private AnimatedSprite up;
    private AnimatedSprite right;
    private AnimatedSprite down;

    private AnimatedSprite current;

    public CreatureSprite(SpriteSheet spriteSheet, int subX, int subY, int spriteWidth, int spriteHeight) {
        // spriteSheet is required to consist of 3*4 sprites, where each row contains 3 frames.
        // Row 1 is the animation for walking down, row 2 = left, row 3 = right and row 4 = up
        this.left = new AnimatedSprite(spriteSheet, subX, subY, spriteWidth, spriteHeight, 3, 1, 0.2);
        this.up = new AnimatedSprite(spriteSheet, subX, subY + spriteHeight, spriteWidth, spriteHeight, 3, 1, 0.2);
        this.right = new AnimatedSprite(spriteSheet, subX, subY + 2*spriteHeight, spriteWidth, spriteHeight, 3, 1, 0.2);
        this.down = new AnimatedSprite(spriteSheet, subX, subY + 3*spriteHeight, spriteWidth, spriteHeight, 3, 1, 0.2);

        // Make all animated sprites oscillate (for correct walking animation)
        this.left.setOscillating(true);
        this.up.setOscillating(true);
        this.right.setOscillating(true);
        this.down.setOscillating(true);

        this.current = this.down;
    }

    public void update(double dt, Direction direction, double moveSpeed, boolean isMoving) {

        // Decide which animated sprite to use
        switch(direction) {
            case LEFT:
            case UP_LEFT:
            case DOWN_LEFT:
                this.current = left;
                break;
            case UP:
                this.current = up;
                break;
            case RIGHT:
            case UP_RIGHT:
            case DOWN_RIGHT:
                this.current = right;
                break;
            case DOWN:
                this.current = down;
                break;
            case NONE:
            default:
                // Keep old sprites
                break;
        }

        // Set frame time based on move speed
        current.setFrameLength(1 / moveSpeed);

        // Update it if animal is moving
        if(isMoving) {
            this.current.update(dt);
        } else {
            // If not moving, set frame to middle (idle)
            this.current.setFrame(1);
        }
    }

    public Sprite getSprite() {
        return current.getSprite();
    }

}
