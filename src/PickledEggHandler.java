import java.awt.*;
import java.util.ArrayList;

public class PickledEggHandler {
    private GamePanel gamepanel;
    private Player player;
    private TileManager tileManager;
    private BulletHandler bulletHandler;
    private ArrayList<PickledEgg> pickledEggs;
    private A
    private int framesPassed;

    public PickledEggHandler(GamePanel gamePanel, Player player, TileManager tileManager, BulletHandler bulletHandler) {
        this.gamepanel = gamePanel;
        this.player = player;
        this.tileManager = tileManager;
        this.pickledEggs = new ArrayList<PickledEgg>();
        this.bulletHandler = bulletHandler;
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
            randomNum = (int) (Math.random() * 20) + 1;
            if (randomNum <= 1) {
                Drone drone1 = new Drone(randomWorldX, randomWorldY - 30, gamepanel, player);
                Drone drone2 = new Drone(randomWorldX + 30, randomWorldY + 30, gamepanel, player);
                Drone drone3 = new Drone(randomWorldX - 30, randomWorldY + 30, gamepanel, player);
                pickledEggs.add(drone1);
                pickledEggs.add(drone2);
                pickledEggs.add(drone3);
            }
            else {
                PickledEgg pickledEgg = new PickledEgg(randomWorldX, randomWorldY, gamepanel, player);
                pickledEggs.add(pickledEgg);
            }
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

    public ArrayList<PickledEgg> getPickledEggs() {
        return pickledEggs;
    }
}
