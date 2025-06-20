public class Enemy1 extends EnemyGeneric implements Enemy{

    protected long nextShoot;

    public Enemy1(int state, double x, double y, double vx, double vy, double radius, double v, double angle, double rv, double explosion_start, double explosion_end, long nextEnemy, long nextShoot) {
        super(state, x, y, vx, vy, radius, v, angle, rv, explosion_start, explosion_end, nextEnemy);
        this.nextShoot = nextShoot;
    }

    public long getNextShoot() {
        return nextShoot;
    }

    public void setNextShoot(long nextShoot) {
        this.nextShoot = nextShoot;
    }

    @Override
    public void update(long currentTime) {

    }

    @Override
    public void render() {

    }

    @Override
    public double getRv() {
        return 0;
    }
}
