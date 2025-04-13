package Menu;

import javax.swing.JFrame;

public class MenuFrame extends JFrame {

    public MenuFrame() {
        setTitle("Supper Hexagon Menu");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the menu panel
        MenuPanel panel = new MenuPanel();
        add(panel);
    }
}
