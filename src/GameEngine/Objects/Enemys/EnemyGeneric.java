package GameEngine.Objects.Enemys;

import GameEngine.Interfaces.GameObject;
import GameEngine.Objects.Projectiles;

public class EnemyGeneric extends Projectiles implements GameObject {

    private int enemy_state;
    private double enemy_X;
    private double enemy_Y;
    private double enemy_V;
    private double enemy_angle;
    private double enemy_RV;
    private double enemy_explosion_start;
    private double enemy_explosion_end;
    private double enemy_radius;
    private long nextEnemy;

    public EnemyGeneric(int projectileCount, double projectile_radius, double enemy_radius,
                        long nextEnemy) {
        super(projectileCount, projectile_radius);
//        this.enemy_state = enemy_state;
//        this.enemy_X = enemy_X;
//        this.enemy_Y = enemy_Y;
//        this.enemy_V = enemy_V;
//        this.enemy_angle = enemy_angle;
//        this.enemy_RV = enemy_RV;
//        this.enemy_explosion_start = enemy_explosion_start;
//        this.enemy_explosion_end = enemy_explosion_end;
//        this.enemy_nextShoot = enemy_nextShoot;
        this.enemy_radius = enemy_radius;
        this.nextEnemy = nextEnemy;
    }


    @Override
    public void collide(GameObject object, long currentTime) {
        if (!(object instanceof EnemyGeneric) || this.getState() != ACTIVE) return;

        EnemyGeneric enemy = (EnemyGeneric) object;

        int[] projStates = enemy.getProjectilesStates();
        double[] projX = enemy.getProjectilesX();
        double[] projY = enemy.getProjectilesY();
        double projRadius = enemy.getProjectileRadius(); // getter

        for (int i = 0; i < projStates.length; i++) {
            if (projStates[i] == ACTIVE) {
                double dx = projX[i] - this.getX();
                double dy = projY[i] - this.getY();
                double dist = Math.sqrt(dx * dx + dy * dy);

                if (dist < (this.getRadius() + projRadius) * 0.8) {
                    this.setState(EXPLODING);
                    this.setExplosionStart(currentTime);
                    this.setExplosionEnd(currentTime + 2000);

                    projStates[i] = INACTIVE;
                    break;
                }
            }
        }
    }


    @Override
    public boolean isActive() {
        if(enemy_state == ACTIVE){
            return true;
        }
        return false;
    }

    @Override
    public void setX(double x) {

    }

    @Override
    public void setY(double y) {

    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public double getVX() {
        return 0;
    }

    @Override
    public double getVY() {
        return 0;
    }

    @Override
    public double getRadius() {
        return 0;
    }

    @Override
    public void setState(int state) {
        this.enemy_state = state;

    }

    @Override
    public int getState() {
        return 0;
    }

    @Override
    public double getExplosionStart() {
        return 0;
    }

    @Override
    public double getExplosionEnd() {
        return 0;
    }

    @Override
    public void setExplosionStart(double explosionStart) {

    }

    @Override
    public void setExplosionEnd(double explosionEnd) {

    }

    @Override
    public double getNextShot() {
        return 0;
    }

    @Override
    public void setNextShot(long nextShot) {

    }

    public double getProjectileRadius() {
        return this.projectile_radius;
    }
}
