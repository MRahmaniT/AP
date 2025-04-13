package Game;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Supper Hexagon");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main game panel
        Game.GamePanel panel = new Game.GamePanel();
        add(panel);
    }
}
