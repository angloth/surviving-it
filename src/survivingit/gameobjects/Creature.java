package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.messaging.Message;
import survivingit.messaging.MessageType;
import survivingit.scene.Scene;

/**
 * Abstract superclass for all Creatures, inherits from VisibleObject.
 *
 * Contains data and functionality concerning health, movement and interaction with other gameObjects/creatures such as
 * performing attacks and igniting fireplaces.
 *
 * @see VisibleObject
 */
public abstract class Creature extends VisibleObject {

    protected int currentHealth;
    protected int maxHealth;
    protected double moveSpeed; // Tiles per second
    protected int damage; // Attack damage
    protected Direction direction;
    protected int alphaLevel; // Creatures will flee from creatures with higher level and performAttack creatures with lower alpha level
    protected double range;

    private double lastX;
    private double lastY;

    protected CreatureSprite sprites;

    /**
     * Creates a new Creature object with the entered x and y position, creature sprites, max health, movement speed,
     * damage, alphalevel and range.
     * @param x double of the creature's inital deltaX coordinate.
     * @param y double of the creature's inital deltaX coordinate.
     * @param sprites Creature sprite of the creature's sprites
     * @param maxHealth int  of the creature's max health.
     * @param moveSpeed double of the creature's initial movement speed.
     * @param damage int of the creature's performAttack damage.
     * @param alphaLevel int of the creature's alpha level.
     * @param range double of the creature's performAttack range.
     */
    protected Creature(double x, double y, Scene scene, CreatureSprite sprites, int maxHealth, double moveSpeed, int damage, int alphaLevel, double range) {
	    super(x, y, scene, sprites.getSprite());
	    this.currentHealth = maxHealth;
	    this.maxHealth = maxHealth;
	    this.moveSpeed = moveSpeed;
	    this.damage = damage;
	    this.direction = Direction.NONE;
	    this.alphaLevel = alphaLevel;
	    this.range = range;

	    this.lastX = x;
	    this.lastY = y;

	    this.sprites = sprites;
    }

    /**
     * Update method for the creature which updates the creature with the entered amount of passed time.
     * @param dt double value of the amount of time that has passed.
     */
    @Override
    public void update(double dt) {
        if (this.direction != Direction.NONE) {
            this.move(this.direction.deltaX * this.moveSpeed * dt, this.direction.deltaY * this.moveSpeed * dt);
        }

        // Update sprites
        sprites.update(dt, this.direction, this.moveSpeed, this.isMoving());
        this.sprite = sprites.getSprite();

        // Keep track of movement
        this.lastX = this.x;
        this.lastY = this.y;
    }

    /**
     * Returns the creature's current health.
     * @return int value of the creature's current health.
     */
    public int getCurrentHealth() {
	    return currentHealth;
    }

    /**
     * Returns the creature's max health.
     * @return int value of the creature's max health.
     */
    public int getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * Returns the creature's movement speed.
     * @return double value of the creature's movement speed.
     */
    public double getMoveSpeed() {
	    return moveSpeed;
    }

    /**
     * Sets the creature's current health to the entered value.
     * @param currentHealth int value to set the creature's current health to.
     */
    public void setCurrentHealth(final int currentHealth) {
        if (currentHealth < 0 || currentHealth > maxHealth) {
            throw new IllegalArgumentException("Negative or above max health entered");
        }
	    this.currentHealth = currentHealth;
    }

    /**
     * The creature takes the entered amount of damage.
     * @param amount Amount of damage to take
     */
    public void takeDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Negative amount of damage attempted to be taken.");
        }
        int newHealth = this.currentHealth - amount;
        if (newHealth < 0) {
            // Creature died
            this.setCurrentHealth(0);
            this.death();
        } else {
            this.setCurrentHealth(newHealth);
        }
    }

    /**
     * Sets the creature's movement speed to the entered value.
     * @param moveSpeed double value to set the creature's movement speed to.
     */
    public void setMoveSpeed(final double moveSpeed) {
	    this.moveSpeed = moveSpeed;
    }

    /**
     * Increase/decrease creature's movespeed
     * @param deltaSpeed movespeed delta
     */
    public void changeMoveSpeed(final double deltaSpeed) {
        this.moveSpeed += deltaSpeed;
    }

    /**
     * Sets the creature's direction to the entered enum value.
     * @param direction Direction enum value to set the creature's direction to.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Returns a boolean of if the creature is moving or not.
     * @return if the creature is moving or not.
     */
    public boolean isMoving() {
        return Double.compare(this.x, this.lastX) != 0 || Double.compare(this.y, this.lastY) != 0;
    }

    /**
     * Returns the creature's damage.
     * @return int value of the creature's damage.
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Returns the creature's alpha level.
     * @return int value of the creature's alpha level.
     */
    public int getAlphaLevel() {
        return this.alphaLevel;
    }

    /**
     * Heals the creature with the entered amount.
     * @param healAmount int value to heal the creature with.
     */
    public void heal(int healAmount) {
        // Don't set health to higher to than max.
        int newHealth = currentHealth + healAmount;
        if (newHealth > maxHealth) {
            newHealth = maxHealth;
        }
        this.setCurrentHealth(newHealth);
    }

    /**
     * Returns the creature's range value.
     * @return double of the creatures range.
     */
    public double getRange() {
        return this.range;
    }

    /**
     * The creature performs an attack with the entered damage, range and angle.
     * @param damage int value of the damage of the attack.
     * @param range double value of the range of the attack.
     */
    public void performAttack(final int damage, final double range) {
        sendMessageToCreaturesInArea(new Message(MessageType.DAMAGE, damage), range, range);
    }

    /**
     * Ignites the fireplaces in the entered rectangle of wifth and square around the creature for the entered fireTime.
     * @param fireTime int of the amount of time for the fires to be active.
     * @param width double of the width around the player which can be lit on fire.
     * @param height double of the height around the player which can be lit on fire.
     */
    public void igniteFirePlaces(final int fireTime, final double width, final double height) {
        sendMessageToGameObjectsInArea(new Message(MessageType.IGNITE, fireTime), width, height);
    }

    private void death() {
        this.alive = false;
    }

}
