import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PickledEgg extends Entity{
    GamePanel gamePanel;
    Player player;
    private int zeroCounter, spriteCounter, spriteNum, velX, velY, lineX1, lineY1, lineX2, lineY2, framesPassed, linewx1, linewy1, linewx2, linewy2;
    private boolean alive;
    private boolean ranged, redLine, laser;
    Image redImage;

    public PickledEgg(Player player, GamePanel gamepanel, int worldX, int worldY) {
        super(worldX, worldY , 7, "down", 0, 0, 100);
        createHitbox(6, 1, 19, 29);
        this.gamePanel = gamepanel;
        this.player = player;
        zeroCounter = 0;
        spriteNum = 0;
        spriteCounter = 0;
        framesPassed = 0;
        redLine = true;
        laser = false;
        alive = true;
        ranged = false;
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

    public void setRanged(boolean var) {
        ranged = var;
    }

    public void update() {
        //moving to player
        getPickledVelocity();
        super.setWorldX(super.getWorldX() + velX);
        super.setWorldY(super.getWorldY() + velY);
        setScreenX((super.getWorldX() - player.getWorldX() + player.getScreenX()));
        setScreenY((super.getWorldY() - player.getWorldY() + player.getScreenY()));

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

    public void drawRedLine(Graphics2D g2) {
        g2.setStroke(new BasicStroke(3));
        Color c = new Color(1f,0f,0f,.3f );
        g2.setColor(c);
        int x1 = getScreenX();
        int y1 = getScreenY();
        int x2 = player.getScreenX();
        int y2 = player.getScreenY();
        lineX1 = x1 + 32;
        lineY1 = y1 + 32;
        for (int i = 0; i < 5; i ++) {
            g2.drawLine(x1 + 32, y1 + 32, x2 + 32, y2 + 32);
            int temp1 = x2;
            int temp2 = y2;
            x2 = x2 - (x1 - x2);
            y2 = y2 - (y1 - y2);
            x1 = temp1;
            y1 = temp2;
        }
        lineX2 = x2 + 32;
        lineY2 = y2 + 32;
    }

    public void drawStillLine(Graphics2D g2) {
        g2.setStroke(new BasicStroke(3));
        Color c = new Color(1f,0f,0f,.3f );
        g2.setColor(c);
        g2.drawLine(linewx1 - player.getWorldX() + player.getScreenX(), linewy1 - player.getWorldY() + player.getScreenY(), linewx2 - player.getWorldX() + player.getScreenX(), linewy2 - player.getWorldY() + player.getScreenY());
    }
    public void drawLaser(Graphics2D g2) {
        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.RED);

        g2.drawLine(linewx1 - player.getWorldX() + player.getScreenX(), linewy1 - player.getWorldY() + player.getScreenY(), linewx2 - player.getWorldX() + player.getScreenX(), linewy2 - player.getWorldY() + player.getScreenY());
    }

    public void convertToWorldCoordinates() {
        linewx1 = lineX1 + player.getWorldX() - player.getScreenX();
        linewx2 = lineX2 + player.getWorldX() - player.getScreenX();
        linewy1 = lineY1 + player.getWorldY() - player.getScreenY();
        linewy2 = lineY2 + player.getWorldY() - player.getScreenY();
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        //red line indicator
        g2.setStroke(new BasicStroke(3));
        Color c = new Color(1f,0f,0f,.3f );
        if (ranged) {
            System.out.println(framesPassed);
            if (framesPassed >= 30 && framesPassed <= 150 && redLine) {
                if (framesPassed >= 30 && framesPassed <= 120) {
                    drawRedLine(g2);
                    convertToWorldCoordinates();
                }
                if (framesPassed > 120) {
                    drawStillLine(g2);
                }
                if (framesPassed == 150) {
                    framesPassed = 0;
                    redLine = false;
                    laser = true;
                }
            }
            if (laser) {
                drawLaser(g2);
                if (framesPassed == 45) {
                    laser = false;
                    redLine = true;
                    framesPassed = 0;
                }
            }
            framesPassed ++;
        }

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
