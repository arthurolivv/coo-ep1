import java.util.List;

public abstract class EnemyGeneric extends GameObject {

    protected double v;
    protected double angle;
    protected double rv;
    protected double explosion_start;
    protected double explosion_end;

    public EnemyGeneric(int state, double x, double y, double radius, double v, double angle, double rv) {
        super(state, x, y, radius);
        this.v = v;
        this.angle = angle;
        this.rv = rv;
        this.explosion_start = 0;
        this.explosion_end = 0;
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

}
