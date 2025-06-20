import java.util.List;

public class Enemy2 extends EnemyGeneric{

    private double spawnX;
    private int count;

    public Enemy2(int state, double x, double y, double radius, double v, double angle, double rv, double spawnX, int count) {
        super(state, x, y, radius, v, angle, rv);
        this.spawnX = spawnX;
        this.count = count;
    }

    public double getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(double spawnX) {
        this.spawnX = spawnX;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void updatePosition(long delta) {

    }
}
