package Game;

import Shape.RotatingBackground;
import Shape.GameShape;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {

    private final List<GameShape> shapes = new ArrayList<>();

    public GamePanel() {
        // ~120 FPS
        Timer timer = new Timer(8, this);
        timer.start();

        // Create a rotating background with a large radius (say 1000) and 1 degree per frame
        RotatingBackground bg = new RotatingBackground(1000, 0.1f);
        shapes.add(bg);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // We want to draw the background centered on the panel, so let’s translate
        // to the panel’s center, then draw.
        Graphics2D g2d = (Graphics2D) g.create();

        // Translate to the center of this panel
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        g2d.translate(cx, cy);

        // Now draw each shape
        for (GameShape shape : shapes) {
            shape.draw(g2d);
        }

        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update each shape
        for (GameShape shape : shapes) {
            shape.update();
        }
        repaint();
    }
}
