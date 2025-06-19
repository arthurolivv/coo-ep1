package GameEngine.Objects;

import states.GameStates;

public class Projectiles {

    // Constantes para estados (assumindo que existem no código original)
    protected static final int INACTIVE = GameStates.INACTIVE.getValue();
    protected static final int ACTIVE = GameStates.ACTIVE.getValue();
    protected static final int EXPLODING = GameStates.EXPLODING.getValue();

    protected int [] projectile_states;
    protected double [] projectile_X;
    protected double [] projectile_Y;
    protected double [] projectile_VX;
    protected double [] projectile_VY;
    protected double projectile_radius; //apenas para inimigos

    public Projectiles(int projectileCount, double projectile_radius) {
        this.projectile_states = new int[projectileCount];
        this.projectile_X = new double[projectileCount];
        this.projectile_Y = new double[projectileCount];
        this.projectile_VX = new double[projectileCount];
        this.projectile_VY = new double[projectileCount];
        this.projectile_radius = projectile_radius;
    }

    public void initializeProjectiles() {
        for(int i = 0; i < projectile_states.length; i++) projectile_states[i] = INACTIVE;
    }

    public int[] getProjectile_states() {
        return projectile_states;
    }

    public double[] getProjectile_X() {
        return projectile_X;
    }

    public double[] getProjectile_Y() {
        return projectile_Y;
    }

    public double[] getProjectile_VX() {
        return projectile_VX;
    }

    public double[] getProjectile_VY() {
        return projectile_VY;
    }

    public double getProjectile_radius() {
        return projectile_radius;
    }

    public void setProjectile_X(double x, int index) {
        this.projectile_X[index] = x;
    }

    public void setProjectile_Y(double y, int index) {
        this.projectile_Y[index] = y;
    }

    public void setProjectile_VX(double vx, int index) {
        this.projectile_VX[index] = vx;
    }

    public void setProjectile_VY(double vy, int index) {
        this.projectile_VY[index] = vy;
    }

    public void setProjectile_State(int state, int index) {
        this.projectile_states[index] = state;
    }

    public void setProjectile_radius(double projectile_radius) {
        this.projectile_radius = projectile_radius;
    }
}
