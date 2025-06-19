package GameEngine.Objects.Enemys;

import libs.GameLib;

public class Enemy2 extends EnemyGeneric{

    private long spawnTime;
    private double spawnX;
    private int count;

    public Enemy2(int projectileCount, double projectile_radius, double enemy_radius, long nextEnemy, double spawnX, int count) {
        super(projectileCount, projectile_radius, enemy_radius, nextEnemy);
        this.spawnTime = spawnTime;
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

    public void incrementCount() {
        this.count++;
    }

    @Override
    public boolean shouldSpawn(long currentTime) {
        return currentTime > spawnTime;
    }

    public void resetSpawnTimer(long currentTime, boolean restartFormation) {
        if (restartFormation) {
            this.count = 0;
            this.spawnX = Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8;
            this.spawnTime = (long) (currentTime + 3000 + Math.random() * 3000);
        } else {
            this.count++;
            this.spawnTime = currentTime + 120;
        }
    }
}
