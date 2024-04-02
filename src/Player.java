import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player  {
    private int health;
    private int walkSpeed;
    private int level;
    private boolean invincibility;
    private BufferedImage image;

    public Player(int health, int walkSpeed) {
        this.health = health;
        this.walkSpeed = walkSpeed;
        try {
            image = ImageIO.read(new File("Images/player.png"));
        }
        catch (IOException e) {
            System.out.println("failed");
        }
        int level = 0;
    }


    public BufferedImage getImage() {
        return image;
    }


}
