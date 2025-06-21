package GameObjects.EnemyObject;

import GameObjects.EnemyObject.Enemys.Type1.Enemy1;
import GameObjects.EnemyObject.Enemys.Type2.Enemy2;
import GameObjects.GameObject;
import GameObjects.Projectile;
import enums.GameStates;
import libs.GameLib;

public class EnemyManager {

    public static final int INACTIVE = GameStates.INACTIVE.getValue();
    public static final int ACTIVE = GameStates.ACTIVE.getValue();
    public static final int EXPLODING = GameStates.EXPLODING.getValue();

    private Enemy1[] enemies1;
    private Enemy2[] enemies2;
    private Projectile[] projectiles;
    private long nextEnemy1;
    private long nextEnemy2;
    private int enemy2Count;
    private double enemy2SpawnX;

    public EnemyManager(long currentTime) {
        // Inicializa arrays
        enemies1 = new Enemy1[10];
        enemies2 = new Enemy2[10];
        projectiles = new Projectile[200];

        // Inicializa inimigos tipo 1 (todos inativos no início)
        for (int i = 0; i < enemies1.length; i++) {
            enemies1[i] = new Enemy1(INACTIVE, 0, 0, 9, 0, 0, 0);
            enemies1[i].setState(INACTIVE);
        }

        // Inicializa inimigos tipo 2
        enemy2SpawnX = GameLib.WIDTH * 0.20;
        for (int i = 0; i < enemies2.length; i++) {
            enemies2[i] = new Enemy2(INACTIVE, 0, 0, 12, 0, 0, 0, enemy2SpawnX, 0);
            enemies2[i].setState(INACTIVE);
        }

        // Inicializa projéteis
        for (int i = 0; i < projectiles.length; i++) {
            projectiles[i] = new Projectile();
        }

        // Configura tempos de spawn iniciais
        nextEnemy1 = currentTime + 2000;
        nextEnemy2 = currentTime + 7000;
        enemy2Count = 0;
    }
}
