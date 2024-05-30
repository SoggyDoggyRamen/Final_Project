import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PickledEgg extends Enemy{
    GamePanel gamePanel;
    Player player;
    private int spriteCounter, spriteNum;

    public PickledEgg(int worldX, int worldY, GamePanel gamePanel, Player player) {
        super(worldX, worldY , 7, 100, player, true);
        createHitbox(6, 1, 19, 29);
        this.gamePanel = gamePanel;
        this.player = player;
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
        //moving to player
        int[] velocity = getEnemyVelocity();
        super.setWorldX(super.getWorldX() + velocity[0]);
        super.setWorldY(super.getWorldY() + velocity[1]);
        setScreenX((super.getWorldX() - player.getWorldX() + player.getScreenX()));
        setScreenY((super.getWorldY() - player.getWorldY() + player.getScreenY()));

        //move the hitbox
        createHitbox(6, 1, 19, 29);

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

    public void draw(Graphics2D g2) {
        Image image = null;
        //red line indicator
        g2.setStroke(new BasicStroke(3));
        Color c = new Color(1f,0f,0f,.3f );
//        if (ranged) {
//            System.out.println(framesPassed);
//            if (framesPassed >= 30 && framesPassed <= 150 && redLine) {
//                if (framesPassed >= 30 && framesPassed <= 120) {
//                    drawRedLine(g2);
//                    convertToWorldCoordinates();
//                }
//                if (framesPassed > 120) {
//                    drawStillLine(g2);
//                }
//                if (framesPassed == 150) {
//                    framesPassed = 0;
//                    redLine = false;
//                    laser = true;
//                }
//            }
//            if (laser) {
//                drawLaser(g2);
//                if (framesPassed == 45) {
//                    laser = false;
//                    redLine = true;
//                    framesPassed = 0;
//                }
//            }
//            framesPassed ++;
//        }

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
