public class Projectile extends GameObject{

    protected double vx;
    protected double vy;
    private long activationTime;


    public Projectile() {
        super(INACTIVE, 0, 0, 2.0);
    }

    @Override
    public void updatePosition(long delta) {
        if (state == ACTIVE) {
            x += vx * delta;
            y += vy * delta;

            // Desativar se sair da tela
            if (y < 0 || x < 0 || x > GameLib.WIDTH) {
                state = INACTIVE;
            }
        }
    }

    public void activate(double x, double y, double vx, double vy, long currentTime) {
        this.setX(x);          // Define posição X
        this.setY(y);          // Define posição Y
        this.setVx(vx);        // Define velocidade X
        this.setVy(vy);        // Define velocidade Y
        this.setState(ACTIVE); // Muda estado para ativo
        this.activationTime = currentTime; // Armazena quando foi ativado
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
