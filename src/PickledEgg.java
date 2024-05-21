import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PickledEgg extends Entity{
    GamePanel gamePanel;
    Player player;
    private int zeroCounter, spriteCounter, spriteNum, velX, velY;
    private boolean alive;
    Image redImage;

    public PickledEgg(Player player, GamePanel gamepanel, int worldX, int worldY) {
        super(worldX, worldY , 7, "down", 0, 0, 100);
        createHitbox(6, 1, 19, 29);
        this.gamePanel = gamepanel;
        this.player = player;
        zeroCounter = 0;
        spriteNum = 0;
        spriteCounter = 0;
        alive = true;
        getPickledEggImage();
    }

    public void getPickledEggImage() {
        try {
            super.setDown0(ImageIO.read(new File("Images/Pickled_Egg/down0.png")));
            super.setDown1(ImageIO.read(new File("Images/Pickled_Egg/down1.png")));
            super.setDown2(ImageIO.read(new File("Images/Pickled_Egg/down2.png")));
            redImage = ImageIO.read(new File("Images/Projectiles/redLine.png"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getPickledVelocity() {
        double dx = player.getPlayerX() - getScreenX();
        double dy = player.getPlayerY() - getScreenY();

        double length = Math.sqrt(dx*dx + dy*dy);

        dx /= length;
        dy /= length;

        velX = (int) (dx * super.getSpeed());
        velY = (int) (dy * super.getSpeed());
    }

    public void gotHit() {
        super.setHealth(super.getHealth() - 100);
    }

    public void update() {
        //moving to player
        getPickledVelocity();
        super.setWorldX(super.getWorldX() + velX);
        super.setWorldY(super.getWorldY() + velY);
        setScreenX((super.getWorldX() - player.getWorldX() + player.getPlayerX()));
        setScreenY((super.getWorldY() - player.getWorldY() + player.getPlayerY()));

        //move the hitbox
        createHitbox(6, 1, 19, 29);

        //check if its dead
        if (super.getHealth() < 0 || super.getHealth() == 0) {
            alive = false;
        }

        // animations
        spriteCounter++;
        if (spriteCounter == 10) {
            if (super.getDirection().equals("right") || super.getDirection().equals("left")) {
                if (spriteNum == 0) {
                    if (zeroCounter == 1) {
                        zeroCounter = 0;
                        spriteNum = 2;
                    } else {
                        spriteNum = 1;
                    }
                } else if (spriteNum == 1) {
                    if (zeroCounter == 0) {
                        zeroCounter++;
                        spriteNum = 0;
                    }
                } else if (spriteNum == 2) {
                    spriteNum = 0;
                }
            } else {
                if (spriteNum == 0) {
                    spriteNum = 1;
                } else if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        //red line indicator
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.RED);
        g2.drawLine(getScreenX() + 32, getScreenY() + 32, player.getPlayerX() + 32, player.getPlayerY() + 32);

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

    public boolean getAlive() {
        return alive;
    }
}
