package GameEngine.Objects.Enemys;

import GameEngine.Interfaces.GameObject;
import GameEngine.Objects.Player;
import GameEngine.Objects.Projectiles;
import libs.GameLib;

public abstract class EnemyGeneric extends Projectiles implements GameObject {

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
        this.enemy_radius = enemy_radius;
        this.nextEnemy = nextEnemy;
    }


    @Override
    public void collide(GameObject Object, long currentTime) {
        if (this.getState() != ACTIVE || !(Object instanceof Player)) return;

        Player player = (Player) Object;

        int[] projStates = player.getProjectile_states();
        double[] projX = player.getProjectile_X();
        double[] projY = player.getProjectile_Y();

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
    public boolean checkState(int state) {
        if(this.enemy_state == state) return true;
        return false;
    }

    @Override
    public void setX(double x) {
        enemy_X = x;
    }

    @Override
    public void setY(double y) {
        enemy_Y = y;

    }

    @Override
    public double getX() {

        return enemy_X;
    }

    @Override
    public double getY() {
        return enemy_Y;
    }

    public double getV(){
        return enemy_V;
    }

    public void setV(double v){
        this.enemy_V = v;
    }

    public double getRV(){
        return enemy_RV;
    }

    public void setRV(double rv){
        this.enemy_RV = rv;
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
        return enemy_state;
    }

    public void setAngle(double angle) {
        this.enemy_angle = angle;
    }

    public double getAngle() {
        return this.enemy_angle;
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
        if (enemy_state == EXPLODING && currentTime > enemy_explosion_end) {
            enemy_state = INACTIVE;
        }
    }

    public void updateMovement(double delta) {
        enemy_X += enemy_V * Math.cos(enemy_angle) * delta;
        enemy_Y += enemy_V * Math.sin(enemy_angle) * delta * (-1.0);
        enemy_angle += enemy_RV * delta;
    }

    public abstract boolean shouldSpawn(long currentTime);

    protected int findFreeIndex(int[] states) {
        for (int i = 0; i < states.length; i++) {
            if (states[i] == INACTIVE) return i;
        }
        return states.length;
    }

    public long getNextEnemy() {
        return nextEnemy;
    }

    public void setNextEnemy(long nextEnemy) {
        this.nextEnemy = nextEnemy;
    }

}

