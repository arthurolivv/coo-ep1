package GameObjects.EnemyObject.Enemys.Type1;

import GameObjects.EnemyObject.EnemyGeneric;
import libs.GameLib;

import java.awt.*;

public class Enemy1 extends EnemyGeneric {

    private static final double RADIUS = 9.0;

    public Enemy1(double x, double y, double v, double angle, double rv) {
        super(INACTIVE, x, y, RADIUS, v, angle, rv);
    }

    @Override
    public void updatePosition(long delta) {
        if (getState() == ACTIVE) {
            angle += rv * delta;
            setX(getX() + v * Math.cos(angle) * delta);
            setY(getY() + v * Math.sin(angle) * delta * (-1.0));

            if (getY() < 0 || getX() < 0 || getX() > GameLib.WIDTH) {
                setState(INACTIVE);
            }
        }
    }

    @Override
    public void renderNormal() {
        GameLib.setColor(Color.CYAN);
        GameLib.drawCircle(this.getX(), this.getY(), this.getRadius());
    }

    public void activate(double x, double y, double v,
                         double angle, double rv, long currentTime) {
        this.state = ACTIVE;
        this.x = x;
        this.y = y;
        this.v = v;
        this.angle = angle;
        this.rv = rv;
        this.nextShoot = currentTime+500;
    }

    public long getNextShoot() {
        return nextShoot;
    }

    public void setNextShoot(long nextShoot) {
        this.nextShoot = nextShoot;
    }

}
