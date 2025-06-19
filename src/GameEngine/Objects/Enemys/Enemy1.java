package GameEngine.Objects.Enemys;

import GameEngine.Objects.Player;

public class Enemy1 extends EnemyGeneric{

    private long nextShoot;

    public Enemy1(int projectileCount, double projectile_radius, double enemy_radius, long nextEnemy) {
        super(projectileCount, projectile_radius, enemy_radius, nextEnemy);
        this.nextShoot = nextShoot;
    }

    @Override
    public boolean shouldSpawn(long currentTime) {
        return currentTime > nextShoot;
    }

    public long getNextShoot(){
        return this.nextShoot;
    }

    public void setNextShoot(long nextShoot){
        this.nextShoot = nextShoot;
    }

}
