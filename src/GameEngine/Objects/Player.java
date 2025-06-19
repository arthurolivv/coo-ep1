package GameEngine.Objects;

import GameEngine.Interfaces.GameObject;
import GameEngine.Objects.Enemys.EnemyGeneric;
import libs.GameLib;

// Classe Player implementando GameObject
public class Player extends Projectiles implements GameObject {

    // Atributos privados (encapsulamento)
    private int state;
    private double x;
    private double y;
    private final double velocityX, velocityY;
    private final double radius;
    private double explosionStart;
    private double explosionEnd;
    private long nextShot;

    // Construtor
    public Player(int projectileCount, double projectileRadius, int state,
                  double x, double y, double velocityX, double velocityY,
                  double radius, double explosionStart, double explosionEnd, long nextShot) {
//        this.state = ACTIVE;
//        this.x = DEFAULT_X;
//        this.y = DEFAULT_Y;
//        this.velocityX = DEFAULT_VELOCITY;
//        this.velocityY = DEFAULT_VELOCITY;
//        this.radius = DEFAULT_RADIUS;
//        this.explosionStart = 0;
//        this.explosionEnd = 0;
//        this.nextShotTime = System.currentTimeMillis();

        super(projectileCount, projectileRadius);
        this.state = state;
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.radius = radius;
        this.explosionStart = explosionStart;
        this.explosionEnd = explosionEnd;
        this.nextShot =  nextShot;

    }


    @Override
    public void collide(GameObject Object, long currentTime) {
        //colisao com projeteis inimigos
        if (Object instanceof EnemyGeneric enemy) {
            int[] states = enemy.getProjectile_states();
            double[] projX = enemy.getProjectile_X();
            double[] projY = enemy.getProjectile_Y();
            double radius = enemy.getProjectileRadius();

            for (int i = 0; i < states.length; i++) {
                if (states[i] == ACTIVE) {
                    double dx = projX[i] - this.getX();
                    double dy = projY[i] - this.getY();
                    double dist = Math.sqrt(dx * dx + dy * dy);

                    if (dist < (this.getRadius() + radius) * 0.8) {
                        this.setState(EXPLODING);
                        this.setExplosionStart(currentTime);
                        this.setExplosionEnd(currentTime + 2000);
                        return;
                    }
                }
            }
        }

        // Colisão direta com inimigo
        if (Object instanceof EnemyGeneric enemy) {
            double dx = enemy.getX() - this.getX();
            double dy = enemy.getY() - this.getY();
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist < (this.getRadius() + enemy.getRadius()) * 0.8) {
                this.setState(EXPLODING);
                this.setExplosionStart(currentTime);
                this.setExplosionEnd(currentTime + 2000);
            }
        }
    }

    @Override
    public boolean checkState(int state) {
        if(this.state == state) return true;
        return false;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    public double getVX() {
        return velocityX;
    }

    public double getVY() {
        return velocityY;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public double getExplosionStart() {
        return explosionStart;
    }

    @Override
    public double getExplosionEnd() {
        return explosionEnd;
    }

    @Override
    public void setExplosionStart(double explosionStart) {
        this.explosionStart = explosionStart;
    }

    @Override
    public void setExplosionEnd(double explosionEnd) {
        this.explosionEnd = explosionEnd;
    }

    public double getNextShot() {

        return nextShot;
    }

    public void setNextShot(long nextShot) {

        this.nextShot = nextShot;
    }

    public void updateStateProjectile(long delta) {
        for(int i = 0; i < getProjectile_states().length; i++) {
            if(getProjectile_states()[i] == ACTIVE) {
                // Verifica se o projétil saiu da tela
                if(getProjectile_Y()[i] < 0) {
                    getProjectile_states()[i] = INACTIVE;
                } else {
                    getProjectile_X()[i] += getProjectile_VX()[i] * delta;
                    getProjectile_Y()[i] += getProjectile_VY()[i] * delta;
                }
            }
        }
    }


}