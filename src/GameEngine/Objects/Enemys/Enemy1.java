package GameEngine.Objects.Enemys;

public class Enemy1 extends EnemyGeneric{

    private long nextShoot;

    public Enemy1(int projectileCount, double projectile_radius, double enemy_radius, long nextEnemy) {
        super(projectileCount, projectile_radius, enemy_radius, nextEnemy);
    }
}
