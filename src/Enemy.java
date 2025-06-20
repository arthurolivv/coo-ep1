public interface Enemy {
    void update(long currentTime);
    void render(); // desenha com GameLib ou outro motor
    int getState();
    void setState(int state);

    double getX();

    double getY();

    double getRadius();

    void setExplosion_start(double explosion_start);

    void setExplosion_end(double explosion_end);

    double getExplosion_end();

    void setX(double x);

    double getV();

    double getAngle();

    void setY(double y);

    double getRv();

    void setAngle(double angle);
}
