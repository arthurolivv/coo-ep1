package GameObjects.PlayerObject;

import GameObjects.GameObject;
import GameObjects.Projectile;
import libs.GameLib;

import java.awt.*;

public class Player extends GameObject {

    private double vx;
    private double vy;
    private double explosion_start;
    private double explosion_end;
    private long nextShot = System.currentTimeMillis();

    private Projectile[] projectiles;
    private final int MAX_PROJECTILES = 10;

    public Player(int state, double x, double y, double radius, double vx, double vy, long nextShot) {
        super(state, x, y, radius);
        this.vx = vx;
        this.vy = vy;
        this.explosion_start = 0;
        this.explosion_end = 0;
        this.nextShot = nextShot;
        this.projectiles = new Projectile[MAX_PROJECTILES];
        initializeProjectiles();
    }

    private void initializeProjectiles() {
        for(int i = 0; i < MAX_PROJECTILES; i++){
            projectiles[i] = new Projectile();
        }
    }

    public Projectile findFreeProjectile() {
        for (Projectile p : projectiles) {
            if (p.getState() == GameObject.INACTIVE) {
                return p;
            }
        }
        return null; // No available projectiles
    }

    @Override
    public void updatePosition(long delta) {
        if (state == GameObject.ACTIVE) {
            x += vx * delta;
            y += vy * delta;

            // Limitar movimento dentro da tela
            x = Math.max(radius, Math.min(x, GameLib.WIDTH - radius));
            y = Math.max(radius, Math.min(y, GameLib.HEIGHT - radius));
        }
    }

    public void shoot(long currentTime) {
        if (state == GameObject.ACTIVE && currentTime > nextShot) {
            // Encontra um projétil inativo
            for (Projectile p : projectiles) {
                if (p.getState() == GameObject.INACTIVE) {
                    p.setState(GameObject.ACTIVE);
                    p.setX(this.x);
                    p.setY(this.y - 2 * radius); // Sai acima do player
                    p.setVy(-0.45); // Move para cima
                    nextShot = currentTime + 300; // Delay de 300ms entre tiros
                    break;
                }
            }
        }
    }

    public void updateProjectiles(long delta) {
        for (Projectile projectile : this.projectiles) {
            projectile.update(delta);
        }
    }

    public void updateExplosion(long currentTime) {
        if (this.getState() == GameObject.EXPLODING && currentTime > this.explosion_end) {
            this.setState(GameObject.ACTIVE);
            this.resetAfterExplosion(); // Opcional: resetar posição, vidas, etc.
        }
    }

    private void resetAfterExplosion() {
        this.setX(GameLib.WIDTH / 2);
        this.setY(GameLib.HEIGHT * 0.90);
    }

    public void explode(long currentTime) {
        this.setState(GameObject.EXPLODING);
        this.explosion_start = currentTime;
        this.explosion_end = currentTime + 2000; // 2 segundos de explosão
    }

    public void handleInput(long delta, long currentTime, boolean upPressed, boolean downPressed,
                            boolean leftPressed, boolean rightPressed, boolean shootPressed) {
        if (this.getState() == GameObject.ACTIVE) {
            handleMovement(delta, upPressed, downPressed, leftPressed, rightPressed);

            if (shootPressed) {
                tryShoot(currentTime);
            }
        }
    }

    private void handleMovement(long delta, boolean up, boolean down, boolean left, boolean right) {
        if (up) setY(getY() - delta * getVy());
        if (down) setY(getY() + delta * getVy());
        if (left) setX(getX() - delta * getVx());
        if (right) setX(getX() + delta * getVx());

        // Corrigindo o bug original (usava getVy() no movimento para direita)
        // Mantém dentro dos limites da tela
        clampPosition();
    }

    private void tryShoot(long currentTime) {
        if (currentTime > getNextShot()) {
            Projectile projectile = findFreeProjectile();
            if (projectile != null) {
                activateProjectile(projectile, currentTime);
                setNextShot(currentTime + 100);
            }
        }
    }

    private void activateProjectile(Projectile projectile, long currentTime) {
        projectile.activate(
                getX(),
                getY() - 2 * getRadius(),
                0.0,
                -1.0,
                currentTime
        );
    }

    private static final double MIN_Y = 25.0; // Limite superior da tela

    public void clampPosition() {
        // Limites horizontais (X)
        double minX = getRadius();
        double maxX = GameLib.WIDTH - getRadius();
        setX(Math.max(minX, Math.min(getX(), maxX)));

        // Limites verticais (Y)
        double minY = MIN_Y;
        double maxY = GameLib.HEIGHT - getRadius();
        setY(Math.max(minY, Math.min(getY(), maxY)));
    }

    public void clampPosition(double minX, double maxX, double minY, double maxY) {
        setX(Math.max(minX, Math.min(getX(), maxX)));
        setY(Math.max(minY, Math.min(getY(), maxY)));
    }

    public void render(long currentTime) {
        if (this.getState() == EXPLODING) {
            renderExplosion(currentTime);
        } else if (this.getState() == ACTIVE) {
            renderNormal();
        }
        // Podemos adicionar outros estados (ex: INVENCIBLE) aqui
    }

    private void renderExplosion(long currentTime) {
        double alpha = (currentTime - this.getExplosion_start()) /
                (this.getExplosion_end() - this.getExplosion_start());
        GameLib.drawExplosion(this.getX(), this.getY(), alpha);
    }

    private void renderNormal() {
        GameLib.setColor(Color.BLUE);
        GameLib.drawPlayer(this.getX(), this.getY(), this.getRadius());
    }

    public Projectile[] getProjectiles() { return projectiles; }
    public double getExplosion_start() {
        return explosion_start;
    }

    public void setExplosion_start(double explosion_start) {
        this.explosion_start = explosion_start;
    }
    public double getExplosion_end() {
        return explosion_end;
    }
    public void setExplosion_end(double explosion_end) {
        this.explosion_end = explosion_end;
    }
    public long getNextShot() {
        return nextShot;
    }
    public void setNextShot(long nextShot) {
        this.nextShot = nextShot;
    }
    public double getVx() {
        return vx;
    }
    public void setVx(double vx) {
        this.vx = vx;
    }
    public double getVy() {
        return vy;
    }
    public void setVy(double vy) {
        this.vy = vy;
    }
}
