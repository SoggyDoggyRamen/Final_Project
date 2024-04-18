import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    public boolean mouse1Down;
    public int x, y;


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getButton());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            mouse1Down = true;
            x = e.getXOnScreen();
            y = e.getYOnScreen();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1) {
            mouse1Down = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("hi");
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
