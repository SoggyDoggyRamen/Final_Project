import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PickledEgg extends Entity{
    GamePanel gamePanel;
    Player player;
    private int zeroCounter, spriteCounter, spriteNum, velX, velY, health;
    private boolean alive;

    public PickledEgg(Player player, GamePanel gamepanel, int worldX, int worldY, Bullets bullets) {
        super(worldX, worldY , 7, "down", 0, 0);
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

    public void update() {
        //moving to player
        getPickledVelocity();
        System.out.println("hi");
        super.setWorldX(super.getWorldX() + velX);
        super.setWorldY(super.getWorldY() + velY);
        setScreenX((super.getWorldX() - player.getWorldX() + player.getPlayerX()));
        setScreenY((super.getWorldY() - player.getWorldY() + player.getPlayerY()));

        //move the hitbox
        createHitbox(6, 1, 19, 29);

        // animations
        spriteCounter ++;
        if (spriteCounter == 10) {
            if (super.getDirection().equals("right") || super.getDirection().equals("left")) {
                if (spriteNum == 0) {
                    if (zeroCounter == 1) {
                        zeroCounter = 0;
                        spriteNum = 2;
                    }
                    else {
                        spriteNum = 1;
                    }
                }
                else if (spriteNum == 1) {
                    if (zeroCounter == 0) {
                        zeroCounter ++;
                        spriteNum = 0;
                    }
                }
                else if (spriteNum == 2) {
                    spriteNum = 0;
                }
            }
            else {
                if (spriteNum == 0) {
                    spriteNum = 1;
                }
                else if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        //Animations for pickle egg
        if (super.getDirection().equals("up")) {
            if (spriteNum == 1) {
                image = super.getUp1();
            }
            else if (spriteNum == 2) {
                image = super.getUp2();
            }
            else {
                image = super.getUp0();
            }
        }
        if(super.getDirection().equals("down")) {
            if (spriteNum == 1) {
                image = super.getDown1();
            }
            else if (spriteNum == 2) {
                image = super.getDown2();
            }
            else {
                image = super.getDown0();
            }
        }
        if(super.getDirection().equals("right")) {
            if (spriteNum == 1) {
                image = super.getRight1();
            }
            else if (spriteNum == 2) {
                image = super.getRight2();
            }
            else {
                image = super.getRight0();
            }
        }
        if (super.getDirection().equals("left")) {
            if (spriteNum == 1) {
                image = super.getLeft1();
            }
            else if (spriteNum == 2) {
                image = super.getLeft2();
            }
            else {
                image = super.getLeft0();
            }
        }

        g2.draw(getHitbox());
        g2.drawImage(image, getScreenX(), getScreenY(), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    public boolean getAlive() {
        return alive;
    }
}
