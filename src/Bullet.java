import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Bullet extends Entity{
    private GamePanel gamePanel;
    private TileManager tileManager;
    private Player player;
    private boolean shoot;
    private String upDown, leftRight;
    private int velX, velY;
    private Image bulletImage;

    public Bullet(GamePanel gamePanel, MouseHandler mouseHandler, TileManager tileManager, Player player) {
        super(-1000, -1000, 10, "down", 0, 0,4, true);
        shoot = false;
        super.setSpeed(12);
        this.gamePanel = gamePanel;
        this.player = player;
        this.tileManager = tileManager;
        getBulletImage();
    }

    public void getBulletImage() {
        try {
            bulletImage = ImageIO.read(new File("Images/Projectiles/player_pew.png"));
        } catch (IOException e) {
            System.out.println("no work");
            throw new RuntimeException(e);
        }
    }

    public void setLeftRight(int mouseX) {
        int playerX = player.getPlayerX() + 32;
        if (mouseX > playerX) {
            leftRight = "right";
        }
        if (mouseX < playerX) {
            leftRight = "left";
        }
    }

    public void setUpDown(int mouseY) {
        int playerY = player.getPlayerY() + 32;
        if (mouseY > playerY) {
            upDown = "down";
        }
        if (mouseY < playerY) {
            upDown = "up";
        }
    }

    public void gotHit() {
        super.setHealth(super.getHealth() - 1);
    }

    public void getBulletVelocity(int mouseX, int mouseY) {
        double dx = mouseX - player.getPlayerX();
        double dy = mouseY - player.getPlayerY();

        double length = Math.sqrt(dx*dx + dy*dy);

        dx /= length;
        dy /= length;

        velX = (int) (dx * super.getSpeed());
        velY = (int) (dy * super.getSpeed());
    }

    public void shooting(boolean bool) {
        shoot = bool;
        super.setHealth(100);
        if (shoot) {
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int mouseX = (int) b.getX() - 320;
            int mouseY = (int) b.getY() - 30;
            getBulletVelocity(mouseX, mouseY);
            super.setWorldX(player.getWorldX() + 16);
            super.setWorldY(player.getWorldY() + 16);
        }
    }

    public void update() {
        if (super.getWorldX() < 0 || super.getWorldX() > tileManager.getMap()[0].length * gamePanel.getTileSize() || super.getWorldY() < 0 || super.getWorldY() > tileManager.getMap().length * gamePanel.getTileSize()) {
            shoot = false;
        }
        if (super.getHealth() < 0 || super.getHealth() == 0) {
            shoot = false;
        }
        if (!shoot) {
            setWorldX(-1000);
            setWorldY(-1000);
        }
        super.setWorldX(super.getWorldX() + velX);
        super.setWorldY(super.getWorldY() + velY);
        setScreenX((super.getWorldX() - player.getWorldX() + player.getPlayerX()));
        setScreenY((super.getWorldY() - player.getWorldY() + player.getPlayerY()));
        moveHitbox(0, 0, 4, 4, getScreenX(), getScreenY());
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(bulletImage, getScreenX(), getScreenY(), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    public boolean getShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }
}
