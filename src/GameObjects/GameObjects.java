package GameObjects;

import states.GameStates;

public abstract class GameObjects {
    protected double x, y;           // Todas têm posição
    protected double radius;         // Todas têm tamanho para colisão
    protected GameStates state;      // Todas têm estado
    protected long currentTime;      // Todas precisam do tempo atual
}
