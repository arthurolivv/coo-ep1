package game.entities;

import game.util.Status;
import game.util.Vector2D;

public abstract class GameObject {

    protected Vector2D position;
    protected Vector2D velocity;
    protected double radius;
    protected Status status;

    protected long explosionStart;
    protected long explosionEnd;

    public GameObject(Vector2D position, Vector2D velocity, double radius) {
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
        this.status = Status.ACTIVE;
    }

    public void explode(long currentTime) {
        this.status = Status.EXPLODING;
        this.explosionStart = currentTime;
        this.explosionEnd = currentTime + 2000;
    }

    public boolean isExploding(long currentTime) {
        return (status == Status.EXPLODING && currentTime <= explosionEnd);
    }

    public double getExplosionAlpha(long currentTime) {
        return (currentTime - explosionStart) / (double)(explosionEnd - explosionStart);
    }

    public Vector2D getPosition() {
         return position; 
    }
    public Vector2D getVelocity() {
         return velocity; 
    }
    public double getRadius() {
         return radius;
    }
    public Status getStatus() {
         return status; 
    }
}
