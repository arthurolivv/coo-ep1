package GameObjects.EnemyObject.Enemys.Type2;

import GameObjects.EnemyObject.EnemyGeneric;
import libs.GameLib;

import java.awt.*;

public class Enemy2 extends EnemyGeneric {

    private static final double RADIUS = 12.0;
    private static final double spawnX = GameLib.WIDTH * 0.20;
    private int count;

    public Enemy2(double y, double v, double angle,  int count) {
        super(INACTIVE, spawnX, y, RADIUS, v, angle, 0);
        this.count = count;
    }

    @Override
    public void updatePosition(long delta) {
        if (getState() == ACTIVE) {
            setY(getY() + v * delta);

            if (getY() > GameLib.HEIGHT) {
                setState(INACTIVE);
            }
        }
    }

    @Override
    public void renderNormal() {
        GameLib.setColor(Color.CYAN);
        GameLib.drawCircle(this.getX(), this.getY(), this.getRadius());
    }

    public void activate(double y, double velocity,
                         double angle, int count, long currentTime) {
        this.state = ACTIVE;
        this.x = spawnX + (count*30);
        this.y = y;
        this.v = velocity;
        this.angle = angle;
        this.count = count;
        this.nextShoot = currentTime + 300;
    }

    public double getSpawnX() {
        return spawnX;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
