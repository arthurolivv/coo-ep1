package GameObjects.EnemyObject.Enemys.Type1;

import GameObjects.EnemyObject.EnemyGeneric;
import libs.GameLib;

import java.awt.*;

public class Enemy1 extends EnemyGeneric {

    private long nextShoot;

    public Enemy1(int state, double x, double y, double radius, double v, double angle, double rv) {
        super(state, x, y, radius, v, angle, rv);
    }

    @Override
    public void renderNormal() {
        GameLib.setColor(Color.CYAN);
        GameLib.drawCircle(this.getX(), this.getY(), this.getRadius());
    }

    public long getNextShoot() {
        return nextShoot;
    }

    public void setNextShoot(long nextShoot) {
        this.nextShoot = nextShoot;
    }

    @Override
    public void updatePosition(long delta) {

    }

}
