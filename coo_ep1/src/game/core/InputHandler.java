package game.core;

import game.GameLib;
import game.entities.Player;
import game.util.Vector2D;

public class InputHandler {

    private final Player player;

    public InputHandler(Player player) {
        this.player = player;
    }

    //REVER ESSE MÉTODO, POIS TEM COMO ENCAPSULAR MAIS
    public void processInput(double delta) {
        // Criamos um vetor novo para acumular os deslocamentos desejados
        Vector2D inputVelocity = new Vector2D(0.0, 0.0);

        if (GameLib.iskeyPressed(GameLib.KEY_LEFT)) {
            inputVelocity.setX(inputVelocity.getX() - 0.4);
        }
        if (GameLib.iskeyPressed(GameLib.KEY_RIGHT)) {
            inputVelocity.setX(inputVelocity.getX() + 0.4);
        }
        if (GameLib.iskeyPressed(GameLib.KEY_UP)) {
            inputVelocity.setY(inputVelocity.getY() - 0.4);
        }
        if (GameLib.iskeyPressed(GameLib.KEY_DOWN)) {
            inputVelocity.setY(inputVelocity.getY() + 0.4);
        }

        // Multiplica a velocidade pela variação de tempo
        Vector2D deltaPosition = inputVelocity.scale(delta);

        // Atualiza a posição do player
        Vector2D newPosition = player.getPosition().add(deltaPosition);
        player.setPosition(newPosition);
    }

    public boolean isShooting() {
        return GameLib.iskeyPressed(GameLib.KEY_CONTROL);
    }

    public boolean isEscapePressed() {
        return GameLib.iskeyPressed(GameLib.KEY_ESCAPE);
    }
}
