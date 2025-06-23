package game.entities;

import game.util.Vector2D;
import game.util.Status;

public abstract class ProjectileGeneric extends GameObject
{
    protected int damage;

    public ProjectileGeneric(Vector2D position, Vector2D velocity, double radius, int damage)
    {
        super(position, velocity, radius);
        this.damage = damage;
    }

    public int getDamage()
    {
        return damage;
    }

    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    public abstract void update(long delta);
    public abstract void render(long currentTime);
}
