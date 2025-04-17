package Main;

import javax.swing.*;
import java.awt.*;
import Game.GamePanel;
import Game.GameOverPanel;
import Menu.MenuPanel;
import Player.PlayerPanel;
import Data.DataManager;

public class MainFrame extends JFrame {
    private static CardLayout cardLayout;
    private static JPanel mainPanel;

    //Panels
    private DataManager dataManager = new DataManager();
    private MenuPanel menuPanel;
    private static GamePanel gamePanel;
    private static GameOverPanel gameOverPanel;
    private static PlayerPanel playerPanel;

    public MainFrame() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout); // set the CardLayout

        // Create or instantiate your cards
        menuPanel = new MenuPanel(dataManager);
        gamePanel = new GamePanel();
        gameOverPanel = new GameOverPanel();
        playerPanel = new PlayerPanel();

        // Add them with identifying names
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(gamePanel, "Game");
        mainPanel.add(gameOverPanel, "GameOver");
        mainPanel.add(playerPanel, "Name");

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
    public static void showMenu() {
        cardLayout.show(mainPanel, "Menu");
    }
    public static void showGame() {
        gamePanel = new GamePanel();
        cardLayout.show(mainPanel, "Game");
    }
    public static void showGameOver() {
        gameOverPanel = new GameOverPanel();
        cardLayout.show(mainPanel, "GameOver");
    }
    public static void takePlayerName() {
        playerPanel = new PlayerPanel();
        cardLayout.show(mainPanel, "Name");
    }
}
