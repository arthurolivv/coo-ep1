package GameEngine.Objects.Enemys;

public class Enemy2 extends EnemyGeneric{

    private double spawnX;
    private int count;

    public Enemy2(int projectileCount, double projectile_radius, double enemy_radius, long nextEnemy, double spawnX, int count) {
        super(projectileCount, projectile_radius, enemy_radius, nextEnemy);
        this.spawnX = spawnX;
        this.count = count;
    }

    public void shootSpread(
            int[] projStates, double[] projX, double[] projY,
            double[] projVX, double[] projVY
    ) {
        double[] angles = {
                Math.PI / 2 + Math.PI / 8,
                Math.PI / 2,
                Math.PI / 2 - Math.PI / 8
        };

        int[] freeArray = findFreeIndex(projStates, angles.length);

        for (int k = 0; k < freeArray.length; k++) {
            int free = freeArray[k];
            if (free < projStates.length) {
                double a = angles[k] + Math.random() * Math.PI / 6 - Math.PI / 12;
                double vx = Math.cos(a);
                double vy = Math.sin(a);

                projX[free] = enemy_X;
                projY[free] = enemy_Y;
                projVX[free] = vx * 0.30;
                projVY[free] = vy * 0.30;
                projStates[free] = ACTIVE;
            }
        }
    }



}
