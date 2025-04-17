package Menu;

import Data.DataManager;
import Main.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {

    private Image backgroundImage;
    private JLabel bestScoreLabel;

    public MenuPanel(DataManager dataManager) {
        // Optional: load scores here, or do it in MainFrame before calling this constructor
        dataManager.loadFile();

        // Compute the best score (0 if no players yet)
        int bestScore = dataManager.getBestScore();

        // Try loading a background image (optional)
        try {
            backgroundImage = ImageIO.read(new File("background.jpg")); // put your real image path
        } catch (IOException e) {
            e.printStackTrace();
        }

        //setPreferredSize(new Dimension(500, 400));

        // 5 rows, 1 column, with 10px gaps
        setLayout(new GridLayout(5, 1, 10, 10));

        // 1) A colorful label at the top showing best score
        bestScoreLabel = new JLabel("Best Score: " + bestScore, SwingConstants.CENTER);
        bestScoreLabel.setForeground(Color.MAGENTA);
        bestScoreLabel.setFont(bestScoreLabel.getFont().deriveFont(Font.BOLD, 18f));
        add(bestScoreLabel);

        // 2) Start New Game button
        JButton btnStartGame = new JButton("Start New Game");
        btnStartGame.addActionListener(e -> {
            MainFrame.takePlayerName();
        });
        add(btnStartGame);

        // 3) Game History button
        JButton btnHistory = new JButton("Game History");
        btnHistory.addActionListener(e -> {
            // Show game history or do something similar
        });
        add(btnHistory);

        // 4) Settings button
        JButton btnSettings = new JButton("Settings");
        btnSettings.addActionListener(e -> {
            // Show or switch to a settings panel
        });
        add(btnSettings);

        // 5) Exit button
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> {
            System.exit(0);
        });
        add(btnExit);
    }

    // Override paintComponent to draw the background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // If we have a background image, fill the panel with it
        if (backgroundImage != null) {
            // Draw image scaled to panel size
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // Optionally, fill with a solid color or gradient
            // e.g., g.setColor(Color.DARK_GRAY);
            // g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
