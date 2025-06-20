public class GameObject {

    public static final int INACTIVE = GameStates.INACTIVE.getValue();
    public static final int ACTIVE = GameStates.ACTIVE.getValue();
    public static final int EXPLODING = GameStates.EXPLODING.getValue();

    private int state;
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double radius;

    public GameObject(int state, double x, double y, double vx, double vy, double radius) {
        this.state = state;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
    }

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

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
