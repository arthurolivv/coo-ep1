import java.util.List;

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
            if (p.getState() == INACTIVE) {
                return p;
            }
        }
        return null; // No available projectiles
    }

    @Override
    public void updatePosition(long delta) {
        if (state == ACTIVE) {
            x += vx * delta;
            y += vy * delta;

            // Limitar movimento dentro da tela
            x = Math.max(radius, Math.min(x, GameLib.WIDTH - radius));
            y = Math.max(radius, Math.min(y, GameLib.HEIGHT - radius));
        }
    }

    public void shoot(long currentTime) {
        if (state == ACTIVE && currentTime > nextShot) {
            // Encontra um projétil inativo
            for (Projectile p : projectiles) {
                if (p.getState() == INACTIVE) {
                    p.setState(ACTIVE);
                    p.setX(this.x);
                    p.setY(this.y - 2 * radius); // Sai acima do player
                    p.setVy(-0.45); // Move para cima
                    nextShot = currentTime + 300; // Delay de 300ms entre tiros
                    break;
                }
            }
        }
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
