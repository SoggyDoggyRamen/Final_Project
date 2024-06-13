import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Drone extends Enemy{
    private int zeroCounter, spriteCounter, spriteNum, lineX1, lineY1, lineX2, lineY2, framesPassed, linewx1, linewy1, linewx2, linewy2;
    private int redLineFrameStart, redLineFrameEnd, stillLineFrameStart, laserFrameEnd;
    private boolean indicator, laser, drawIndicator, drawLaser, drawStillIndicator;
    private ArrayList<PickledEgg> pickledEggs;
    private ArrayList<Drone> drones;

    public Drone(int worldX, int worldY, GamePanel gamePanel, Player player, ArrayList<PickledEgg> pickledEggs, ArrayList<Drone> drones) {
        super(worldX, worldY , 5, 100, player, true);
        this.gamePanel = gamePanel;
        this.player = player;
        this.pickledEggs = pickledEggs;
        this.drones =drones;
        framesPassed = 0;
        redLineFrameStart = 30;
        redLineFrameEnd = 150;
        stillLineFrameStart = 120;
        zeroCounter = 0;
        spriteNum = 0;
        spriteCounter = 0;
        indicator = true;
        drawIndicator = false;
        drawLaser = false;
        getDroneImage();
    }

    public void gotHit() {
        setHealth(getHealth() - 50);
    }

    public void getDroneImage() {
        try {
            super.setDown0(ImageIO.read(new File("Images/Enemies/Drone/down0.png")));
            super.setDown1(ImageIO.read(new File("Images/Enemies/Drone/down1.png")));
            super.setDown2(ImageIO.read(new File("Images/Enemies/Drone/down2.png")));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void drawIndicator(Graphics2D g2) {
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

    public void drawStillIndicator(Graphics2D g2) {
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

    public void update() {
        if (player.getAlive()) {
            //moving to player
            int[] velocity = getEnemyVelocity();
            super.setWorldX(super.getWorldX() + velocity[0]);
            super.setWorldY(super.getWorldY() + velocity[1]);
            setScreenX((super.getWorldX() - player.getWorldX() + player.getScreenX()));
            setScreenY((super.getWorldY() - player.getWorldY() + player.getScreenY()));

            //move the hitbox
            moveHitbox(10, 9, 11, 13, getScreenX(), getScreenY());

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

            // indicator and laser stuff
            if (framesPassed >= redLineFrameStart && framesPassed <= redLineFrameEnd && indicator) {
                drawLaser = false;
                if (framesPassed <= stillLineFrameStart) {
                    drawIndicator = true;
                    convertToWorldCoordinates();
                }
                if (framesPassed > stillLineFrameStart) {
                    drawStillIndicator = true;
                    drawIndicator = false;
                }
                if (framesPassed == redLineFrameEnd) {
                    framesPassed = 0;
                    indicator = false;
                    drawStillIndicator = false;
                    laser = true;
                }
            }
            if (laser) {
                drawLaser = true;
                if (framesPassed == laserFrameEnd) {
                    laser = false;
                    indicator = true;
                    framesPassed = 0;
                }
            }
            framesPassed ++;
        }
    }

    public void draw(Graphics2D g2) {
        Image image = null;

        //red line indicator stuff
        if (drawIndicator) {
            drawIndicator(g2);
        }
        else if (drawStillIndicator) {
            drawStillIndicator(g2);
        }
        else if (drawLaser) {
            drawLaser(g2);
        }

        //Animations for drone
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

    public int getLinewy1() {
        return linewy1;
    }

    public int getLinewx1() {
        return linewx1;
    }

    public int getLinewx2() {
        return linewx2;
    }

    public int getLinewy2() {
        return linewy2;
    }

    public boolean isLaser() {
        return laser;
    }

    public boolean isDrawLaser() {
        return drawLaser;
    }
}
