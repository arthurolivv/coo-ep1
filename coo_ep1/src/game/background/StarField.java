package game.background;

import game.GameLib;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class StarField {
    private final ArrayList<Star> stars;

    public StarField(int numStars) {
        stars = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < numStars; i++) {
            double x = rand.nextDouble() * GameLib.WIDTH;
            double y = rand.nextDouble() * GameLib.HEIGHT;
            double speed = 0.1 + rand.nextDouble() * 0.3;
            stars.add(new Star(x, y, speed));
        }
    }

    public void update(double delta) {
        for (Star s : stars) s.update(delta);
    }

    public void render() {
        GameLib.setColor(Color.WHITE);
        for (int i = 0; i < stars.size(); i++) {
            stars.get(i).render();
        }
    }
}
