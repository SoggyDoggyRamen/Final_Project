import java.awt.*;

public class Tile {
    private Image image;
    private boolean collision;

    public Tile(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
