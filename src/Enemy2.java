public class Enemy2 extends EnemyGeneric implements Enemy{

    private double spawnX;
    private int count;

    public Enemy2(int state, double x, double y, double vx, double vy, double radius, double v, double angle, double rv, double explosion_start, double explosion_end, long nextEnemy, int count) {
        super(state, x, y, vx, vy, radius, v, angle, rv, explosion_start, explosion_end, nextEnemy);
        this.spawnX = GameLib.WIDTH * 0.20;
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
