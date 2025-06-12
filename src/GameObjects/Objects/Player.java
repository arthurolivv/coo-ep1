package GameObjects.Objects;

import GameObjects.GameObject;
import libs.GameLib;

import java.util.ArrayList;

// Classe Player implementando GameObject
public class Player implements GameObject {
    // Constantes para estados (assumindo que existem no código original)
    public static final int ACTIVE = 0;
    public static final int EXPLODING = 1;
    public static final int INACTIVE = 2;

    // Constantes de configuração
    private static final double DEFAULT_X = GameLib.WIDTH / 2.0;
    private static final double DEFAULT_Y = GameLib.HEIGHT * 0.90;
    private static final double DEFAULT_VELOCITY = 0.25;
    private static final double DEFAULT_RADIUS = 12.0;
    private static final long SHOT_COOLDOWN = 100; // tempo entre tiros em ms

    // Atributos privados (encapsulamento)
    private int state;
    private double x, y;
    private double velocityX, velocityY;
    private double radius;
    private double explosionStart;
    private double explosionEnd;
    private long nextShotTime;

    // Projéteis como parte do Player (Composição)
    private List<Projectile> projectiles;

    // Construtor
    public Player() {
        this.state = ACTIVE;
        this.x = DEFAULT_X;
        this.y = DEFAULT_Y;
        this.velocityX = DEFAULT_VELOCITY;
        this.velocityY = DEFAULT_VELOCITY;
        this.radius = DEFAULT_RADIUS;
        this.explosionStart = 0;
        this.explosionEnd = 0;
        this.nextShotTime = System.currentTimeMillis();
        this.projectiles = new ArrayList<>();
    }


    @Override
    public void update(long currentTime) {

    }

    @Override
    public void render() {

    }

    @Override
    public boolean isActive() {
        return false;
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
    public double getRadius() {
        return 0;
    }

    @Override
    public void setState(int state) {

    }

    @Override
    public int getState() {
        return 0;
    }
}