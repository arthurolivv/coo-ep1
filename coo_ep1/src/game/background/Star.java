package game.background;

import game.GameLib;

public class Star {
    public double x;
    public double y;
    public double speed;

    public Star(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void update(double delta) {
        y += speed * delta;
        if (y > 720) y = 0; // Reinicia no topo da tela
    }

    public void render() {
        GameLib.drawLine(x, y, x, y + 1);
    }
}
