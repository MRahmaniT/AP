package Game;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {

    private Timer timer;
    //private GameController controller;  // optional reference to a controller/engine

    public GamePanel() {
        // 60 FPS → ~16 ms between frames
        timer = new Timer(16, this);
        timer.start();

        // Optionally create or pass in an existing GameController
        //controller = new GameController();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw game objects
        // e.g. controller.drawAll(g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update game objects each frame
        // e.g. controller.updateAll();

        // Redraw the panel
        repaint();
    }
}
