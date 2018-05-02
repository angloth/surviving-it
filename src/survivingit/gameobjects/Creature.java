package survivingit.gameobjects;

import survivingit.graphics.CreatureSprite;
import survivingit.messaging.Message;
import survivingit.messaging.MessageType;

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

    public Creature(final double x, final double y, final CreatureSprite sprites, final int maxHealth, final double moveSpeed, final int damage, final int alphaLevel, final double range) {
	    super(x, y, sprites.getSprite());
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

    public void update(double dt) {
        if(this.direction != Direction.NONE) {
            this.move(this.direction.x * this.moveSpeed * dt, this.direction.y * this.moveSpeed * dt);
        }

        // Update sprites
        sprites.update(dt, this.direction, this.moveSpeed, this.isMoving());
        this.sprite = sprites.getSprite();

        // Keep track of movement
        this.lastX = this.x;
        this.lastY = this.y;
    }

    public int getCurrentHealth() {
	    return currentHealth;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public double getMoveSpeed() {
	    return moveSpeed;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setCurrentHealth(final int currentHealth) {
	    this.currentHealth = currentHealth;
    }

    public void setMaxHealth(final int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void takeDamage(int amount) {
        this.setCurrentHealth(currentHealth - amount);
        if(currentHealth < 0) {
            // Creature dead
            this.setCurrentHealth(0);
            this.death();
        }
    }

    public void setMoveSpeed(final double moveSpeed) {
	    this.moveSpeed = moveSpeed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isMoving() {
        return Math.abs(this.x - this.lastX) > 0.00001 || Math.abs(this.y - this.lastY) > 0.00001;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getAlphaLevel() {
        return this.alphaLevel;
    }

    public void heal(int healAmount) {
        // Don't set health to higher to than max.
        int newHealth = currentHealth + healAmount;
        if(newHealth > maxHealth) newHealth = maxHealth;
        this.setCurrentHealth(newHealth);
        //this.setCurrentHealth(currentHealth + Math.max(healAmount - maxHealth, 0)); // <- Doesn't work?
    }

    public double getRange() {
        return this.range;
    }

    public void performAttack(final int damage, final double range, final double angle) {
        sendMesageToCreaturesInArea(new Message(MessageType.ATTACK, damage), range, range);
        System.out.println("attack");
    }

    public double getAngleTo(final double targetX, final double targetY) {
        return Math.atan2(targetY - this.y, targetX - this.x);
    }

    public void igniteFirePlaces() {
    }

    public void death() {
        this.alive = false;
    }

}
