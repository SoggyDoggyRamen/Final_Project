import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    private int playerX, playerY;
    private int zeroCounter;
    private boolean isRolling;
    private int spriteCounter;
    private int spriteNum;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(0, 0, 5, "down");
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        getPlayerImage();
        zeroCounter = 0;
        spriteNum = 0;
        spriteCounter = 0;

        //Set player values
        playerX = gamePanel.getScreenWidth()/2 - gamePanel.getTileSize();
        playerY = gamePanel.getScreenHeight()/2 - gamePanel.getTileSize();
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

    public void update() {
        //animations
        if (keyHandler.up || keyHandler.down || keyHandler.left || keyHandler.right) {
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
        else {
            spriteNum = 0;
        }
        //Movement for player
        if (keyHandler.up && keyHandler.right) {
            setWorldY(getWorldY() - (int) Math.sqrt(2 * Math.pow(getSpeed(), 2)) / 2 + 1 - 2);
            setWorldX(getWorldX() + (int) Math.sqrt(2 * Math.pow(getSpeed(), 2)) / 2 + 1 + 2);
            super.setDirection("up");
        }
        else if (keyHandler.up && keyHandler.left) {
            setWorldY(getWorldY() - (int) Math.sqrt(2 * Math.pow(getSpeed(), 2)) / 2 + 1 - 2);
            setWorldX(getWorldX() - (int) Math.sqrt(2 * Math.pow(getSpeed(), 2)) / 2 + 1 - 2);
            super.setDirection("up");
        }
        else if (keyHandler.down && keyHandler.right) {
            setWorldY(getWorldY() + (int) Math.sqrt(2 * Math.pow(getSpeed(), 2)) / 2 + 1);
            setWorldX(getWorldX() + (int) Math.sqrt(2 * Math.pow(getSpeed(), 2)) / 2 + 1);
            super.setDirection("down");
        }
        else if (keyHandler.down && keyHandler.left) {
            setWorldY(getWorldY() + (int) Math.sqrt(2 * Math.pow(getSpeed(), 2)) / 2 + 1 + 2);
            setWorldX(getWorldX() - (int) Math.sqrt(2 * Math.pow(getSpeed(), 2)) / 2 + 1 - 2);
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


        g2.drawImage(image, playerX, playerY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }
}
