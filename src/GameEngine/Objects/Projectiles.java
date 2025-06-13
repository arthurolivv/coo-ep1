package GameEngine.Objects;

import states.GameStates;

public class Projectiles {

    // Constantes para estados (assumindo que existem no código original)
    public static final int INACTIVE = GameStates.INACTIVE.getValue();
    public static final int ACTIVE = GameStates.ACTIVE.getValue();
    public static final int EXPLODING = GameStates.EXPLODING.getValue();

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

    public int [] getProjectilesStates(){
        return projectile_states;
    }

    public void setProjectilesStates(int [] projectile_states){
        this.projectile_states = projectile_states;
    }

    public double [] getProjectilesX(){
        return projectile_X;
    }

    public double [] getProjectilesY(){
        return projectile_Y;
    }

    public double [] getProjectilesVX(){
        return projectile_VX;
    }

    public double [] getProjectilesVY(){
        return projectile_VY;
    }
}
