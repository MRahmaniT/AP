package Main;

import javax.swing.*;
import java.awt.*;
import Game.GamePanel;
import Menu.MenuPanel;

public class MainFrame extends JFrame {
    private static CardLayout cardLayout;
    private static JPanel mainPanel; // This will hold the cards
    private MenuPanel menuPanel;
    private GamePanel gamePanel;

    public MainFrame() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout); // set the CardLayout

        // Create or instantiate your cards
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel();

        // Add them with identifying names
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(gamePanel, "Game");

        // Add the mainPanel to the frame
        add(mainPanel);

        // Basic frame settings
        setTitle("CardLayout Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Start on the "Menu" card
        cardLayout.show(mainPanel, "Menu");
    }

    // A method to switch to the Game panel
    public static void showGame() {
        cardLayout.show(mainPanel, "Game");
    }

}
