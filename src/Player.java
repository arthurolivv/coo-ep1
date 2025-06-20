public class Player extends GameObject {

    double explosion_start;
    double explosion_end;
    long nextShot;

    public Player(int state, double x, double y, double vx, double vy, double radius, double explosion_start, double explosion_end, long nextShot) {
        super(state, x, y, vx, vy, radius);
        this.explosion_start = explosion_start;
        this.explosion_end = explosion_end;
        this.nextShot = nextShot;
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

    public long getNextShot() {
        return nextShot;
    }

    public void setNextShot(long nextShot) {
        this.nextShot = nextShot;
    }
}
