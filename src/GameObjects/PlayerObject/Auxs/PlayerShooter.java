package GameObjects.PlayerObject.Auxs;

import GameObjects.PlayerObject.Player;
import GameObjects.Projectile;

public class PlayerShooter {

    private final Player player;
    private final Projectile[] projectiles;
    private final int maxProjectiles = 10;


    public PlayerShooter(Player player) {
        this.player = player;
        this.projectiles = new Projectile[maxProjectiles];
        for (int i = 0; i < maxProjectiles; i++) projectiles[i] = new Projectile();
    }

    public void updateProjectiles(long delta) {
        for (Projectile projectile : this.projectiles) {
            projectile.update(delta);
        }
    }
}
