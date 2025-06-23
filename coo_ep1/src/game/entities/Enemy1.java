package game.entities;

import game.GameLib;
import game.util.Vector2D;
import game.util.Status;

import java.awt.Color;

public class Enemy1 extends EnemyGeneric {

    private static long spawnCooldown = 500;
    private static long nextSpawnTime = 0;

    public static long getSpawnCooldown() {
        return spawnCooldown;
    }

    public static void setSpawnCooldown(long cooldown) {
        spawnCooldown = cooldown;
    }

    public static long getNextSpawnTime() {
        return nextSpawnTime;
    }

    public static void setNextSpawnTime(long time) {
        nextSpawnTime = time;
    }

    public Enemy1(Vector2D position, Vector2D velocity, double radius, int health, int damage, long fireCooldown, double explosionStart, double explosionEnd, double dydx) {
        super(position, velocity, radius, health, damage, fireCooldown, explosionStart, explosionEnd, dydx);
    }

    @Override
    public void update(long delta) {
        double newX = getPosition().getX() + getVelocity().getX() * delta;
        double newY = getPosition().getY() + getVelocity().getY() * delta;
        newX += getVelocity().getY() * getDydx() * delta;

        getPosition().setX(newX);
        getPosition().setY(newY);

        if (newY > GameLib.HEIGHT) setStatus(Status.INACTIVE);
    }

    @Override
    public void render(long currentTime) {
        if (getStatus() == Status.ACTIVE) {
            GameLib.setColor(Color.YELLOW);
            GameLib.drawCircle(getPosition().getX(), getPosition().getY(), getRadius());
        } else if (getStatus() == Status.EXPLODING) {
            double alpha = (currentTime - getExplosionStart()) / (getExplosionEnd() - getExplosionStart());
            if (alpha < 1.0)
                GameLib.drawExplosion(getPosition().getX(), getPosition().getY(), alpha);
            else
                setStatus(Status.INACTIVE);
        }
    }
}
