import java.awt.*;
import java.util.ArrayList;

public class PickledEggHandler {
    private GamePanel gamepanel;
    private Player player;
    private TileManager tileManager;
    private Bullets bullets;
    private ArrayList<PickledEgg> pickledEggs;
    private int framesPassed;

    public PickledEggHandler(GamePanel gamePanel, Player player, TileManager tileManager, Bullets bullets) {
        this.gamepanel = gamePanel;
        this.player = player;
        this.tileManager = tileManager;
        this.pickledEggs = new ArrayList<PickledEgg>();
        this.bullets = bullets;
        framesPassed = 0;
        generatePickledEggs(10);
    }

    public void generatePickledEggs(int amt){
        for (int i = 0; i < amt; i ++) {
            int randomNum = (int) (Math.random() * 1) + 1;
            int positiveNeg = 1;
            if (randomNum == 1) {
                positiveNeg = -1;
            }
            int randomWorldX = positiveNeg * ((int) (Math.random() * 501) + 500) + player.getWorldX();
            randomNum = (int) (Math.random() * 1) + 1;
            positiveNeg = 1;
            if (randomNum == 1) {
                positiveNeg = -1;
            }
            int randomWorldY = positiveNeg * ((int) (Math.random() * 501) + 500) + player.getWorldY();
            PickledEgg pickledEgg = new PickledEgg(player, gamepanel, randomWorldX, randomWorldY, bullets);
            pickledEggs.add(pickledEgg);
        }
    }

    public void update() {
        for (PickledEgg pickledEgg: pickledEggs) {
            if (pickledEgg.getAlive()) {
                pickledEgg.update();
            }
            if (!pickledEgg.getAlive()) {
                pickledEggs.remove(pickledEgg);
            }
        }
        framesPassed ++;
        if (framesPassed == 300) {
            generatePickledEggs(10);
            framesPassed = 0;
        }
    }

    public void draw(Graphics2D g2) {
        for (PickledEgg pickledEgg: pickledEggs) {
            if(pickledEgg.getAlive()) {
                pickledEgg.draw(g2);
            }
        }
    }
}
