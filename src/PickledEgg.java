import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PickledEgg extends Enemy{
    GamePanel gamePanel;
    Player player;
    private int spriteCounter, spriteNum;
    private ArrayList<PickledEgg> pickledEggs;
    private static int id;
    private int currID;
    private ArrayList<Drone> drones;

    public PickledEgg(int worldX, int worldY, GamePanel gamePanel, Player player, ArrayList<PickledEgg> pickledEggs, ArrayList<Drone> drones) {
        super(worldX, worldY , 10, 100, player, true);
        setScreenX((super.getWorldX() - player.getWorldX() + player.getScreenX()));
        setScreenY((super.getWorldY() - player.getWorldY() + player.getScreenY()));
        this.gamePanel = gamePanel;
        this.player = player;
        this.pickledEggs = pickledEggs;
        this.drones = drones;
        currID = id;
        id ++;
        spriteNum = 0;
        spriteCounter = 0;
        getPickledEggImage();
    }

    public void getPickledEggImage() {
        try {
            super.setDown0(ImageIO.read(new File("Images/Enemies/Pickled_Egg/down0.png")));
            super.setDown1(ImageIO.read(new File("Images/Enemies/Pickled_Egg/down1.png")));
            super.setDown2(ImageIO.read(new File("Images/Enemies/Pickled_Egg/down2.png")));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void gotHit() {
        super.setHealth(super.getHealth() - 100);
    }

    public void update() {
        if (player.getAlive()) {
            //moving to player
            int[] velocity = getEnemyVelocity();
            super.setWorldX(super.getWorldX() + velocity[0]);
            super.setWorldY(super.getWorldY() + velocity[1]);
            setScreenX((super.getWorldX() - player.getWorldX() + player.getScreenX()));
            setScreenY((super.getWorldY() - player.getWorldY() + player.getScreenY()));
            //move the hitbox
            moveHitbox(10, 7, 10, 15, getScreenX(), getScreenY());

            //check if its dead
            if (super.getHealth() < 0 || super.getHealth() == 0) {
                setAlive(false);
            }

            // animations
            spriteCounter++;
            if (spriteCounter == 10) {
                if (spriteNum == 0) {
                    spriteNum = 1;
                } else if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        Image image = null;

        //Animations for pickle egg
        if (spriteNum == 1) {
            image = super.getDown1();
        }
        else if (spriteNum == 2) {
            image = super.getDown2();
        }
        else {
            image = super.getDown0();
        }
        g2.drawImage(image, getScreenX(), getScreenY(), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }
}
