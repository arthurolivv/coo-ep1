package GameEngine.Objects.Managers;

import GameEngine.Objects.Enemys.EnemyGeneric;
import java.util.List;

public class EnemyManager {
    private List<EnemyGeneric> enemies;

    public EnemyManager(List<EnemyGeneric> enemies) {
        this.enemies = enemies;
    }

    public void updateAll(double delta, long currentTime) {
        for (EnemyGeneric e : enemies) {
            e.updateExplosion(currentTime);
            if (e.checkState(1)) { // ACTIVE
                e.updateMovement(delta);
                e.updateProjectiles(delta);
            }
        }
    }

    public List<EnemyGeneric> getEnemies() {
        return enemies;
    }
}
