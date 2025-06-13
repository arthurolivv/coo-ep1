package GameEngine.Objects.Enemys;

public class Enemy2 extends EnemyGeneric{

    private double spawnX;
    private int count;

    public Enemy2(int projectileCount, double projectile_radius, double enemy_radius, long nextEnemy, double spawnX, int count) {
        super(projectileCount, projectile_radius, enemy_radius, nextEnemy);
        this.spawnX = spawnX;
        this.count = count;
    }
}
