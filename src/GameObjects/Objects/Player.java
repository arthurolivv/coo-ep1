package GameObjects.Objects;

import GameObjects.GameObjects;
import states.GameStates;

public class Player extends GameObjects {

    public GameStates state;
    public double x;
    public double y;
    public double vx;
    public double vy;
    public double radius;
    public double explosionStart;
    public double explosionEnd;
    public long nextShot;

    // Construtor que já inicializa com valores padrão
    public Player(long currentTime) {
        this.state = GameStates.INACTIVE;  // ou ACTIVE, depende do seu uso inicial
        this.x = GameLib.WIDTH / 2;
        this.y = GameLib.HEIGHT * 0.90;
        this.vx = 0.25;
        this.vy = 0.25;
        this.radius = 12.0;
        this.explosionStart = 0;
        this.explosionEnd = 0;
        this.nextShot = currentTime;
    }
}

