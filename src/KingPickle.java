import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class KingPickle extends Enemy {

    private int spriteNum, maxHP;
    private int spriteCounter;
    public KingPickle(int worldX, int worldY, GamePanel gamePanel, Player player) {
        super(worldX, worldY , 12, 100, player, true);
        setScreenX((super.getWorldX() - player.getWorldX() + player.getScreenX()));
        setScreenY((super.getWorldY() - player.getWorldY() + player.getScreenY()));
        this.gamePanel = gamePanel;
        this.player = player;
        spriteNum = 0;
        maxHP = getHealth();
        spriteCounter = 0;
        getKingPickleImages();
    }

    public void getKingPickleImages() {
        try {
            super.setDown0(ImageIO.read(new File("Images/Enemies/Pickled_Egg_King/down0.png")));
            super.setDown1(ImageIO.read(new File("Images/Enemies/Pickled_Egg_King/down1.png")));
            super.setDown2(ImageIO.read(new File("Images/Enemies/Pickled_Egg_King/down2.png")));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void gotHit() {
        super.setHealth(super.getHealth() - 6);
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
            moveHitbox(17, 11, 10 * 5, 15 * 5, getScreenX(), getScreenY());

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
        g2.setColor(Color.RED);
        g2.fillRect(getScreenX(), getScreenY() - 15, 124, 10);
        g2.setColor(Color.GREEN);
        g2.fillRect(getScreenX(), getScreenY() - 15, (int)(124 * (double) getHealth() / maxHP), 10);
        g2.drawImage(image, getScreenX(), getScreenY(), gamePanel.getTileSize() * 3, gamePanel.getTileSize() * 3, null);
    }
}