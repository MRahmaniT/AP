package Menu;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;

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
            MainFrame.takePlayerName();
            System.out.println("Start New Game clicked");
        });

        btnBestRecord.addActionListener(e -> {
            System.out.println("Best Record clicked");
        });

        btnHistory.addActionListener(e -> {
            System.out.println("Game History clicked");
        });

        btnSettings.addActionListener(e -> {
            System.out.println("Settings clicked");
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
