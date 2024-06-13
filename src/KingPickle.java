import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class KingPickle extends Enemy {

    private int spriteNum;
    private int spriteCounter;
    public KingPickle(int worldX, int worldY, GamePanel gamePanel, Player player) {
        super(worldX, worldY , 10, 100, player, true);
        setScreenX((super.getWorldX() - player.getWorldX() + player.getScreenX()));
        setScreenY((super.getWorldY() - player.getWorldY() + player.getScreenY()));
        this.gamePanel = gamePanel;
        this.player = player;
        spriteNum = 0;
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
        super.setHealth(super.getHealth() - 3);
    }

    public void update() {
        if (player.getAlive()) {
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