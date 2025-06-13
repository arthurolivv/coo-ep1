package GameEngine.Objects;

// Interface que define o contrato básico para todos os objetos do jogo
public interface GameObject {

    void update(long currentTime);

    void render();

    boolean isActive();

    void setX(double x);

    void setY(double y);

    double getX();

    double getY();

    double getVX();

    double getVY();

    double getRadius();

    void setState(int state);

    int getState();

    double getExplosionStart();

    double getExplosionEnd();

    void setExplosionStart(double explosionStart);

    void setExplosionEnd(double explosionEnd);

    double getNextShot();

    void setNextShot(long nextShot);


}
