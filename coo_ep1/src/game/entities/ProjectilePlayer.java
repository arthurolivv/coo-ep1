package game.entities;

import game.GameLib;
import game.util.Vector2D;
import game.util.Status;

import java.awt.Color;

public class ProjectilePlayer extends ProjectileGeneric
{
    public ProjectilePlayer(Vector2D position, Vector2D velocity, double radius, int damage)
    {
        super(position, velocity, radius, damage);
    }

    @Override
    public void update(long delta)
    {
        Vector2D newPosition = getPosition().add(getVelocity().scale(delta));
        setPosition(newPosition);
    }

    @Override
    public void render(long currentTime)
    {
        if (getStatus() == Status.ACTIVE)
        {
            GameLib.setColor(Color.CYAN);
            GameLib.drawCircle(getPosition().getX(), getPosition().getY(), getRadius());
        }
    }
}
