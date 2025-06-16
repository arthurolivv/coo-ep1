package GameEngine.Objects.Enemys;

import GameEngine.Objects.Player;

public class Enemy1 extends EnemyGeneric{

    private long nextShoot;

    public Enemy1(int projectileCount, double projectile_radius, double enemy_radius, long nextEnemy) {
        super(projectileCount, projectile_radius, enemy_radius, nextEnemy);
    }

    public void trySimpleShot(
            long currentTime,
            Player player,
            int[] projStates,
            double[] projX, double[] projY,
            double[] projVX, double[] projVY
    ) {
        if (currentTime > nextShoot && enemy_Y < player.getY()) {
            int free = findFreeIndex(projStates);
            if (free < projStates.length) {
                projX[free] = enemy_X;
                projY[free] = enemy_Y;
                projVX[free] = Math.cos(enemy_angle) * 0.45;
                projVY[free] = Math.sin(enemy_angle) * 0.45 * (-1.0);
                projStates[free] = ACTIVE;
                nextShoot = (long) (currentTime + 200 + Math.random() * 500);
            }
        }
    }

}
