import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DrawPanel extends JPanel implements KeyListener {
    private boolean running;
    private Player player;
    public DrawPanel() {
        this.addKeyListener(this);
        player = new Player(10, 10);
        running = true;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(player.getImage(), 10, 10, (int) (player.getImage().getWidth() * 0.1), (int) (player.getImage().getHeight() * 0.1), null);

    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}