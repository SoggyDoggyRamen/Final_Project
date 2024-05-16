import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    private int worldX, worldY, screenX, screenY;
    private int speed;
    private BufferedImage up0, up1, up2, down0, down1, down2, left0, left1, left2, right0, right1, right2;
    private String direction;
    private Rectangle hitbox;
    private int health;

    public Entity(int worldX, int worldY, int speed, String direction, int screenX, int screenY) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.screenX = screenX;
        this.screenY = screenY;
        this.speed = speed;
        this.direction = direction;
    }

    public void createHitbox(int incrementX, int incrementY, int width, int height) {
        hitbox = new Rectangle();
        hitbox.setBounds(screenX + incrementX * 2, screenY + incrementY * 2, width * 2, height * 2);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
    public int getSpeed() {
        return speed;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public BufferedImage getUp0() {
        return up0;
    }

    public BufferedImage getUp1() {
        return up1;
    }

    public BufferedImage getUp2() {
        return up2;
    }

    public BufferedImage getDown0() {
        return down0;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public BufferedImage getDown2() {
        return down2;
    }

    public BufferedImage getLeft0() {
        return left0;
    }

    public BufferedImage getLeft1() {
        return left1;
    }

    public BufferedImage getLeft2() {
        return left2;
    }

    public BufferedImage getRight0() {
        return right0;
    }

    public BufferedImage getRight1() {
        return right1;
    }

    public BufferedImage getRight2() {
        return right2;
    }

    public String getDirection() {
        return direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setUp0(BufferedImage up0) {
        this.up0 = up0;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public void setDown0(BufferedImage down0) {
        this.down0 = down0;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public void setLeft0(BufferedImage left0) {
        this.left0 = left0;
    }

    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }

    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }

    public void setRight0(BufferedImage right0) {
        this.right0 = right0;
    }

    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }
}