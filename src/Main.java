import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pew Pew Adventures");

        GamePanel gamePanel = new GamePanel();
        TileManager tileManager = new TileManager(gamePanel);
        window.add(gamePanel);
        window.pack();

        window.setLocation(0, 0);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
