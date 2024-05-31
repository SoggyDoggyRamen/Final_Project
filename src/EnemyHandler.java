import java.awt.*;
import java.util.ArrayList;

public class EnemyHandler {
    private GamePanel gamepanel;
    private Player player;
    private TileManager tileManager;
    private BulletHandler bulletHandler;
    private ArrayList<PickledEgg> pickledEggs;
    private ArrayList<Drone> drones;
    private int framesPassed;

    public EnemyHandler(GamePanel gamePanel, Player player, TileManager tileManager, BulletHandler bulletHandler) {
        this.gamepanel = gamePanel;
        this.player = player;
        this.tileManager = tileManager;
        this.pickledEggs = new ArrayList<PickledEgg>();
        this.drones = new ArrayList<Drone>();
        this.bulletHandler = bulletHandler;
        framesPassed = 0;
        generateEnemies(10);
    }

    public void generateEnemies(int amt){
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
                Drone drone1 = new Drone(randomWorldX, randomWorldY - 30, gamepanel, player, pickledEggs, drones);
                Drone drone2 = new Drone(randomWorldX + 30, randomWorldY + 30, gamepanel, player, pickledEggs, drones);
                Drone drone3 = new Drone(randomWorldX - 30, randomWorldY + 30, gamepanel, player, pickledEggs, drones);
                drones.add(drone1);
                drones.add(drone2);
                drones.add(drone3);
            }
            else {
                PickledEgg pickledEgg = new PickledEgg(randomWorldX, randomWorldY, gamepanel, player, pickledEggs, drones);
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
        for (int i = 0; i < drones.size(); i ++) {
            if (drones.get(i).getAlive()) {
                drones.get(i).update();
            }
            if (!drones.get(i).getAlive()) {
                drones.remove(i);
            }
        }
        framesPassed ++;
        if (framesPassed == 120) {
            generateEnemies(10);
            framesPassed = 0;
        }
    }

    public void draw(Graphics2D g2) {
        for (PickledEgg pickledEgg: pickledEggs) {
            if(pickledEgg.getAlive()) {
                pickledEgg.draw(g2);
            }
        }
        for (Drone drone: drones) {
            if (drone.getAlive()) {
                drone.draw(g2);
            }
        }
    }

    public ArrayList<PickledEgg> getPickledEggs() {
        return pickledEggs;
    }

    public ArrayList<Drone> getDrones() {
        return drones;
    }
}
