import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    private int zeroCounter, spriteCounter, spriteNum, sideSpriteNum;
    private double angle;
    private double angleInRadians;
    private boolean isRolling;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(0, 0, 10, "down", gamePanel.getScreenWidth()/2 - gamePanel.getTileSize(), gamePanel.getScreenHeight()/2 - gamePanel.getTileSize(), 100, true);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        getPlayerImage();
        zeroCounter = 0;
        spriteNum = 0;
        spriteCounter = 0;
        isRolling = false;
    }

    public void getPlayerImage() {
        try {
            super.setUp0(ImageIO.read(new File("Images/Player/up0.png")));
            super.setUp1(ImageIO.read(new File("Images/Player/up1.png")));
            super.setUp2(ImageIO.read(new File("Images/Player/up2.png")));
            super.setDown0(ImageIO.read(new File("Images/Player/down0.png")));
            super.setDown1(ImageIO.read(new File("Images/Player/down1.png")));
            super.setDown2(ImageIO.read(new File("Images/Player/down2.png")));
            super.setRight0(ImageIO.read(new File("Images/Player/right0.png")));
            super.setRight1(ImageIO.read(new File("Images/Player/right1.png")));
            super.setRight2(ImageIO.read(new File("Images/Player/right2.png")));
            super.setLeft0(ImageIO.read(new File("Images/Player/left0.png")));
            super.setLeft1(ImageIO.read(new File("Images/Player/left1.png")));
            super.setLeft2(ImageIO.read(new File("Images/Player/left2.png")));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void gotHit() {
        super.setHealth(super.getHealth() - 10);
    }

    public void update() {
        //animations
        if (keyHandler.up || keyHandler.down || keyHandler.left || keyHandler.right) {
            spriteCounter ++;
            if (spriteCounter == 10) {
                if (super.getDirection().equals("right") || super.getDirection().equals("left")) {
                    if (sideSpriteNum == 0) {
                        if (zeroCounter == 1) {
                            zeroCounter = 0;
                            sideSpriteNum = 2;
                        }
                        else {
                            sideSpriteNum = 1;
                        }
                    }
                    else if (sideSpriteNum == 1) {
                        if (zeroCounter == 0) {
                            zeroCounter ++;
                            sideSpriteNum = 0;
                        }
                    }
                    else if (sideSpriteNum == 2) {
                        sideSpriteNum = 0;
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
        else {
            spriteNum = 0;
            sideSpriteNum = 0;
        }
        //Movement for player
        if (keyHandler.up && keyHandler.right) {
            angle = 315.0;
            angleInRadians = Math.toRadians(angle);
            setWorldX(getWorldX() + (int) (Math.cos(angleInRadians) * getSpeed()));
            setWorldY(getWorldY() + (int) (Math.sin(angleInRadians) * getSpeed()));
            super.setDirection("up");
        }
        else if (keyHandler.up && keyHandler.left) {
            angle = 225.0;
            angleInRadians = Math.toRadians(angle);
            setWorldX(getWorldX() + (int) (Math.cos(angleInRadians) * getSpeed()));
            setWorldY(getWorldY() + (int) (Math.sin(angleInRadians) * getSpeed()));
            super.setDirection("up");
        }
        else if (keyHandler.down && keyHandler.right) {
            angle = 45.0;
            angleInRadians = Math.toRadians(angle);
            setWorldX(getWorldX() + (int) (Math.cos(angleInRadians) * getSpeed()));
            setWorldY(getWorldY() + (int) (Math.sin(angleInRadians) * getSpeed()));
            super.setDirection("down");
        }
        else if (keyHandler.down && keyHandler.left) {
            angle = 135.0;
            angleInRadians = Math.toRadians(angle);
            setWorldX(getWorldX() + (int) (Math.cos(angleInRadians) * getSpeed()));
            setWorldY(getWorldY() + (int) (Math.sin(angleInRadians) * getSpeed()));
            super.setDirection("down");
        }
        else if (keyHandler.up) {
            setWorldY(getWorldY() - getSpeed());
            super.setDirection("up");
        }
        else if(keyHandler.down) {
            setWorldY(getWorldY() + getSpeed());
            super.setDirection("down");
        }
        else if(keyHandler.right) {
            setWorldX(getWorldX() + getSpeed());
            super.setDirection("right");
        }
        else if (keyHandler.left) {
            setWorldX(getWorldX() - getSpeed());
            super.setDirection("left");
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        //Animations for player
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
            if (sideSpriteNum == 1) {
                image = super.getRight1();
            }
            else if (sideSpriteNum == 2) {
                image = super.getRight2();
            }
            else {
                image = super.getRight0();
            }
        }
        if (super.getDirection().equals("left")) {
            if (sideSpriteNum == 1) {
                image = super.getLeft1();
            }
            else if (sideSpriteNum == 2) {
                image = super.getLeft2();
            }
            else {
                image = super.getLeft0();
            }
        }

        g2.drawImage(image, super.getScreenX(), getScreenY(), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    public int getPlayerX() {
        return getScreenX();
    }

    public int getPlayerY() {
        return getScreenY();
    }
}
