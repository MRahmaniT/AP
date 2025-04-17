package Game;

import Data.DataManager;
import Main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {

    private final DataManager dataManager = new DataManager();

    public GameOverPanel() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title or instructions
        JLabel promptLabel1 = new JLabel("GAME OVER");
        promptLabel1.setForeground(Color.RED);
        promptLabel1.setSize(5,2);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(promptLabel1, gbc);

        // Proceed button
        JButton proceedButton = new JButton("Try again");
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(proceedButton, gbc);

        // Optional: a "Back" or "Cancel" button
        JButton cancelButton = new JButton("Cancel");
        gbc.gridx = 1;
        add(cancelButton, gbc);

        // Button action: validate or create Person
        proceedButton.addActionListener(e -> handleProceed());
        cancelButton.addActionListener(e -> handleCancel());
    }

    private void handleProceed() {
        MainFrame.showGame();
    }

    private void handleCancel() {
        MainFrame.showMenu();
    }
}
