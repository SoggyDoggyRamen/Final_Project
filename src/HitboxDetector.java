import java.util.ArrayList;

public class HitboxDetector {
    private ArrayList<PickledEgg> pickledEggs;
    private Bullet[] bullets;
    private Player player;

    public HitboxDetector(ArrayList<PickledEgg> pickledEggs, Bullet[] bullets, Player player) {
        this.pickledEggs = pickledEggs;
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
        }
    }
}
