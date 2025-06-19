package GameEngine.Interfaces;

// Interface que define o contrato básico para todos os objetos do jogo
public interface GameObject {

    void collide(GameObject Object, long currentTime);

    boolean checkState(int state);

    void setX(double x);

    void setY(double y);

    double getX();

    double getY();

    double getRadius();

    void setState(int state);

    double getExplosionStart();

    double getExplosionEnd();

    void setExplosionStart(double explosionStart);

    void setExplosionEnd(double explosionEnd);


}
