import java.awt.*;

public class BulletHandler {
    private Bullet[] bullets;
    private int bulletNum;
    private int num;
    private MouseHandler mouseHandler;
    private GamePanel gamePanel;
    private long timePassed;
    private long startTime;

    public BulletHandler(GamePanel gamePanel, MouseHandler mouseHandler, Player player, int num, TileManager tileManager) {
        bullets = new Bullet[num];
        for (int i = 0; i < num; i ++) {
            Bullet bullet = new Bullet(gamePanel, mouseHandler, tileManager, player);
            bullets[i] = bullet;
        }
        this.num = num;
        this.mouseHandler = mouseHandler;
        this.gamePanel = gamePanel;
        timePassed = 500;
        bulletNum = 0;
    }

    public void update() {
        if (mouseHandler.mouse1Down && timePassed > 124) {
            if (bulletNum == num - 1) {
                bulletNum = 0;
            }
            bullets[bulletNum].shooting(true);
            bulletNum ++;
            startTime = System.currentTimeMillis();
            timePassed = 0;
        }
        else {
            timePassed = System.currentTimeMillis() - startTime;
        }
        for (Bullet bullet: bullets) {
            if (bullet.getShoot()) {
                bullet.update();
            }
        }
    }

    public void draw(Graphics2D g2) {
        for (Bullet bullet: bullets) {
            if (bullet.getShoot()) {
                bullet.draw(g2);
            }
        }
    }

    public Bullet[] getBullets() {
        return bullets;
    }
}
