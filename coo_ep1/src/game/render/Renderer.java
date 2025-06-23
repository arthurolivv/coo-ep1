package game.render;

import game.background.StarField;
import game.entities.*;

import java.util.List;

public class Renderer {

    private final Player player;
    private final List<EnemyGeneric> enemies;
    private final List<ProjectileGeneric> projectiles; // Lista genérica
    private final StarField starField;

    public Renderer(Player player, List<EnemyGeneric> enemies, List<ProjectileGeneric> projectiles, StarField starField) {
        this.player = player;
        this.enemies = enemies;
        this.projectiles = projectiles;
        this.starField = starField;
    }

    public void renderAll(long currentTime) {
        // Fundo animado
        starField.render();

        // Inimigos
        for (EnemyGeneric enemy : enemies) {
            enemy.render(currentTime);
        }

        // Todos os projéteis (tanto do player quanto dos inimigos)
        for (ProjectileGeneric projectile : projectiles) {
            projectile.render(currentTime);  // Polimorfismo em ação
        }

        // Jogador
        player.render(currentTime);

        // (Opcional) HUD
    }
}
