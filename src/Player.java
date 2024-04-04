import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    private int playerX, playerY, playerSpeed;
    private int zeroCounter;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        getPlayerImage();
        zeroCounter = 0;

        //Set player values
        playerX = 100;
        playerY = 100;
        playerSpeed = 5;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up0 = ImageIO.read(new File("Images/Player/up0.png"));
            up1 = ImageIO.read(new File("Images/Player/up1.png"));
            up2 = ImageIO.read(new File("Images/Player/up2.png"));
            down0 = ImageIO.read(new File("Images/Player/down0.png"));
            down1 = ImageIO.read(new File("Images/Player/down1.png"));
            down2 = ImageIO.read(new File("Images/Player/down2.png"));
            right0 = ImageIO.read(new File("Images/Player/right0.png"));
            right1 = ImageIO.read(new File("Images/Player/right1.png"));
            right2 = ImageIO.read(new File("Images/Player/right2.png"));
            left0 = ImageIO.read(new File("Images/Player/left0.png"));
            left1 = ImageIO.read(new File("Images/Player/left1.png"));
            left2 = ImageIO.read(new File("Images/Player/left2.png"));
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
                if (direction.equals("right") || direction.equals("left")) {
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
            playerY -= (int) Math.sqrt(2 * Math.pow(playerSpeed, 2)) / 2 + 1;
            playerX += (int) Math.sqrt(2 * Math.pow(playerSpeed, 2)) / 2 + 1;
            direction = "up";
        }
        else if (keyHandler.up && keyHandler.left) {
            playerY -= (int) Math.sqrt(2 * Math.pow(playerSpeed, 2)) / 2 + 1;
            playerX -= (int) Math.sqrt(2 * Math.pow(playerSpeed, 2)) / 2 + 1;
            direction = "up";
        }
        else if (keyHandler.down && keyHandler.right) {
            playerY += (int) Math.sqrt(2 * Math.pow(playerSpeed, 2)) / 2 + 1;
            playerX += (int) Math.sqrt(2 * Math.pow(playerSpeed, 2)) / 2 + 1;
            direction = "down";
        }
        else if (keyHandler.down && keyHandler.left) {
            playerY += (int) Math.sqrt(2 * Math.pow(playerSpeed, 2)) / 2 + 1;
            playerX -= (int) Math.sqrt(2 * Math.pow(playerSpeed, 2)) / 2 + 1;
            direction = "down";
        }
        else if (keyHandler.up) {
            playerY -= playerSpeed;
            direction = "up";
        }
        else if(keyHandler.down) {
            playerY += playerSpeed;
            direction = "down";
        }
        else if(keyHandler.right) {
            playerX += playerSpeed;
            direction = "right";
        }
        else if (keyHandler.left) {
            playerX -= playerSpeed;
            direction = "left";
        }


    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        //Animations for player
        if (direction.equals("up")) {
            System.out.println(spriteNum);
            if (spriteNum == 1) {
                image = up1;
            }
            else if (spriteNum == 2) {
                image = up2;
            }
            else {
                image = up0;
            }
        }
        if(direction.equals("down")) {
            if (spriteNum == 1) {
                image = down1;
            }
            else if (spriteNum == 2) {
                image = down2;
            }
            else {
                image = down0;
            }
        }
        if(direction.equals("right")) {
            if (spriteNum == 1) {
                image = right1;
            }
            else if (spriteNum == 2) {
                image = right2;
            }
            else {
                image = right0;
            }
        }
        if (direction.equals("left")) {
            if (spriteNum == 1) {
                image = left1;
            }
            else if (spriteNum == 2) {
                image = left2;
            }
            else {
                image = left0;
            }
        }

        g2.drawImage(image, playerX, playerY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
