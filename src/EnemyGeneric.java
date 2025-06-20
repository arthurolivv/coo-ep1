public class EnemyGeneric extends GameObject {

    protected double v;
    protected double angle;
    protected double rv;
    protected double explosion_start;
    protected double explosion_end;
    protected long nextEnemy;

    long currentTime = System.currentTimeMillis();

    public EnemyGeneric(int state, double x, double y, double vx, double vy, double radius, double v, double angle, double rv, double explosion_start, double explosion_end, long nextEnemy) {
        super(state, x, y, vx, vy, radius);
        this.nextEnemy = nextEnemy;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getRv() {
        return rv;
    }

    public void setRv(double rv) {
        this.rv = rv;
    }

    public double getExplosion_start() {
        return explosion_start;
    }

    public void setExplosion_start(double explosion_start) {
        this.explosion_start = explosion_start;
    }

    public double getExplosion_end() {
        return explosion_end;
    }

    public void setExplosion_end(double explosion_end) {
        this.explosion_end = explosion_end;
    }

    public long getNextEnemy() {
        return nextEnemy;
    }

    public void setNextEnemy(long nextEnemy) {
        this.nextEnemy = nextEnemy;
    }
}
