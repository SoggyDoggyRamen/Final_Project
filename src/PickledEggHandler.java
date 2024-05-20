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
            int randomNum = (int) (Math.random() * 2) + 1;
            int positiveNeg = 1;
            if (randomNum == 1) {
                positiveNeg = -1;
            }
            int randomWorldX = positiveNeg * ((int) (Math.random() * 501) + 500) + player.getWorldX();
            randomNum = (int) (Math.random() * 2) + 1;
            positiveNeg = 1;
            if (randomNum == 1) {
                positiveNeg = -1;
            }
            int randomWorldY = positiveNeg * ((int) (Math.random() * 501) + 500) + player.getWorldY();
            PickledEgg pickledEgg = new PickledEgg(player, gamepanel, randomWorldX, randomWorldY);
            pickledEggs.add(pickledEgg);
        }
    }

    public void update() {
        for (int i = 0; i < pickledEggs.size(); i ++) {
            if (pickledEggs.get(i).getAlive()) {
                pickledEggs.get(i).update();
            }
            if (!pickledEggs.get(i).getAlive()) {
                pickledEggs.remove(i);
            }
        }
        framesPassed ++;
        if (framesPassed == 120) {
            generatePickledEggs(20);
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

    public ArrayList<PickledEgg> getPickledEggs() {
        return pickledEggs;
    }
}
