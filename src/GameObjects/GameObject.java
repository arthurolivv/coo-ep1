package GameObjects;

// Interface que define o contrato básico para todos os objetos do jogo
public interface GameObject {
    /**
     * Atualiza o estado do objeto baseado no tempo atual
     */
    void update(long currentTime);

    /**
     * Renderiza o objeto na tela
     */
    void render();

    /**
     * Verifica se o objeto está ativo (não foi destruído)
     */
    boolean isActive();

    /**
     * Retorna a coordenada X do objeto
     */
    double getX();

    /**
     * Retorna a coordenada Y do objeto
     */
    double getY();

    /**
     * Retorna o raio do objeto (para detecção de colisão)
     */
    double getRadius();

    /**
     * Define o estado do objeto
     */
    void setState(int state);

    /**
     * Retorna o estado atual do objeto
     */
    int getState();
}
