package GameObjects;

import enums.GameStates;

public abstract class GameObject {

    public static final int INACTIVE = GameStates.INACTIVE.getValue();
    public static final int ACTIVE = GameStates.ACTIVE.getValue();
    public static final int EXPLODING = GameStates.EXPLODING.getValue();

    protected int state;
    protected double x;
    protected double y;
    protected double radius;

    public GameObject(int state, double x, double y,  double radius) {
        this.state = state;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public abstract void updatePosition(long delta);

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

}
