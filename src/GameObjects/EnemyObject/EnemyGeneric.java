package GameObjects.EnemyObject;

import GameObjects.GameObject;
import libs.GameLib;

import java.awt.*;

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

    private void renderExplosion(long currentTime) {
        double alpha = (currentTime - this.getExplosion_start()) /
                (this.getExplosion_end() - this.getExplosion_start());
        GameLib.drawExplosion(this.getX(), this.getY(), alpha);
    }

    public void render(long currentTime) {
        if (this.getState() == EXPLODING) {
            renderExplosion(currentTime);
        } else if (this.getState() == ACTIVE) {
            renderNormal();
        }
    }

    public abstract void renderNormal();

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
