import java.util.ArrayList;

public class HitboxDetector {
    private ArrayList<PickledEgg> pickledEggs;
    private ArrayList<Drone> drones;
    private Bullet[] bullets;
    private Player player;

    public HitboxDetector(ArrayList<PickledEgg> pickledEggs, Bullet[] bullets, Player player, ArrayList<Drone> drones) {
        this.pickledEggs = pickledEggs;
        this.drones = drones;
        this.bullets = bullets;
        this.player = player;
    }

    public void update() {
        for (int i = 0; i < pickledEggs.size(); i ++) {
            if (pickledEggs.get(i).getHitbox().intersects(player.getHitbox())) {
                player.gotHit();
            }
        }
        for (int i = 0; i < bullets.length; i ++) {
            for (int idx = 0; idx < pickledEggs.size(); idx ++) {
                if (pickledEggs.get(idx).getHitbox().intersects(bullets[i].getHitbox())) {
                    pickledEggs.get(idx).gotHit();
                    bullets[i].gotHit();
                }
            }
            for (int idx2 = 0; idx2 < drones.size(); idx2 ++) {
                if (drones.get(idx2).getHitbox().intersects(bullets[i].getHitbox())) {
                    drones.get(idx2).gotHit();
                    bullets[i].gotHit();
                }
            }
        }

    }
}
