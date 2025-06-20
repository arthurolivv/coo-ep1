import java.util.List;

public class Enemy1 extends EnemyGeneric{

    protected long nextShoot;

    public Enemy1(int state, double x, double y, double radius, double v, double angle, double rv) {
        super(state, x, y, radius, v, angle, rv);
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
