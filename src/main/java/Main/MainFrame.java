package Main;

import javax.swing.*;
import java.awt.*;
import Game.GamePanel;
import Game.GameOverPanel;
import Menu.MenuPanel;
import Music.SoundPlayer;
import Player.PlayerPanel;
import Data.DataManager;
import Data.HistoryPanel;
import Setting.SettingPanel;

public class MainFrame extends JFrame {
    private static CardLayout cardLayout;
    private static JPanel mainPanel;

    //Panels
    private static SettingPanel settingPanel;
    private static SoundPlayer musicPlayer;
    private DataManager dataManager = new DataManager();
    private MenuPanel menuPanel;
    private static GamePanel gamePanel;
    private static GameOverPanel gameOverPanel;
    private static PlayerPanel playerPanel;
    private static HistoryPanel historyPanel;

    public MainFrame() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        //Music
        musicPlayer = new SoundPlayer();
        if (SettingPanel.playSound){musicPlayer.playMusic("music.wav");}

        // Create or instantiate your cards
        menuPanel = new MenuPanel(dataManager);
        historyPanel = new HistoryPanel(dataManager);
        settingPanel = new SettingPanel();
        gamePanel = new GamePanel();
        gameOverPanel = new GameOverPanel();
        playerPanel = new PlayerPanel();

        // Add them with identifying names
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(historyPanel, "History");
        mainPanel.add(settingPanel, "Setting");
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
    public static void showSetting() {
        cardLayout.show(mainPanel, "Setting");
    }
    public static void showGame() {
        gamePanel = new GamePanel();
        mainPanel.remove(gamePanel);
        mainPanel.add(gamePanel, "Game");
        mainPanel.revalidate();
        mainPanel.repaint();
        cardLayout.show(mainPanel, "Game");
    }
    public static void showHistory() {
        DataManager dataManager = new DataManager();
        historyPanel = new HistoryPanel(dataManager);
        mainPanel.remove(historyPanel);
        mainPanel.add(historyPanel, "History");
        mainPanel.revalidate();
        mainPanel.repaint();
        cardLayout.show(mainPanel, "History");
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
