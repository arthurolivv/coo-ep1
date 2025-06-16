package GameEngine.Objects.Enemys;

import GameEngine.Interfaces.GameObject;
import GameEngine.Objects.Player;
import GameEngine.Objects.Projectiles;
import libs.GameLib;

public class EnemyGeneric extends Projectiles implements GameObject {

    protected int enemy_state;
    protected double enemy_X;
    protected double enemy_Y;
    protected double enemy_V;
    protected double enemy_angle;
    protected double enemy_RV;
    protected double enemy_explosion_start;
    protected double enemy_explosion_end;
    protected double enemy_radius;
    protected long nextEnemy;

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
    public void collide(GameObject Object, long currentTime) {
        if (this.getState() != ACTIVE || !(Object instanceof Player)) return;

        Player player = (Player) Object;

        int[] projStates = player.getProjectilesStates();
        double[] projX = player.getProjectilesX();
        double[] projY = player.getProjectilesY();

        for (int i = 0; i < projStates.length; i++) {
            if (projStates[i] == ACTIVE) {
                double dx = this.getX() - projX[i];
                double dy = this.getY() - projY[i];
                double dist = Math.sqrt(dx * dx + dy * dy);

                if (dist < this.getRadius()) {
                    this.setState(EXPLODING);
                    this.setExplosionStart(currentTime);
                    this.setExplosionEnd(currentTime + 500);

                    projStates[i] = INACTIVE;
                    break;
                }
            }
        }
    }

    @Override
    public boolean isActive() {
        if (enemy_state == ACTIVE) {
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

    public void updateProjectiles(double delta) {
        for (int i = 0; i < projectile_states.length; i++) {
            if (projectile_states[i] == ACTIVE) {

                // Se saiu da tela (parte inferior), desativa
                if (projectile_Y[i] > GameLib.HEIGHT) {
                    projectile_states[i] = INACTIVE;
                } else {
                    // Atualiza posição
                    projectile_X[i] += projectile_VX[i] * delta;
                    projectile_Y[i] += projectile_VY[i] * delta;
                }
            }
        }
    }


    public void updateExplosion(long currentTime) {
        if (this.getState() == EXPLODING) {
            if (currentTime > this.getExplosionEnd()) {
                this.setState(INACTIVE);
            }
        }
    }

    public boolean updateMovement(double delta, double screenLimitY, double screenLimitX) {
        if (enemy_state != ACTIVE) return false;

        // Saiu da tela? Desativa
        if ((screenLimitY > 0 && enemy_Y > screenLimitY) ||
                (screenLimitX > 0 && (enemy_X < -10 || enemy_X > screenLimitX + 10))) {
            enemy_state = INACTIVE;
            return false;
        }

        // Atualiza posição e rotação
        enemy_X += enemy_V * Math.cos(enemy_angle) * delta;
        enemy_Y += enemy_V * Math.sin(enemy_angle) * delta * (-1.0);
        enemy_angle += enemy_RV * delta;

        return true;
    }

//    public void updateActive(double delta, long currentTime, double playerY, int[] projectileStates, double[] projectileX, double[] projectileY, double[] projectileVX, double[] projectileVY) {
//
//        if (enemy_state == ACTIVE) {
//
//            // Se saiu da tela, desativa
//            if (enemy_Y > GameLib.HEIGHT + 10) {
//                enemy_state = INACTIVE;
//            } else {
//                // Atualiza posição e rotação
//                enemy_X += enemy_V * Math.cos(enemy_angle) * delta;
//                enemy_Y += enemy_V * Math.sin(enemy_angle) * delta * (-1.0);
//                enemy_angle += enemy_RV * delta;
//
//                // Verifica se deve atirar
//                if (currentTime > nextEnemy && enemy_Y < playerY) {
//
//                    int free = findFreeIndex(projectileStates);
//
//                    if (free < projectileStates.length) {
//                        projectileX[free] = enemy_X;
//                        projectileY[free] = enemy_Y;
//                        projectileVX[free] = Math.cos(enemy_angle) * 0.45;
//                        projectileVY[free] = Math.sin(enemy_angle) * 0.45 * (-1.0);
//                        projectileStates[free] = ACTIVE;
//
//                        nextEnemy = currentTime + 200 + (long)(Math.random() * 500);
//                    }
//                }
//            }
//        }
//
//        // Atualiza estado de explosão
//        if (enemy_state == EXPLODING) {
//            if (currentTime > enemy_explosion_end) {
//                enemy_state = INACTIVE;
//            }
//        }
//    }

    protected int findFreeIndex(int[] states) {
        for (int i = 0; i < states.length; i++) {
            if (states[i] == INACTIVE) return i;
        }
        return states.length;
    }

}

