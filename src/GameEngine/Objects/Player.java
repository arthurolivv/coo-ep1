package GameEngine.Objects;

import GameEngine.Interfaces.GameObject;
import libs.GameLib;

// Classe Player implementando GameObject
public class Player extends Projectiles implements GameObject {

    // Atributos privados (encapsulamento)
    private int state;
    private double x;
    private double y;
    private final double velocityX, velocityY;
    private final double radius;
    private double explosionStart;
    private double explosionEnd;
    private long nextShot;

    // Construtor
    public Player(int projectileCount, double projectileRadius, int state,
                  double x, double y, double velocityX, double velocityY,
                  double radius, double explosionStart, double explosionEnd, long nextShot) {
//        this.state = ACTIVE;
//        this.x = DEFAULT_X;
//        this.y = DEFAULT_Y;
//        this.velocityX = DEFAULT_VELOCITY;
//        this.velocityY = DEFAULT_VELOCITY;
//        this.radius = DEFAULT_RADIUS;
//        this.explosionStart = 0;
//        this.explosionEnd = 0;
//        this.nextShotTime = System.currentTimeMillis();

        super(projectileCount, projectileRadius);
        this.state = state;
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.radius = radius;
        this.explosionStart = explosionStart;
        this.explosionEnd = explosionEnd;
        this.nextShot =  nextShot;

    }

    @Override
    public boolean isActive() {
        if(state == ACTIVE){
            return true;
        }
        return false;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getVX() {
        return velocityX;
    }

    @Override
    public double getVY() {
        return velocityY;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public int getState() {
        return this.state;
    }

    @Override
    public double getExplosionStart() {
        return explosionStart;
    }

    @Override
    public double getExplosionEnd() {
        return explosionEnd;
    }

    @Override
    public void setExplosionStart(double explosionStart) {
        this.explosionStart = explosionStart;
    }

    @Override
    public void setExplosionEnd(double explosionEnd) {
        this.explosionEnd = explosionEnd;
    }

    @Override
    public double getNextShot() {
        return nextShot;
    }

    @Override
    public void setNextShot(long nextShot) {
        this.nextShot = nextShot;
    }

}