package Menu;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;

import Main.Main;
import Main.MainFrame;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        // 5 rows, 1 column, 10px horizontal/vertical gap
        setLayout(new GridLayout(5, 1, 10, 10));

        // Create the buttons
        JButton btnStartGame   = new JButton("Start New Game");
        JButton btnBestRecord  = new JButton("Best Record");
        JButton btnHistory     = new JButton("Game History");
        JButton btnSettings    = new JButton("Settings");
        JButton btnExit        = new JButton("Exit");

        // Add action listeners for each button
        btnStartGame.addActionListener(e -> {
            MainFrame.showGame();
            System.out.println("Start New Game clicked");
            // TODO: switch to your GameFrame or show the game panel
        });

        btnBestRecord.addActionListener(e -> {
            System.out.println("Best Record clicked");
            // TODO: retrieve from DataManager and show best score, etc.
        });

        btnHistory.addActionListener(e -> {
            System.out.println("Game History clicked");
            // TODO: retrieve or display list of played games from DataManager
        });

        btnSettings.addActionListener(e -> {
            System.out.println("Settings clicked");
            // TODO: open or switch to a settings panel
        });

        btnExit.addActionListener(e -> {
            System.out.println("Exit clicked");
            System.exit(0);
        });

        // Add buttons to the panel
        add(btnStartGame);
        add(btnBestRecord);
        add(btnHistory);
        add(btnSettings);
        add(btnExit);
    }
}
